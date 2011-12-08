package be.jsams.server.service.pdf.impl;

import java.io.File;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.server.model.sale.xml.EstimateXml;
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void generatePdf(EstimateBean object) {
        XmlEstimateGeneratorImpl xmlGenerator = new XmlEstimateGeneratorImpl();
        EstimateXml estimateXml = xmlGenerator.generateXml(object);
        XmlFileEstimateGeneratorImpl fileEstimateGeneratorImpl = new XmlFileEstimateGeneratorImpl();
        File generatedXmlFile = fileEstimateGeneratorImpl.generateXmlFile(estimateXml);

        String reportFileName = "reports/estimate.jasper";
        String outFileName = "reports/estimate.pdf";
        String xmlFileName = generatedXmlFile.getAbsolutePath();
        String recordPath = "estimate";

        try {
            JRXmlDataSource jrxmlds = new JRXmlDataSource(xmlFileName, recordPath);
            jrxmlds.setDatePattern("yyyy-mm-dd");
            HashMap<String, Object> hm = new HashMap<String, Object>();
            JasperPrint print = JasperFillManager.fillReport(reportFileName, hm, jrxmlds);

            JRExporter exporter = new JRPdfExporter();

            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);

            exporter.exportReport();
            JasperViewer.viewReport(print);
            System.out.println("Created file: " + outFileName);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
