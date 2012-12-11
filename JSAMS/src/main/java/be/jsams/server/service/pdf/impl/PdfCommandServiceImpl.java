package be.jsams.server.service.pdf.impl;

import java.io.File;

import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.server.model.xml.command.CommandXml;
import be.jsams.server.service.pdf.AbstractPdfService;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlCommandGeneratorImpl;

/**
 * PDF command service implementation.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfCommandServiceImpl extends AbstractPdfService implements PdfService<CommandBean> {

    private XmlCommandGeneratorImpl xmlGenerator;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(CommandBean object) {
        CommandXml commandXml = xmlGenerator.generateXml(object);
        String path = getRootReportsPath() + getRecordPath();
        File generatedXmlFile = getXmlFileGenerator().generateXmlFile(commandXml, path + ".xml", CommandXml.class);

        String reportFileName = path + ".jasper";
        String outFileName = path + ".pdf";

        getMerger().merge(generatedXmlFile, getRecordPath(), reportFileName, outFileName);
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

}
