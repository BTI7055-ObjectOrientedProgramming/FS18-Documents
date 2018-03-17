package rss_solution;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

public final class RSSFeedParser {

	
	// These are labels for our elements
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	
	final URL url;

	public RSSFeedParser(String feedUrl) {
		try {this.url = new URL(feedUrl);}
		catch (MalformedURLException e) {throw new RuntimeException(e);}
	}

	public RSSFeedParser(URL url) {this.url = url;}

	public Feed readFeed() {
		Feed feed = null;
		try {
			boolean isFeedHeader = true; // each feed starts with a header

			// Set header values initially to the empty string
			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String pubdate = "";
			

			// First create a new XMLInputFactory
			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			// Setup a new eventReader
			InputStream in = initInputStream();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);

			// read the XML document
			while (eventReader.hasNext()) {

				XMLEvent event = eventReader.nextEvent();

				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();

					switch (localPart) {
					case ITEM:
						if (isFeedHeader) {
							isFeedHeader = false; // we have read the header
							feed = new Feed(title, link, description, language,copyright, pubdate);
							// now the header is treated
						}
						// go to next event
						event = eventReader.nextEvent();
						break;
					case TITLE:
						title = getCharacterData(event, eventReader);
						break;
					case DESCRIPTION:
						description = getCharacterData(event, eventReader);
						break;
					case LINK:
						if (event.asStartElement().getName().getPrefix().isEmpty()) {	
							link = getCharacterData(event, eventReader);
						}
						break;
					case LANGUAGE:
						language = getCharacterData(event, eventReader);
						break;
					case PUB_DATE:
						pubdate = getCharacterData(event, eventReader);
						break;
					case COPYRIGHT:
						copyright = getCharacterData(event, eventReader);
						break;
					}
				} else if (event.isEndElement()) {
					if (event.asEndElement().getName().getLocalPart() == (ITEM)) {
						FeedMessage message = new FeedMessage();
						message.setDescription(description);
						message.setLink(link);
						message.setTitle(title);
						feed.getMessages().add(message);
						event = eventReader.nextEvent();
						continue;
					}
				}
			}
		}
		catch (XMLStreamException e) {throw new RuntimeException(e);}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
			throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if (event instanceof Characters) result = event.asCharacters().getData();
		return result;
	}

	private InputStream initInputStream() {
		try{return url.openStream();}
		catch (IOException e) {throw new RuntimeException(e);}
	}
}