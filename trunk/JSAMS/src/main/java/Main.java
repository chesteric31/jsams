import java.util.HashMap;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;


public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String reportFileName = "reports/estimate.jasper";
        String outFileName = "reports/estimate.pdf";
        String xmlFileName = "reports/estimate.xml";
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
