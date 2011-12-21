package be.jsams.server.service.xml.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import be.jsams.server.model.xml.estimate.EstimateXml;
import be.jsams.server.service.xml.XmlFileGenerator;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlFileEstimateGeneratorImpl implements XmlFileGenerator<EstimateXml> {

    /**
     * {@inheritDoc}
     */
    @Override
    public File generateXmlFile(EstimateXml xml) {
        File output = null;
        try {
            output = new File("reports/estimate/estimate.xml");
            JAXBContext context = JAXBContext.newInstance(EstimateXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(xml, output);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
        return output;
    }

}
