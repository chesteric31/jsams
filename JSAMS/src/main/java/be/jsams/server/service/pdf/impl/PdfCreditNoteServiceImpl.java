package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.server.model.xml.creditNote.CreditNoteXml;
import be.jsams.server.service.pdf.AbstractPdfService;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlCreditNoteGeneratorImpl;

/**
 * PDF credit note service implementation.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfCreditNoteServiceImpl extends AbstractPdfService implements PdfService<CreditNoteBean> {

    private XmlCreditNoteGeneratorImpl xmlGenerator;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(CreditNoteBean object) {
        CreditNoteXml creditNoteXml = xmlGenerator.generateXml(object);
        String path = getRootReportsPath() + getRecordPath();
        File generatedXmlFile = getXmlFileGenerator()
                .generateXmlFile(creditNoteXml, path + ".xml", CreditNoteXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        getMerger().merge(generatedXmlFile, getRecordPath(), reportFileName, outFileName);
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

}
