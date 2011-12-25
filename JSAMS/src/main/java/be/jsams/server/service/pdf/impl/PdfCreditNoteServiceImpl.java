package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.model.xml.creditNote.CreditNoteXml;
import be.jsams.server.service.pdf.PdfMerger;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.XmlFileGenerator;
import be.jsams.server.service.xml.impl.XmlCreditNoteGeneratorImpl;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfCreditNoteServiceImpl implements PdfService<CreditNoteBean> {

    private XmlCreditNoteGeneratorImpl xmlGenerator;
    private XmlFileGenerator xmlFileGenerator;
    private PdfMerger merger;
    private String rootReportsPath;
    private String recordPath;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(CreditNoteBean object) {
        CreditNoteXml creditNoteXml = xmlGenerator.generateXml(object);
        String path = rootReportsPath + recordPath;
        File generatedXmlFile = xmlFileGenerator.generateXmlFile(creditNoteXml, path + ".xml", CreditNoteXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        merger.merge(generatedXmlFile, recordPath, reportFileName, outFileName);
    }

    /**
     * @return the xmlGenerator
     */
    public XmlCreditNoteGeneratorImpl getXmlGenerator() {
        return xmlGenerator;
    }

    /**
     * @param xmlGenerator the xmlGenerator to set
     */
    public void setXmlGenerator(XmlCreditNoteGeneratorImpl xmlGenerator) {
        this.xmlGenerator = xmlGenerator;
    }

    /**
     * @return the xmlFileGenerator
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
