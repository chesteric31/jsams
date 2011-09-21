package be.jsams.server.xml.service.impl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import be.jsams.server.xml.estimate.EstimateXml;
import be.jsams.server.xml.service.XmlGenerator;

/**
 * Service to generate a Xml from a {@link EstimateXml} object.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class XmlEstimateGeneratorImpl implements XmlGenerator<EstimateXml> {

    /**
     * {@inheritDoc}
     */
    @Override
    public void generateXml(Class<EstimateXml> clazz, EstimateXml object) {
        try {
            JAXBContext context = JAXBContext.newInstance(clazz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(object, System.out);
        } catch (JAXBException ex) {
            ex.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        ObjectFactory factory = new ObjectFactory();
//        EstimateXml xml = factory.createEstimate();
//        xml.setCreationDate(new Date());
//        new XmlEstimateGeneratorImpl().generateXml(EstimateXml.class, xml);
//    }

}
