package be.jsams.server.service.rss.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedParser;

/**
 * RSS feed parse implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class RSSFeedParserImpl implements RSSFeedParser {

    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String LINK = "link";
    private static final String AUTHOR = "author";
    private static final String ITEM = "item";
    private static final String RELEASE_DATE = "releaseDate";
    private static final String VERSION = "version";
    private static final String GUID = "guid";

    private URL url;

    /**
     * Constructor.
     * 
     * @param feedUrl the feed URL
     */
    public RSSFeedParserImpl(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Feed readFeed() {
        Feed feed = null;
        try {

            boolean isFeedHeader = true;
            // Set header values initial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String author = "";
            String releaseDate = "";
            String version = "";
            String guid = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement asStartElement = event.asStartElement();
                    String localPart = asStartElement.getName().getLocalPart();
                    if (ITEM.equals(localPart)) {
                        if (isFeedHeader) {
                            isFeedHeader = false;
                            feed = new Feed(title, description, link, author);
                        }
                        event = eventReader.nextEvent();
                        continue;
                    }

                    if (TITLE.equals(localPart)) {
                        event = eventReader.nextEvent();
                        title = event.asCharacters().getData();
                        continue;
                    }
                    if (DESCRIPTION.equals(localPart)) {
                        event = eventReader.nextEvent();
                        description = event.asCharacters().getData();
                        continue;
                    }

                    if (LINK.equals(localPart)) {
                        event = eventReader.nextEvent();
                        link = event.asCharacters().getData();
                        continue;
                    }

                    if (VERSION.equals(localPart)) {
                        event = eventReader.nextEvent();
                        version = event.asCharacters().getData();
                        continue;
                    }
                    if (RELEASE_DATE.equals(localPart)) {
                        event = eventReader.nextEvent();
                        releaseDate = event.asCharacters().getData();
                        continue;
                    }
                    if (AUTHOR.equals(localPart)) {
                        event = eventReader.nextEvent();
                        author = event.asCharacters().getData();
                        continue;
                    }
                    if (GUID.equals(localPart)) {
                        event = eventReader.nextEvent();
                        guid = event.asCharacters().getData();
                        continue;
                    }
                } else if (event.isEndElement()) {
                    String localPart = event.asEndElement().getName().getLocalPart();
                    if (ITEM.equals(localPart)) {
                        FeedMessage message = new FeedMessage();
                        message.setAuthor(author);
                        message.setDescription(description);
                        message.setGuid(guid);
                        message.setLink(link);
                        message.setTitle(title);
                        message.setReleaseDate(releaseDate);
                        message.setVersion(version);
                        feed.getMessages().add(message);
                        event = eventReader.nextEvent();
                        continue;
                    }
                }
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    /**
     * Reads the {@link InputStream}.
     * 
     * @return the read {@link InputStream}
     */
    private InputStream read() {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
