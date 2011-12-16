package be.jsams.server.service.xml;

import java.io.File;

/**
 * Generic service interface to generate a XML file as output.
 * @param <X> the XML pojo
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface XmlFileGenerator<X> {

    /**
     * Generates a XML file from a XML pojo.
     * 
     * @param xml the XML pojo
     * @return the generate XML file
     */
    File generateXmlFile(X xml);
    
}
