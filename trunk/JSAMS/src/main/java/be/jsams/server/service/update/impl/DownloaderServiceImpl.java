package be.jsams.server.service.update.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.prefs.Preferences;

import be.jsams.common.util.JsamsConstants;
import be.jsams.server.model.rss.Feed;
import be.jsams.server.model.rss.FeedMessage;
import be.jsams.server.service.property.PropertyHolder;
import be.jsams.server.service.rss.RSSFeedParser;
import be.jsams.server.service.update.DownloaderService;

/**
 * Downloader service, to update/check the updates onto the net.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DownloaderServiceImpl implements DownloaderService {

    private RSSFeedParser rssFeedParser;
    private PropertyHolder propertyHolder;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String retrieveAvailableUpdateVersion() {
        String version = "";
        Preferences userRootPrefs = Preferences.userRoot();
        Preferences jsamsPrefs = userRootPrefs.node(JsamsConstants.PACKAGE_ROOT_NAME);

        Properties systemProperties = System.getProperties();
        systemProperties.put(JsamsConstants.PROXY_TO_SET, jsamsPrefs.get(JsamsConstants.PROXY_TO_SET, "false"));
        systemProperties.put(JsamsConstants.PROXY_HOST, jsamsPrefs.get(JsamsConstants.PROXY_HOST, ""));
        systemProperties.put(JsamsConstants.PROXY_PORT, jsamsPrefs.get(JsamsConstants.PROXY_PORT, ""));
        Feed feed = rssFeedParser.readFeed();
        LinkedList<FeedMessage> feedMessages = new LinkedList<FeedMessage>(feed.getMessages());
        version = feedMessages.getLast().getVersion();
        return version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String downloadAvailableUpdateFile(String host) {
        if (host == null || host.isEmpty()) {
            throw new IllegalArgumentException("Empty URL or file.");
        }
        InputStream input = null;
        FileOutputStream writeFile = null;
        String fileName = null;
        try {
            URL url = new URL(host);
            URLConnection connection = url.openConnection();
            int fileLength = connection.getContentLength();

            if (fileLength == -1) {
                throw new IllegalArgumentException("Invalid URL or file.");
            }

            input = connection.getInputStream();
            fileName = url.getFile().substring(url.getFile().lastIndexOf('/') + 1);
            writeFile = new FileOutputStream(fileName);
            byte[] buffer = new byte[1024];
            int read;
            do {
                read = input.read(buffer);
                writeFile.write(buffer, 0, read);
            } while (read > 0);
            writeFile.flush();
        } catch (IOException e) {
            throw new IllegalArgumentException("Error while trying to download the file.");
        } finally {
            try {
                writeFile.close();
                input.close();
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return fileName;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> retrieveHostStringForUpdates() {
        List<String> hostForUpdates = new ArrayList<String>();
        String installedVersion = propertyHolder.retrieveInstalledVersion();
        LinkedList<FeedMessage> feedMessages = new LinkedList<FeedMessage>(rssFeedParser.readFeed().getMessages());
        boolean toKeep = false;
        for (FeedMessage feedMessage : feedMessages) {
            String version = feedMessage.getVersion();
            if (toKeep) {
                hostForUpdates.add(feedMessage.getLink());
            }
            if (version.equals(installedVersion)) {
                toKeep = true;
                // the next one will be to keep its link
            }
        }
        return hostForUpdates;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> downloadUpdate() {
        List<String> pathNamesToUpdateJar = new ArrayList<String>();
        List<String> forUpdates = retrieveHostStringForUpdates();
        for (String update : forUpdates) {
            pathNamesToUpdateJar.add(downloadAvailableUpdateFile(update));
        }
        return pathNamesToUpdateJar;
    }

    /**
     * @return the rssFeedParser
     */
    public RSSFeedParser getRssFeedParser() {
        return rssFeedParser;
    }

    /**
     * @param rssFeedParser the rssFeedParser to set
     */
    public void setRssFeedParser(RSSFeedParser rssFeedParser) {
        this.rssFeedParser = rssFeedParser;
    }

    /**
     * @return the propertyHolder
     */
    public PropertyHolder getPropertyHolder() {
        return propertyHolder;
    }

    /**
     * @param propertyHolder the propertyHolder to set
     */
    public void setPropertyHolder(PropertyHolder propertyHolder) {
        this.propertyHolder = propertyHolder;
    }

}
