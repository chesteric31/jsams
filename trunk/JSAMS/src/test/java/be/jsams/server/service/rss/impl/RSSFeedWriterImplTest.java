package be.jsams.server.service.rss.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedWriter;

/**
 * Test class for {@link RSSFeedWriterImpl}.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class RSSFeedWriterImplTest {

    /**
     * Test method for {@link be.jsams.server.service.rss.impl.RSSFeedWriterImpl#writeFeed()}.
     */
    @Test
    public void testWriteFeed() {
        // Create the rss feed
        String author = "chesteric31";
        String title = "JSAMS";
        String description = "Java Simplified Accouting Management System";
        String version = "1.0.2-SNAPSHOT";
        String link = "http://code.google.com/feeds/p/jsams/updates/";
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat date_format = new SimpleDateFormat(
                "EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String releaseDate = date_format.format(creationDate);
        Feed rssFeeder = new Feed(title, description, link, author);

        // Now add one example entry
        FeedMessage feed = new FeedMessage();
        feed.setTitle("JSAMS update");
        feed.setDescription("This is a description");
        feed.setAuthor("chesteric31");
        feed.setGuid("http://code.google.com/feeds/p/jsams/updates/");
        feed.setLink("http://code.google.com/feeds/p/jsams/updates/");
        feed.setReleaseDate(releaseDate);
        feed.setVersion(version);
        rssFeeder.getMessages().add(feed);

        // Now write the file
        RSSFeedWriter writer = new RSSFeedWriterImpl(rssFeeder, "updates.rss");
        try {
            writer.writeFeed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
