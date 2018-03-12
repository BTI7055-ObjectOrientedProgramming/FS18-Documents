package persistence.jaxb;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04.MaritalstatusType;
import https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04.ObjectFactory;
import https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04.PersonType;
import https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04.PersonsType;
import model.MaritalStatus;
import model.Person;
import persistence.PersistenceException;
import persistence.PersonDAO;

public class JAXBPersonDAO implements PersonDAO {
	private static final String SCHEMA_LOCATION_ATTRIBUTE_VALUE = "https://github.com/BTI7055-ObjectOrientedProgramming/FS18-Documents/topic04 Schema/Persons.xsd";
	private static final String JAXB_CONTEXT_NAME = "https.github_com.bti7055_objectorientedprogramming.fs18_documents.topic04";
	private File file;
	private JAXBContext jaxbContext;

	public JAXBPersonDAO(File file) throws PersistenceException {
		this.file = file;
		try {
			jaxbContext = JAXBContext.newInstance(JAXB_CONTEXT_NAME);
		} catch (JAXBException e) {
			throw new PersistenceException("Internal error.", e);
		}
	}

	@Override
	public void save(List<Person> persons) throws PersistenceException {
		ObjectFactory factory = new ObjectFactory();
		try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
			Marshaller marshaller = createMarshaller();
			PersonsType personsXML = factory.createPersonsType();
			personsXML.setVersion("FS2018");
			for (Person person : persons) {
				PersonType personXML = factory.createPersonType();
				copyPersonData(person, personXML);
				personsXML.getPerson().add(personXML);
			}
			JAXBElement<PersonsType> personsElement = factory.createPersons(personsXML);
			marshaller.marshal(personsElement, out);
		} catch (IOException | JAXBException | DatatypeConfigurationException e) {
			throw new PersistenceException("Error while saving to file " + file.getAbsolutePath(), e);
		}
	}

	private Marshaller createMarshaller() throws JAXBException, PropertyException {
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, SCHEMA_LOCATION_ATTRIBUTE_VALUE);
		return marshaller;
	}

	private void copyPersonData(Person person, PersonType personXML) throws DatatypeConfigurationException {
		personXML.setName(person.getLastname());
		personXML.setFirstname(person.getFirstname());
		personXML.setMaritalstatus(MaritalstatusType.fromValue(person.getMaritalStatus().toString()));
		GregorianCalendar gcal = GregorianCalendar.from(person.getBirthdate().atStartOfDay(ZoneId.systemDefault()));
		XMLGregorianCalendar xcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
		personXML.setBirthdate(xcal);
	}

	@Override
	public List<Person> load() throws PersistenceException {
		ArrayList<Person> persons = new ArrayList<>();
		try (FileReader in = new FileReader(file)) {
			Unmarshaller unmarschaller = createUnmarshaller();
			JAXBElement<PersonsType> personsElement = (JAXBElement<PersonsType>) unmarschaller.unmarshal(in);
			for (PersonType personXML : personsElement.getValue().getPerson()) {
				Person person = createPersonFromPersonType(personXML);
				persons.add(person);
			}
		} catch (JAXBException | IOException | SAXException e) {
			throw new PersistenceException("Error while loading from file " + file.getAbsolutePath(), e);
		}
		return persons;
	}

	private Unmarshaller createUnmarshaller() throws JAXBException, SAXException {
		Unmarshaller unmarschaller = jaxbContext.createUnmarshaller();
		Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File("Schema/Persons.xsd"));
		unmarschaller.setSchema(schema );
		return unmarschaller;
	}

	private Person createPersonFromPersonType(PersonType personXML) {
		String lastname = personXML.getName();
		String firstname = personXML.getFirstname();
		LocalDate birthdate = personXML.getBirthdate().toGregorianCalendar().toZonedDateTime().toLocalDate();
		MaritalStatus maritalStatus = MaritalStatus.valueOf(personXML.getMaritalstatus().toString());
		return new Person(lastname, firstname, birthdate, maritalStatus);
	}
}
