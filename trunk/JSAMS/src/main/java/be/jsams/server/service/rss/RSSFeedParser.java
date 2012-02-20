package be.jsams.server.service.rss;

import be.jsams.server.model.rss.Feed;

/**
 * RSS feed parse service.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface RSSFeedParser {

    /**
     * Reads the feed.
     * 
     * @return the read feed
     */
    Feed readFeed();

}
