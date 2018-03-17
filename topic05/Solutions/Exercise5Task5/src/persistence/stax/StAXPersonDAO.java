package persistence.stax;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import model.MaritalStatus;
import model.Person;
import persistence.PersistenceException;
import persistence.PersonDAO;

public class StAXPersonDAO implements PersonDAO {
	private File xmlFile;

	public StAXPersonDAO(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	public void save(List<Person> persons) throws PersistenceException {
		XMLOutputFactory outputFactory = XMLOutputFactory.newFactory();
		XMLEventFactory eventFactory = XMLEventFactory.newFactory();
		try(FileWriter outputStream = new FileWriter(xmlFile)) {
			XMLEventWriter eventWriter = outputFactory.createXMLEventWriter(outputStream);
			writeDocumentStart(eventFactory, eventWriter);
			for (Person person : persons) {
				writePersonElement(eventFactory, person, eventWriter);
			}
			writeDocumentEnd(eventFactory, eventWriter);
			outputStream.flush();
			outputStream.close();
		} catch (IOException | XMLStreamException e) {
			throw new PersistenceException("Cannot write to file " + xmlFile.getAbsolutePath(), e);
		}
	}

	private void writeDocumentStart(XMLEventFactory factory, XMLEventWriter eventWriter) throws XMLStreamException {
		eventWriter.add(factory.createStartDocument());
		eventWriter.add(factory.createIgnorableSpace("\n"));
		eventWriter.add(factory.createStartElement(new QName(null, "Persons"), null, null));
		eventWriter.add(factory.createIgnorableSpace("\n"));
	}

	private void writePersonElement(XMLEventFactory factory, Person person, XMLEventWriter eventWriter) throws XMLStreamException {
		eventWriter.add(factory.createIgnorableSpace("\t"));
		eventWriter.add(factory.createStartElement(new QName(null, "Person"), null, null));
		eventWriter.add(factory.createIgnorableSpace("\n\t\t"));
		eventWriter.add(factory.createStartElement(new QName(null, "Name"), null, null));
		eventWriter.add(factory.createCharacters(person.getLastname()));
		eventWriter.add(factory.createEndElement(new QName(null, "Name"), null));
		eventWriter.add(factory.createIgnorableSpace("\n\t\t"));
		eventWriter.add(factory.createStartElement(new QName(null, "Firstname"), null, null));
		eventWriter.add(factory.createCharacters(person.getFirstname()));
		eventWriter.add(factory.createEndElement(new QName(null, "Firstname"), null));
		eventWriter.add(factory.createIgnorableSpace("\n\t\t"));
		eventWriter.add(factory.createStartElement(new QName(null, "Birthdate"), null, null));
		eventWriter.add(factory.createCharacters(person.getBirthdate().toString()));
		eventWriter.add(factory.createEndElement(new QName(null, "Birthdate"), null));
		eventWriter.add(factory.createIgnorableSpace("\n\t\t"));
		eventWriter.add(factory.createStartElement(new QName(null, "Maritalstatus"), null, null));
		eventWriter.add(factory.createCharacters(person.getMaritalStatus().toString()));
		eventWriter.add(factory.createEndElement(new QName(null, "Maritalstatus"), null));
		eventWriter.add(factory.createIgnorableSpace("\n\t"));
		eventWriter.add(factory.createEndElement(new QName(null, "Person"), null));
		eventWriter.add(factory.createIgnorableSpace("\n"));
	}

	private void writeDocumentEnd(XMLEventFactory factory, XMLEventWriter eventWriter) throws XMLStreamException {
		eventWriter.add(factory.createEndElement(new QName(null, "Persons"), null));
		eventWriter.add(factory.createIgnorableSpace("\n"));
		eventWriter.add(factory.createEndDocument());
	}

	@Override
	public List<Person> load() throws PersistenceException {
		ArrayList<Person> persons = new ArrayList<>();
		XMLInputFactory factory = XMLInputFactory.newFactory();
		try(FileReader inputStream = new FileReader(xmlFile)) {
			XMLEventReader eventReader = factory.createXMLEventReader(inputStream);
			StartElement personStart = findStartElement(eventReader, new QName(null, "Person"));
			while (personStart != null) {
				Person person = createPerson(eventReader);
				persons.add(person);
				personStart = findStartElement(eventReader, new QName(null, "Person"));
			}
			inputStream.close();
		} catch (IOException | XMLStreamException e) {
			throw new PersistenceException("Cannot read file " + xmlFile.getAbsolutePath(), e);
		}
		return persons;
	}

	private StartElement findStartElement(XMLEventReader eventReader, QName tagName) throws XMLStreamException {
		while (eventReader.hasNext()) {
			XMLEvent event = eventReader.nextEvent();
			if (event.getEventType() == XMLEvent.START_ELEMENT) {
				if (event.asStartElement().getName().equals(tagName))
					return event.asStartElement();
			}
		}
		return null;
	}

	private Person createPerson(XMLEventReader eventReader) throws XMLStreamException {
		findStartElement(eventReader, new QName(null, "Name"));
		String lastname = eventReader.getElementText();
		findStartElement(eventReader, new QName(null, "Firstname"));
		String firstname = eventReader.getElementText();
		findStartElement(eventReader, new QName(null, "Birthdate"));
		String birthdate = eventReader.getElementText();
		findStartElement(eventReader, new QName(null, "Maritalstatus"));
		String maritalstatus = eventReader.getElementText();
		return new Person(lastname, firstname, LocalDate.parse(birthdate), MaritalStatus.valueOf(maritalstatus));
	}
}
