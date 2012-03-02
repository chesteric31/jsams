package be.jsams.server.service.property;

/**
 * Service that manages the read/write of properties into properties files.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface PropertyHolder {
    
    /**
     * Returns the installed version following the property value into
     * jsams-application-version.properties.
     * 
     * @return the installed version
     */
    String retrieveInstalledVersion();

}
