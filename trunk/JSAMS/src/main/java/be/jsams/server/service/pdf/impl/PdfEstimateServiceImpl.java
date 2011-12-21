package be.jsams.server.service.pdf.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.SimpleFileResolver;
import net.sf.jasperreports.view.JasperViewer;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.model.xml.estimate.EstimateXml;
import be.jsams.server.service.pdf.PdfService;
import be.jsams.server.service.xml.impl.XmlEstimateGeneratorImpl;
import be.jsams.server.service.xml.impl.XmlFileEstimateGeneratorImpl;

/**
 * PDF service implementation for an {@link EstimateBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PdfEstimateServiceImpl implements PdfService<EstimateBean> {
    
    private XmlEstimateGeneratorImpl xmlGenerator;
    private XmlFileEstimateGeneratorImpl fileEstimateGeneratorImpl;

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(EstimateBean object) {
        EstimateXml estimateXml = xmlGenerator.generateXml(object);
        File generatedXmlFile = fileEstimateGeneratorImpl.generateXmlFile(estimateXml);

        String reportFileName = "reports/estimate/estimate.jasper";
        String outFileName = "reports/estimate/estimate.pdf";
        String xmlFileName = generatedXmlFile.getAbsolutePath();
        String recordPath = "estimate";

        try {
            JRXmlDataSource jrxmlds = new JRXmlDataSource(xmlFileName, recordPath);
            jrxmlds.setDatePattern("yyyy-MM-dd");
            jrxmlds.setLocale(Locale.ENGLISH);
            jrxmlds.setNumberPattern("###0.00");
            HashMap<String, Object> params = new HashMap<String, Object>();
            String subReportsDirectory = generatedXmlFile.getParentFile().getAbsolutePath();
            File reportsDir = new File(subReportsDirectory);
            params.put(JRParameter.REPORT_FILE_RESOLVER, new SimpleFileResolver(reportsDir));

            JasperPrint print = JasperFillManager.fillReport(reportFileName, params, jrxmlds);

            JRExporter exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            exporter.exportReport();
            JasperViewer.viewReport(print, false);
            System.out.println("Created file: " + outFileName);
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public XmlFileEstimateGeneratorImpl getFileEstimateGeneratorImpl() {
        return fileEstimateGeneratorImpl;
    }

    /**
     * @param fileEstimateGeneratorImpl the fileEstimateGeneratorImpl to set
     */
    public void setFileEstimateGeneratorImpl(XmlFileEstimateGeneratorImpl fileEstimateGeneratorImpl) {
        this.fileEstimateGeneratorImpl = fileEstimateGeneratorImpl;
    }

}
