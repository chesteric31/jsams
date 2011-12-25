package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.model.xml.command.CommandXml;
import be.jsams.server.service.pdf.PdfMerger;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.XmlFileGenerator;
import be.jsams.server.service.xml.impl.XmlCommandGeneratorImpl;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfCommandServiceImpl implements PdfService<CommandBean> {

    private XmlCommandGeneratorImpl xmlGenerator;
    private XmlFileGenerator xmlFileGenerator;
    private PdfMerger merger;
    private String rootReportsPath;
    private String recordPath;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(CommandBean object) {
        CommandXml commandXml = xmlGenerator.generateXml(object);
        String path = rootReportsPath + recordPath;
        File generatedXmlFile = xmlFileGenerator.generateXmlFile(commandXml, path + ".xml", CommandXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        merger.merge(generatedXmlFile, recordPath, reportFileName, outFileName);
    }

    /**
     * @return the xmlGenerator
     */
    public XmlCommandGeneratorImpl getXmlGenerator() {
        return xmlGenerator;
    }

    /**
     * @param xmlGenerator the xmlGenerator to set
     */
    public void setXmlGenerator(XmlCommandGeneratorImpl xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

    /**
     * @return the XmlFileGenerator
     */
    public XmlFileGenerator getXmlFileGenerator() {
        return xmlFileGenerator;
    }

    /**
     * @param xmlFileGenerator the xmlFileGenerator to set
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
