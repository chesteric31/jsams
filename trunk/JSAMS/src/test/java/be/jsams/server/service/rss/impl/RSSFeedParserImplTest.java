package be.jsams.server.service.rss.impl;

import static org.junit.Assert.assertFalse;

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
     */
    @Test
    public void testReadFeed() {
        System.getProperties().put("proxySet", "true");
        System.getProperties().put("proxyHost", "10.16.0.25");
        System.getProperties().put("proxyPort", "8080");
        RSSFeedParser parser = new RSSFeedParserImpl("http://jsams.googlecode.com/files/Updates.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        List<FeedMessage> messages = feed.getMessages();
        for (FeedMessage message : messages) {
            System.out.println(message);
        }
        assertFalse(messages.isEmpty());
    }

}
