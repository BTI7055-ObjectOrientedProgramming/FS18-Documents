package bookCatalog;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Class that prints out the main StAX events
 * @author lua1
 */
public class StAXEventReader2 {

	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {

		// First, create a new XMLInputFactory
		XMLInputFactory inputFactory = XMLInputFactory.newInstance();

		// Setup a new eventReader
		InputStream in = new FileInputStream("BookCatalogue.xml");
		XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

		int nbr = 1;
		// read the entire XML document - loop over all events
		while (eventReader.hasNext()) {
			// get next event
			XMLEvent event = eventReader.nextEvent();
			System.out.print("# " + (nbr++) + " : ");
			if (event.isStartDocument()) {
				System.out.print("StartDocument");
			} else if (event.isStartElement()) {
				System.out.print("StartElement:");
				StartElement s = event.asStartElement();
				System.out.print(" qname=" + s.getName().getLocalPart()+":"+s.getName().getNamespaceURI());
				Iterator<Attribute> attributes = s.getAttributes();
				System.out.print(" attributes=");
				if (!attributes.hasNext()) {
					System.out.print("null");
				} else {
					while (attributes.hasNext()) {
						System.out.print(attributes.next().getName()+", "); 				
					}
				}
			} else if (event.isEndElement()) {
				System.out.print("EndElement:");				
				EndElement s = event.asEndElement();
				System.out.print(" qname=" + s.getName().getLocalPart());
			} else if (event.isEndDocument()) {
				System.out.print("EndDocument");				
			} else if (event.isCharacters()) {
				System.out.print("Characters: ");
				Characters c = event.asCharacters();
				System.out.print("isCDate=" + c.isCData());
				if (!c.isWhiteSpace()) {
					System.out.print(" data=\"" + c.getData() + "\"");
				}	
				System.out.print(" isWhiteSpace=" + c.isWhiteSpace());
			} else {
				System.out.print(event.getEventType());
			}
			System.out.printf("%n");
		}

	}

}
