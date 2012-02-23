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
     * Test method for
     * {@link be.jsams.server.service.rss.impl.RSSFeedWriterImpl#writeFeed()}.
     */
    @Test
    public void testWriteFeed() {
        // System.getProperties().put("proxySet", "true");
        // System.getProperties().put("proxyHost", "10.16.0.25");
        // System.getProperties().put("proxyPort", "8080");
        // Create the rss feed
        String author = "chesteric31";
        String title = "JSAMS";
        String description = "Java Simplified Accounting Management System";
        String link = "http://code.google.com/p/jsams/";
        Calendar cal = new GregorianCalendar();
        Date creationDate = cal.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
        String releaseDate = simpleDateFormat.format(creationDate);
        Feed rssFeeder = new Feed(title, description, link, author);

        for (int i = 2; i <= 3; i++) {
            // Now add one example entry
            FeedMessage feed = new FeedMessage();
            feed.setTitle("JSAMS update");
            feed.setDescription("This is a description");
            feed.setAuthor("chesteric31");
            feed.setGuid("http://jsams.googlecode.com/files/JSAMS-1.0." + i + "-SNAPSHOT-update.jar");
            feed.setLink("http://jsams.googlecode.com/files/JSAMS-1.0." + i + "-SNAPSHOT-update.jar");
            feed.setReleaseDate(releaseDate);
            feed.setVersion("1.0." + i + "-SNAPSHOT");
            rssFeeder.getMessages().add(feed);
        }

        // Now write the file
        RSSFeedWriter writer = new RSSFeedWriterImpl(rssFeeder, "updates.rss");
        try {
            writer.writeFeed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
