package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.model.xml.estimate.EstimateXml;
import be.jsams.server.service.pdf.PdfMerger;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.XmlFileGenerator;
import be.jsams.server.service.xml.impl.XmlEstimateGeneratorImpl;

/**
 * PDF service implementation for an {@link EstimateBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PdfEstimateServiceImpl implements PdfService<EstimateBean> {

    private XmlEstimateGeneratorImpl xmlGenerator;
    private XmlFileGenerator xmlFileGenerator;
    private PdfMerger merger;
    private String rootReportsPath;
    private String recordPath;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(EstimateBean object) {
        EstimateXml estimateXml = xmlGenerator.generateXml(object);
        String path = rootReportsPath + recordPath;
        File generatedXmlFile = xmlFileGenerator.generateXmlFile(estimateXml, path + ".xml", EstimateBean.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        merger.merge(generatedXmlFile, recordPath, reportFileName, outFileName);
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

    /**
     * @return the XmlFileGenerator
     */
    public XmlFileGenerator getXmlFileGenerator() {
        return xmlFileGenerator;
    }

    /**
     * @param xmlFileGenerator the XmlFileGenerator to set
     */
    public void setXmlFileGenerator(XmlFileGenerator xmlFileGenerator) {
        this.xmlFileGenerator = xmlFileGenerator;
    }

    /**
     * @return the merger
     */
    public PdfMerger getMerger() {
        return merger;
    }

    /**
     * @param merger the merger to set
     */
    public void setMerger(PdfMerger merger) {
        this.merger = merger;
    }

    /**
     * @return the rootReportsPath
     */
    public String getRootReportsPath() {
        return rootReportsPath;
    }

    /**
     * @param rootReportsPath the rootReportsPath to set
     */
    public void setRootReportsPath(String rootReportsPath) {
        this.rootReportsPath = rootReportsPath;
    }

    /**
     * @return the recordPath
     */
    public String getRecordPath() {
        return recordPath;
    }

    /**
     * @param recordPath the recordPath to set
     */
    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath;
    }

}
