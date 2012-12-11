package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.model.xml.estimate.EstimateXml;
import be.jsams.server.service.pdf.AbstractPdfService;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlEstimateGeneratorImpl;

/**
 * PDF service implementation for an {@link EstimateBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PdfEstimateServiceImpl extends AbstractPdfService implements PdfService<EstimateBean> {

    private XmlEstimateGeneratorImpl xmlGenerator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(EstimateBean object) {
        EstimateXml estimateXml = xmlGenerator.generateXml(object);
        String path = getRootReportsPath() + getRecordPath();
        File generatedXmlFile = getXmlFileGenerator().generateXmlFile(estimateXml, path + ".xml", EstimateXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        getMerger().merge(generatedXmlFile, getRecordPath(), reportFileName, outFileName);
    }

    /**
     * @return the xmlGenerator
     */
    public XmlEstimateGeneratorImpl getXmlGenerator() {
        return xmlGenerator;
    }

    /**
     * @param xmlGenerator the xmlGenerator to set
     */
    public void setXmlGenerator(XmlEstimateGeneratorImpl xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

}
