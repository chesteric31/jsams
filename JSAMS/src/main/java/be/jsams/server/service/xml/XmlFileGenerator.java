package be.jsams.server.service.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Generator of XML physical file from a XML JAXB object, a class type.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlFileGenerator {

    /**
     * Generates a file with the XML and class type input.
     * 
     * @param xml the JAXB object
     * @param xmlFilePath the file path for the built XML
     * @param classType the class type
     * @return the built XML file
     */
    public File generateXmlFile(Object xml, String xmlFilePath, Class<?> classType) {
        File output = null;
        try {
            output = new File(xmlFilePath);
            JAXBContext context = JAXBContext.newInstance(classType);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xml, output);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return output;
    }

}
