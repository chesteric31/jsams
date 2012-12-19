package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.Rotation;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.service.sale.BillService;

/**
 * Statistics panel.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class StatisticsPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5941212055654534097L;

    private JsamsStatusBar statusBar = new JsamsStatusBar();

    /**
     * Constructor.
     */
    public StatisticsPanel() {
        super();
        setLayout(new BorderLayout());
        buildMainPanel();
    }

    /**
     * @return the statusBar
     */
    public JsamsStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @param statusBar the statusBar to set
     */
    public void setStatusBar(JsamsStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    /**
     * Builds the main panel contained all the components.
     */
    protected void buildMainPanel() {
        SocietyBean society = Desktop.getInstance().getCurrentSociety();
        BigDecimal globalTurnover = getService().findGlobalTurnover(society);
        this.add(new JsamsLabel(globalTurnover.toPlainString()));

        final DefaultPieDataset pieDataset = new DefaultPieDataset();

        List<BillBean> openedBills = getService().findOpenedBills(society);
        pieDataset.setValue(I18nLabelResource.LABEL_BILL_OPENED.getTranslation(), openedBills.size());
        List<BillBean> closedBills = getService().findClosedBills(society);
        pieDataset.setValue(I18nLabelResource.LABEL_BILL_CLOSED.getTranslation(), closedBills.size());

        final JFreeChart pieChart = ChartFactory.createPieChart3D(
                I18nResource.TITLE_STATISTICS_RATIO_BILLS.getTranslation(), pieDataset, true, true, false);
        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        final ChartPanel cPanel = new ChartPanel(pieChart);

        this.add(cPanel, BorderLayout.CENTER);
    }

    /**
     * @return the {@link BillService}
     */
    private BillService getService() {
        return ApplicationContext.getBillService();
    }

    /**
     * Builds the south panel with buttons and status bar.
     * 
     * @return the south panel with buttons and status bar.
     */
    protected JPanel buildSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());
        southPanel.add(statusBar);
        return southPanel;
    }

}
