package be.jsams.server.service.xml;

/**
 * Generic service interface to generate a XML pojo as output.
 * 
 * @param <M> the model object
 * @param <X> the XML pojo type
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface XmlGenerator<M, X> {

    /**
     * Generates a XML pojo from a a bean object.
     * 
     * @param object the object to generate in XML pojo
     * @return the generated XML pojo
     */
    X generateXml(M object);

}
