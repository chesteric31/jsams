package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.model.xml.deliveryOrder.DeliveryOrderXml;
import be.jsams.server.service.pdf.AbstractPdfService;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlDeliveryOrderGeneratorImpl;

/**
 * PDF delivery order service implementation.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfDeliveryOrderServiceImpl extends AbstractPdfService implements PdfService<DeliveryOrderBean> {

    private XmlDeliveryOrderGeneratorImpl xmlGenerator;

    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePdf(DeliveryOrderBean object, boolean viewReport) {
        DeliveryOrderXml deliveryOrderXml = xmlGenerator.generateXml(object);
        String path = getRootReportsPath() + getRecordPath();
        File generatedXmlFile = getXmlFileGenerator().generateXmlFile(deliveryOrderXml, path + ".xml",
                DeliveryOrderXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        return getMerger().merge(generatedXmlFile, getRecordPath(), reportFileName, outFileName, viewReport);
    }

    /**
     * @return the xmlGenerator
     */
    public XmlDeliveryOrderGeneratorImpl getXmlGenerator() {
        return xmlGenerator;
    }

    /**
     * @param xmlGenerator the xmlGenerator to set
     */
    public void setXmlGenerator(XmlDeliveryOrderGeneratorImpl xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

}
