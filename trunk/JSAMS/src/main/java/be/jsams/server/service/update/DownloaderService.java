package be.jsams.server.service.update;

import java.util.List;

/**
 * Downloader service, to update/check the updates onto the net.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface DownloaderService {
    
    /**
     * Returns the last available version of the JSAMS application.
     * 
     * @return the last available version of the JSAMS application
     */
    String retrieveAvailableUpdateVersion();
    
    /**
     * Retrieves the downloaded update file of the JSAMS application.
     * 
     * @param host the host URL to use for download
     * 
     * @return a path name of download update jar
     */
    String downloadAvailableUpdateFile(String host);
    
    /**
     * Retrieves a list of string host to use for the download of updates files.
     * 
     * @return a list of string host
     */
    List<String> retrieveHostStringForUpdates();

    /**
     * Downloads update files into current directory.
     * 
     * @return a list of path names of download update jar
     */
    List<String> downloadUpdate();
    
}
