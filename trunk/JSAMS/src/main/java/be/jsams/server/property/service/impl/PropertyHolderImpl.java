package be.jsams.server.property.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.property.service.PropertyHolder;

/**
 * Implementation for the property holder service.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PropertyHolderImpl implements PropertyHolder {

    private static final Log LOGGER = LogFactory.getLog(PropertyHolderImpl.class);

    /**
     * JSAMS application version properties
     */
    public static final String JSAMS_APPLICATION_VERSION_PROPERTY = "jsams-application-version.properties";

    /**
     * JSAMS application internal version identifier
     */
    public static final String APPLICATION_INTERNALVERSION_IDENTIFIER = "application.internalversion.identifier";

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInstalledVersion() {
        String result = "";
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties properties = new Properties();
        try {
            properties.load(classLoader.getResourceAsStream(JSAMS_APPLICATION_VERSION_PROPERTY));
            result = String.valueOf(properties.get(APPLICATION_INTERNALVERSION_IDENTIFIER));
        } catch (FileNotFoundException e) {
            LOGGER.error(e);
        } catch (IOException e) {
            LOGGER.error(e);
        }
        return result;
    }

}
