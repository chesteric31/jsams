package be.jsams.server.service.update.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import be.jsams.server.service.property.PropertyHolder;
import be.jsams.server.service.property.impl.PropertyHolderImpl;
import be.jsams.server.service.rss.RSSFeedParser;
import be.jsams.server.service.rss.impl.RSSFeedParserImpl;

/**
 * 
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DownloaderServiceImplTest {

    /**
     * Test method for
     * {@link be.jsams.server.service.update.impl.DownloaderServiceImpl#retrieveAvailableUpdateVersion()}
     * .
     */
    @Test
    public void testRetrieveAvailableUpdateVersion() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.update.impl.DownloaderServiceImpl#downloadAvailableUpdateFile(java.lang.String)}
     * .
     */
    @Test
    public void testRetrieveAvailableUpdateFile() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link be.jsams.server.service.update.impl.DownloaderServiceImpl#retrieveHostStringForUpdates()}
     * .
     * @throws IOException 
     */
    @Test
    public void testRetrieveHostStringForUpdates() throws IOException {
        DownloaderServiceImpl downloaderService = new DownloaderServiceImpl();
        PropertyHolder propertyHolder = new PropertyHolderImpl();
        downloaderService.setPropertyHolder(propertyHolder);
        String os = System.getProperty("os.name").toLowerCase();
        String rssFileName = null;
        // windows
        if (os.indexOf("win") >= 0) {
            rssFileName = "file:\\";
        } else if ((os.indexOf("mac") >= 0) || (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0)) {
            rssFileName = "file://";
        }
        File path = new File(".");
        String separator = File.separator;
        rssFileName += path.getCanonicalPath() + separator + "src" + separator + "test" + separator
                + "resources" + separator + "Updates.rss";
        RSSFeedParser rssFeedParser = new RSSFeedParserImpl(rssFileName);
        downloaderService.setRssFeedParser(rssFeedParser);
        List<String> stringForUpdates = downloaderService.retrieveHostStringForUpdates();
        assertFalse(stringForUpdates.isEmpty());
    }

}
