package be.jsams.server.service.xml;

/**
 * Generic service interface to generate a XML as output.
 * 
 * @param <M> the model object
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface XmlGenerator<M> {

    /**
     * Generate a Xml file from a class type and an object.
     * 
     * @param object the object to generate in Xml
     */
    void generateXml(M object);

}
