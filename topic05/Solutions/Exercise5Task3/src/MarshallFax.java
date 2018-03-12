import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import ch.bfh.fax.Fax;
import ch.bfh.fax.FromTo;
import ch.bfh.fax.Header;
import ch.bfh.fax.ObjectFactory;
import ch.bfh.fax.Priority;

public class MarshallFax {
	private final static String PACKAGE = "ch.bfh.fax";
	private final static String XML_FILE = "faxCreated.xml";

	public static void main(String[] l) {

		// First we need a factory object to get basic access!
		ObjectFactory objectFactory = new ObjectFactory();

		// create From
		FromTo from = objectFactory.createFromTo();
		from.setName("MyCompany");
		from.setFaxno("012 345 67 89");

		// create To
		FromTo to = objectFactory.createFromTo();
		to.setName("YourCompany");
		to.setFaxno("098 765 43 21");

		// Create Header
		Header header = objectFactory.createHeader();
		header.setFrom(from);
		header.setTo(to);
		header.setPages(1);
		header.setPriority(Priority.URGENT);

		Fax fax = objectFactory.createFax();
		fax.setHeader(header);
		fax.setBody("This is the message in the body.");

		// Now we want to marshal the content of persons to an XML file
		try {
			// We need a JAXBElement of our type (this represents our datas to
			// be written)
			// JAXBElement<Fax> dataContent = objectFactory.createFax();
			// We need a JAXBContex which knows our package (generated code)
			JAXBContext context = JAXBContext.newInstance(PACKAGE);
			// The output XML file
			File file = new File(XML_FILE);
			// Use the context to receive a marshaller object
			Marshaller m = context.createMarshaller();
			// set property if you want a pretty printed XML file
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(fax, file); // Create the file
			m.marshal(fax, System.out); // Print it on console too

		} catch (JAXBException jbe) {
			jbe.printStackTrace();
		}
	}
}
