package be.jsams.server.service.rss.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartDocument;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedWriter;

/**
 * RSS feed writer implementation.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class RSSFeedWriterImpl implements RSSFeedWriter {

    private String outputFile;
    private Feed rssfeed;

    /**
     * Constructor.
     * 
     * @param rssfeed the {@link Feed}
     * @param outputFile the output file
     */
    public RSSFeedWriterImpl(Feed rssfeed, String outputFile) {
        this.rssfeed = rssfeed;
        this.outputFile = outputFile;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void writeFeed() {
        // Create a XMLOutputFactory
        XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
        // Create XMLEventWriter
        XMLEventWriter eventWriter;
        try {
            eventWriter = outputFactory.createXMLEventWriter(new FileOutputStream(outputFile));
            // Create a EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // Create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            // Create open tag
            eventWriter.add(end);

            StartElement rssStart = eventFactory.createStartElement("", "", "rss");
            eventWriter.add(rssStart);
            eventWriter.add(eventFactory.createAttribute("version", "2.0"));
            eventWriter.add(end);

            // Write the different nodes

            createNode(eventWriter, "title", rssfeed.getTitle());
            createNode(eventWriter, "link", rssfeed.getLink());
            createNode(eventWriter, "description", rssfeed.getDescription());
            createNode(eventWriter, "author", rssfeed.getAuthor());

            for (FeedMessage entry : rssfeed.getMessages()) {
                eventWriter.add(eventFactory.createStartElement("", "", "item"));
                eventWriter.add(end);
                createNode(eventWriter, "title", entry.getTitle());
                createNode(eventWriter, "description", entry.getDescription());
                createNode(eventWriter, "link", entry.getLink());
                createNode(eventWriter, "author", entry.getAuthor());
                createNode(eventWriter, "guid", entry.getGuid());
                createNode(eventWriter, "version", entry.getVersion());
                createNode(eventWriter, "author", entry.getAuthor());
                createNode(eventWriter, "releaseDate", entry.getReleaseDate());
                eventWriter.add(eventFactory.createEndElement("", "", "item"));
                eventWriter.add(end);
            }
            eventWriter.add(eventFactory.createEndElement("", "", "rss"));
            eventWriter.add(end);

            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates node.
     * 
     * @param eventWriter the {@link XMLEventWriter}
     * @param name the name
     * @param value the value
     * @throws XMLStreamException a possible {@link XMLStreamException}
     */
    private void createNode(XMLEventWriter eventWriter, String name, String value) throws XMLStreamException {
        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // Create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // Create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // Create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }

}
