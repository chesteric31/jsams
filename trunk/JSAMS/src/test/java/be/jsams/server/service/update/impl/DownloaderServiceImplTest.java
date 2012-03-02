package be.jsams.server.service.update.impl;

import static org.junit.Assert.fail;

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
     * Test method for {@link be.jsams.server.service.update.impl.DownloaderServiceImpl#retrieveAvailableUpdateVersion()}.
     */
    @Test
    public void testRetrieveAvailableUpdateVersion() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.update.impl.DownloaderServiceImpl#downloadAvailableUpdateFile(java.lang.String)}.
     */
    @Test
    public void testRetrieveAvailableUpdateFile() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link be.jsams.server.service.update.impl.DownloaderServiceImpl#retrieveHostStringForUpdates()}.
     */
    @Test
    public void testRetrieveHostStringForUpdates() {
        DownloaderServiceImpl downloaderService = new DownloaderServiceImpl();
        PropertyHolder propertyHolder = new PropertyHolderImpl();
        downloaderService.setPropertyHolder(propertyHolder);
        RSSFeedParser rssFeedParser = new RSSFeedParserImpl(
                "file:///home//chesteric31//Dev//workspace-jsams//JSAMS//updates.rss");
        downloaderService.setRssFeedParser(rssFeedParser);
        List<String> stringForUpdates = downloaderService.retrieveHostStringForUpdates();
        for (String string: stringForUpdates) {
            System.out.println(string);
        }
    }

}
