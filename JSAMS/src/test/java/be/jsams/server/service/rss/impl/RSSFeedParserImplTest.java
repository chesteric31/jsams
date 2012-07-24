package be.jsams.server.service.rss.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedParser;

/**
 * Test class for {@link RSSFeedParser} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class RSSFeedParserImplTest {

    /**
     * Test method for
     * {@link be.jsams.server.service.rss.impl.RSSFeedParserImpl#readFeed()}.
     * 
     * @throws IOException an {@link IOException}
     */
    @Test
    public void testReadFeed() throws IOException {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", "10.16.0.25");
        System.getProperties().put("proxyPort", "8080");
        File path = new File(".");
        String separator = File.separator;
        String pathString = path.getCanonicalPath() + separator + "src" + separator + "test" + separator + "resources"
                + separator;
        // RSSFeedParser parser = new
        // RSSFeedParserImpl("http://jsams.googlecode.com/files/Updates.rss");
        RSSFeedParser parser = new RSSFeedParserImpl("file:\\" + pathString + "Updates.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        List<FeedMessage> messages = feed.getMessages();
        for (FeedMessage message : messages) {
            System.out.println(message);
            assertEquals("chesteric31", message.getAuthor());
        }
        assertFalse(messages.isEmpty());
    }

}
