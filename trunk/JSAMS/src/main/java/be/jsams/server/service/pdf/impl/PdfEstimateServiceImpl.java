package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.model.xml.estimate.EstimateXml;
import be.jsams.server.service.pdf.PdfMerger;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlEstimateGeneratorImpl;
import be.jsams.server.service.xml.impl.XmlFileGenerator;

/**
 * PDF service implementation for an {@link EstimateBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PdfEstimateServiceImpl implements PdfService<EstimateBean> {

    private XmlEstimateGeneratorImpl xmlGenerator;
    private XmlFileGenerator fileGenerator;
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
        File generatedXmlFile = fileGenerator.generateXmlFile(estimateXml, path + ".xml", EstimateBean.class);

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
     * @return the fileEstimateGeneratorImpl
     */
    public XmlFileGenerator getFileEstimateGeneratorImpl() {
        return fileGenerator;
    }

    /**
     * @param fileEstimateGeneratorImpl the fileEstimateGeneratorImpl to set
     */
    public void setFileEstimateGeneratorImpl(XmlFileGenerator fileEstimateGeneratorImpl) {
        this.fileGenerator = fileEstimateGeneratorImpl;
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
