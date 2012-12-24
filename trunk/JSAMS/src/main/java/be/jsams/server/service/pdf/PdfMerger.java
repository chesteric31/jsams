package be.jsams.server.service.pdf;

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

/**
 * PDF merger with XML file input and Jasper report template.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class PdfMerger {

    /**
     * Merges the generated XML file with the default Jasper report template.
     * 
     * @param generatedXmlFile the JAXB auto generated XML file
     * @param recordPath the record path
     * @param reportFileName the report file name
     * @param outFileName the output file name
     * @param viewReport true if we will to see the report, false otherwise
     * @return the filename where the exported PDF was created
     */
    public String merge(File generatedXmlFile, String recordPath, String reportFileName, String outFileName,
            boolean viewReport) {
        try {
            JRXmlDataSource jrxmlds = new JRXmlDataSource(generatedXmlFile.getAbsolutePath(), recordPath);
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

            if (viewReport) {
                JasperViewer.viewReport(print, false);
            }
            return outFileName;
        } catch (JRException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
