package persistence.dom;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.MaritalStatus;
import model.Person;
import persistence.PersistenceException;
import persistence.PersonDAO;

public class DOMPersonDAO implements PersonDAO {
	private static final String PERSON_NS_URI = "https://github.com/BTI7055-ObjectOrientedProgramming/FS18-Documents/topic04";
	private static final String SCHEMA_LOCATION = "Schema/Persons.xsd";
	
	private File xmlFile;
	private DocumentBuilder documentBuilder;

	public DOMPersonDAO(File xmlFile) throws PersistenceException {
		this.xmlFile = xmlFile;
		createDocumentBuilder();
	}

	private void createDocumentBuilder() throws PersistenceException {
		DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		documentFactory.setNamespaceAware(true);
		documentFactory.setIgnoringElementContentWhitespace(true);
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema;
		try {
			schema = schemaFactory.newSchema(new File(SCHEMA_LOCATION));
			documentFactory.setSchema(schema);
			documentFactory.setValidating(true);
			documentBuilder = documentFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new DefaultHandler());
		} catch (SAXException | ParserConfigurationException e) {
			e.printStackTrace();
			throw new PersistenceException("Internal error", e);
		}
	}

	@Override
	public void save(List<Person> persons) throws PersistenceException {
		try (OutputStream out = new FileOutputStream(xmlFile)){
			Document document = documentBuilder.newDocument();
			Element personsElement = createPersonsElement(document);
			document.appendChild(personsElement);
			addPersonElements(document, personsElement, persons);
			writeDocument(out, document);
		} catch (IOException e) {
			throw new PersistenceException("Cannot write to file " + xmlFile.getAbsolutePath(), e);
		}
	}

	private Element createPersonsElement(Document document) {
		Element personsElement = document.createElementNS(PERSON_NS_URI, "pd:Persons");
		personsElement.setAttributeNS(XMLConstants.XMLNS_ATTRIBUTE_NS_URI, "xmlns:xsi", XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI);
		personsElement.setAttributeNS(XMLConstants.W3C_XML_SCHEMA_INSTANCE_NS_URI, "xsi:schemaLocation", PERSON_NS_URI + " " + SCHEMA_LOCATION);
		return personsElement;
	}

	private void addPersonElements(Document document, Element personsElement, List<Person> persons) {
		for (Person person : persons) {
			Element personElement = document.createElementNS(PERSON_NS_URI, "pd:Person");
			personsElement.appendChild(personElement);
			
			Element nameElement = document.createElementNS(PERSON_NS_URI, "pd:Name");
			nameElement.appendChild(document.createTextNode(person.getLastname()));
			personElement.appendChild(nameElement);
			
			Element firstnameElement = document.createElementNS(PERSON_NS_URI, "pd:Firstname");
			firstnameElement.appendChild(document.createTextNode(person.getFirstname()));
			personElement.appendChild(firstnameElement);
			
			Element birthdateElement = document.createElementNS(PERSON_NS_URI, "pd:Birthdate");
			birthdateElement.appendChild(document.createTextNode(person.getBirthdate().toString()));
			personElement.appendChild(birthdateElement);
			
			Element maritalstatusElement = document.createElementNS(PERSON_NS_URI, "pd:Maritalstatus");
			maritalstatusElement.appendChild(document.createTextNode(person.getMaritalStatus().name()));
			personElement.appendChild(maritalstatusElement);
		}
	}

	private void writeDocument(OutputStream out, Document document) {
		DOMImplementationLS domImplementation = (DOMImplementationLS) document.getImplementation();
		LSSerializer serializer = domImplementation.createLSSerializer();
		serializer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
		LSOutput output = domImplementation.createLSOutput();
		output.setByteStream(out);
		serializer.write(document, output);
	}

	@Override
	public List<Person> load() throws PersistenceException {
		ArrayList<Person> persons = new ArrayList<>();
		try (InputStream xml = new FileInputStream(xmlFile)){
			Document dom = documentBuilder.parse(xml);
			NodeList personNodes = dom.getFirstChild().getChildNodes();
			readPersons(personNodes, persons);
		} catch (SAXException e) {
			throw new PersistenceException("Internal error", e);
		} catch (IOException e) {
			throw new PersistenceException("Cannot read file " + xmlFile.getAbsolutePath(), e);
		}
		return persons;
	}

	private void readPersons(NodeList personNodes, ArrayList<Person> persons) {
		for (int i = 0; i < personNodes.getLength(); i++) {
			if (personNodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
				NodeList personData = personNodes.item(i).getChildNodes();
				String name = personData.item(0).getTextContent();
				String firstname = personData.item(1).getTextContent();
				String birthdate = personData.item(2).getTextContent();
				String maritalstatus = personData.item(3).getTextContent();
				Person  person = new Person(firstname, name, LocalDate.parse(birthdate), MaritalStatus.valueOf(maritalstatus));
				persons.add(person);
			} else {
				continue;
			}
		}
	}
}
