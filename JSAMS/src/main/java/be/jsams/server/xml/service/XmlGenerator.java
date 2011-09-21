package be.jsams.server.xml.service;

/**
 * Generic service interface to generate a Xml as output.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface XmlGenerator<M> {

    /**
     * Generate a Xml file from a class type and an object.
     * 
     * @param clazz the class type
     * @param object the object to generate in Xml
     */
    public void generateXml(Class<M> clazz, M object);
    
}
