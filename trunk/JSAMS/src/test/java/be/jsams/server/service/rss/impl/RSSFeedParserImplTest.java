package be.jsams.server.service.rss.impl;

import org.junit.Test;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedParser;

/**
 * Test class for {@link RSSFeedParserImpl}.
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
        RSSFeedParser parser = new RSSFeedParserImpl("http://jsams.googlecode.com/files/updates.rss");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }
    }

}