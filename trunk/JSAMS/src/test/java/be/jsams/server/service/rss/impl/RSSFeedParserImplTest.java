package be.jsams.server.service.rss.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedParser;
import be.jsams.server.service.rss.model.MockRssGenerator;

/**
 * Test class for {@link RSSFeedParser} class.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class RSSFeedParserImplTest {

    private String pathName;
    
    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        pathName = MockRssGenerator.generateRss();
    }
    
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
        // RSSFeedParser parser = new
        // RSSFeedParserImpl("http://jsams.googlecode.com/files/Updates.rss");
        RSSFeedParser parser = new RSSFeedParserImpl("file:\\" + pathName);
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
