package be.jsams.server.service.pdf;

import be.jsams.server.service.xml.XmlFileGenerator;

/**
 * Abstract class to centralize all common methods/variables for the children services.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractPdfService {

    private PdfMerger merger;
    private String rootReportsPath;
    private String recordPath;
    private XmlFileGenerator xmlFileGenerator;
    
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
