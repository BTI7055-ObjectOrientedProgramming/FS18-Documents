import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import ch.bfh.fax.Fax;

public final class UnMarshallFax {

	private final static String PACKAGE = "ch.bfh.fax";
	private final static String XSD_FILE = "schema/fax.xsd";
	private final static String XML_FILE = "faxCreated.xml";

	public static void main(String[] l) {

		// First of all: Validate the XML file against it's schema
		if (!XMLValidation.validateXMLSchema(XSD_FILE, XML_FILE)) {
			System.out.println("THE XML file " + XML_FILE + " is not valid against " + XSD_FILE);
			System.exit(0);
		} else
			System.out.println("THE XML file " + XML_FILE + " is valid against " + XSD_FILE);

		try {
			// Get the context
			JAXBContext context = JAXBContext.newInstance(PACKAGE);

			// Get an unmarshaller object from the context
			Unmarshaller unmarshaller = context.createUnmarshaller();

			// Define a source (=our XML file)
			Source source = new StreamSource(new java.io.FileInputStream(XML_FILE));

			// UnMarshall the XML content and get access to the root element
			JAXBElement<Fax> root = unmarshaller.unmarshal(source, Fax.class);

			// Our root element can deliver a fax object
			Fax fax = root.getValue();

			System.out.println("READING the message:");

			System.out.println(fax.getBody());

		} catch (JAXBException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
