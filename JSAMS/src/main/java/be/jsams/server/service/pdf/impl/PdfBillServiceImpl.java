package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.model.xml.bill.BillXml;
import be.jsams.server.service.pdf.AbstractPdfService;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlBillGeneratorImpl;

/**
 * PDF bill service implementation.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfBillServiceImpl extends AbstractPdfService implements PdfService<BillBean> {

    private XmlBillGeneratorImpl xmlGenerator;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String generatePdf(BillBean object, boolean viewReport) {
        BillXml billXml = xmlGenerator.generateXml(object);
        String path = getRootReportsPath() + getRecordPath();
        File generatedXmlFile = getXmlFileGenerator().generateXmlFile(billXml, path + ".xml", BillXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        return getMerger().merge(generatedXmlFile, getRecordPath(), reportFileName, outFileName, viewReport);
    }

    /**
     * @return the xmlGenerator
     */
    public XmlBillGeneratorImpl getXmlGenerator() {
        return xmlGenerator;
    }

    /**
     * @param xmlGenerator the xmlGenerator to set
     */
    public void setXmlGenerator(XmlBillGeneratorImpl xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

}
