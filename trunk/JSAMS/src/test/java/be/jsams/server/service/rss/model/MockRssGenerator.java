package be.jsams.server.service.rss.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.rss.RSSFeedWriter;
import be.jsams.server.service.rss.impl.RSSFeedWriterImpl;

/**
 * Generator of RSS fluxes.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public final class MockRssGenerator {

    /**
     * Default private constructor for utility class.
     */
    private MockRssGenerator() {
    }

    /**
     * Generates mock RSS flux file.
     * 
     * @return the path name to the generated RSS flux file 
     */
    public static String generateRss() {
        String pathName = "";
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

        // Now add one example entry
        FeedMessage feedOld = new FeedMessage();
        feedOld.setTitle("JSAMS update");
        feedOld.setDescription("This is a description");
        feedOld.setAuthor("chesteric31");
        feedOld.setGuid("null");
        feedOld.setLink("null");
        cal.set(2011, 11, 25);
        feedOld.setReleaseDate(simpleDateFormat.format(cal.getTime()));
        String version = "1.0.1-SNAPSHOT";
        feedOld.setVersion(version);
        rssFeeder.getMessages().add(feedOld);
        for (int i = 2; i <= 4; i++) {
            // Now add one example entry
            FeedMessage feed = new FeedMessage();
            feed.setTitle("JSAMS update");
            feed.setDescription("This is a description");
            feed.setAuthor("chesteric31");
            String guidLink = "http://jsams.googlecode.com/files/JSAMS-1.0." + i + "-SNAPSHOT-updater.jar";
            feed.setGuid(guidLink);
            feed.setLink(guidLink);
            feed.setReleaseDate(releaseDate);
            feed.setVersion("1.0." + i + "-SNAPSHOT");
            rssFeeder.getMessages().add(feed);
        }

        // Now writes the file
        try {
            File path = new File(".");
            String separator = File.separator;
            pathName = path.getCanonicalPath() + separator + "src" + separator + "test" + separator
                    + "resources" + separator + "Updates.rss";
            RSSFeedWriter writer = new RSSFeedWriterImpl(rssFeeder, pathName);
            writer.writeFeed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathName;
    }

}
