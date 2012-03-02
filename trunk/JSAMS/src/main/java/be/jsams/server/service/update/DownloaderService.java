package be.jsams.server.service.update;

import java.io.File;
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
     * Returns the downloaded last available version of the JSAMS application.
     * 
     * @param host the host URL to use for download
     * @return the downloaded last available version of the JSAMS application.
     */
    File retrieveAvailableUpdateFile(String host);
    
    /**
     * Retrieves a list of string host to use for the download of updates files.
     * 
     * @return a list of string host
     */
    List<String> retrieveHostStringForUpdates();
    
}
