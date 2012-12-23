package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.time.Month;
import org.jfree.data.time.SimpleTimePeriod;
import org.jfree.data.time.TimePeriodValues;
import org.jfree.data.time.TimePeriodValuesCollection;
import org.jfree.data.xy.XYDataset;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.server.service.sale.BillService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

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
        super(new BorderLayout());
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
        JDesktopPane pane = new JDesktopPane();
        pane.setLayout(new GridLayout(0, 4));

        SocietyBean society = Desktop.getInstance().getCurrentSociety();
        BigDecimal globalTurnover = getService().findGlobalTurnover(society);
//        this.add(new JsamsLabel(globalTurnover.toPlainString()));
//        final DefaultPieDataset pieDataset = new DefaultPieDataset();

//        List<BillBean> openedBills = getService().findOpenedBills(society);
//        pieDataset.setValue(I18nLabelResource.LABEL_BILL_OPENED.getTranslation(), openedBills.size());
//        List<BillBean> closedBills = getService().findClosedBills(society);
//        pieDataset.setValue(I18nLabelResource.LABEL_BILL_CLOSED.getTranslation(), closedBills.size());

//        final JFreeChart pieChart = ChartFactory.createPieChart3D(
//                I18nResource.TITLE_STATISTICS_RATIO_BILLS.getTranslation(), pieDataset, true, true, false);
//        PiePlot3D plot = (PiePlot3D) pieChart.getPlot();
//        plot.setStartAngle(290);
//        plot.setDirection(Rotation.CLOCKWISE);
//        plot.setForegroundAlpha(0.5f);
//        final ChartPanel cPanel = new ChartPanel(pieChart);

        final XYDataset data1 = createDataset();
        final XYItemRenderer renderer1 = new XYBarRenderer();
        
        final DateAxis domainAxis = new DateAxis("Date");
        final ValueAxis rangeAxis = new NumberAxis("Value");
        
        final XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);

        final JFreeChart chart = new JFreeChart(plot);
        final ChartPanel chartPanel = new ChartPanel(chart);

        JInternalFrame billsFrame = new JInternalFrame("Bills", true, false, true, true);
        FormLayout layout = new FormLayout("left:p, 3dlu, p, 5dlu, left:p, 3dlu, p, 5dlu, left:p, 3dlu, p:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout);
        builder.setDefaultDialogBorder();
        builder.appendSeparator("not paid bills");
        builder.append(new JsamsLabel("nbr"));
        builder.append(new JsamsLabel("3"));
        builder.append(new JsamsLabel("sum"));
        builder.append(new JsamsLabel("1505"));
        builder.nextLine();
        builder.appendSeparator("bills to throw back");
        builder.append(new JsamsLabel("nbr"));
        builder.append(new JsamsLabel("1"));
        builder.append(new JsamsLabel("sum"));
        builder.append(new JsamsLabel("100"));
        builder.nextLine();
        builder.appendSeparator("bills expired");
        builder.append(new JsamsLabel("date"));
        builder.append(new JsamsLabel("01/01/1970"));
        builder.append(new JsamsLabel("sum"));
        builder.append(new JsamsLabel("99"));
        builder.nextLine();
        builder.appendSeparator("not closed bills");
        builder.append(new JsamsLabel("nbr"));
        builder.append(new JsamsLabel("1"));
        builder.append(new JsamsLabel("sum"));
        builder.append(new JsamsLabel("100"));
        builder.append(new JsamsLabel("avg"));
        builder.append(new JsamsLabel("100"));
        billsFrame.add(builder.getPanel());
        billsFrame.pack();
        billsFrame.setVisible(true);
        pane.add(billsFrame);

        JInternalFrame estimatesFrame = new JInternalFrame("Estimates", true, false, true, true);
        FormLayout layout1 = new FormLayout("left:p, 3dlu, p:grow");
        DefaultFormBuilder builder1 = new DefaultFormBuilder(layout1);
        builder1.setDefaultDialogBorder();
        builder1.appendSeparator("not transferred estimates");
        builder1.append(new JsamsLabel("nbr"));
        builder1.append(new JsamsLabel("3"));
        builder1.append(new JsamsLabel("sum"));
        builder1.append(new JsamsLabel("1505"));
        builder1.append(new JsamsLabel("avg"));
        builder1.append(new JsamsLabel("100"));
        estimatesFrame.add(builder1.getPanel());
        estimatesFrame.pack();
        estimatesFrame.setVisible(true);
        pane.add(estimatesFrame);
        
        JInternalFrame customersFrame = new JInternalFrame("Customers", true, false, true, true);
        FormLayout layout11 = new FormLayout("left:p, 3dlu, p:grow");
        DefaultFormBuilder builder11 = new DefaultFormBuilder(layout11);
        builder11.setDefaultDialogBorder();
        builder11.appendSeparator("top 5");
        builder11.append(new JsamsLabel("1"));
        builder11.nextLine();
        builder11.append(new JsamsLabel("2"));
        builder11.nextLine();
        builder11.append(new JsamsLabel("3"));
        builder11.nextLine();
        builder11.append(new JsamsLabel("4"));
        builder11.nextLine();
        builder11.append(new JsamsLabel("5"));
        builder11.nextLine();
        builder11.appendSeparator("customer with estimates");
        builder11.append(new JsamsLabel("12"));
        builder11.nextLine();
        builder11.appendSeparator("customer with bills");
        builder11.append(new JsamsLabel("16"));
        customersFrame.add(builder11.getPanel());
        customersFrame.pack();
        customersFrame.setVisible(true);
        pane.add(customersFrame);

        JInternalFrame productsFrame = new JInternalFrame("Products", true, false, true, true);
        FormLayout layout111 = new FormLayout("left:p, 3dlu, p:grow");
        DefaultFormBuilder builder111 = new DefaultFormBuilder(layout111);
        builder111.setDefaultDialogBorder();
        builder111.appendSeparator("top 5");
        builder111.append(new JsamsLabel("1"));
        builder111.append(new JsamsLabel("123"));
        builder111.nextLine();
        builder111.append(new JsamsLabel("2"));
        builder111.append(new JsamsLabel("123"));
        builder111.nextLine();
        builder111.append(new JsamsLabel("3"));
        builder111.append(new JsamsLabel("123"));
        builder111.nextLine();
        builder111.append(new JsamsLabel("4"));
        builder111.append(new JsamsLabel("123"));
        builder111.nextLine();
        builder111.append(new JsamsLabel("5"));
        builder111.append(new JsamsLabel("123"));
        productsFrame.add(builder111.getPanel());
        productsFrame.pack();
        productsFrame.setVisible(true);
        pane.add(productsFrame);

        add(pane, BorderLayout.NORTH);

        JDesktopPane pane1 = new JDesktopPane();
        JInternalFrame globalTurnoverFrame = new JInternalFrame("Global turnover evolution", true, false, true, true);
        JPanel globalTurnoverPanel = new JPanel();
        globalTurnoverPanel.add(new JsamsLabel("Global turnover"));
        globalTurnoverPanel.add(new JsamsLabel(globalTurnover.toEngineeringString()));
        globalTurnoverFrame.add(globalTurnoverPanel, BorderLayout.WEST);
        globalTurnoverFrame.add(chartPanel, BorderLayout.CENTER);
        globalTurnoverFrame.pack();
        globalTurnoverFrame.setVisible(true);
        pane1.add(globalTurnoverFrame);
        try {
            globalTurnoverFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException(e);
        }
        add(pane1, BorderLayout.CENTER);
    }

    /**
     * Creates a dataset, consisting of two series of monthly data.
     * 
     * @return the dataset.
     */
    public XYDataset createDataset() {

        final TimePeriodValues series = new TimePeriodValues("Series 1");
        Month month = new Month(1, GregorianCalendar.getInstance().get(Calendar.YEAR));
        for (int i = 1; i < 12; i++) {
            series.add(new SimpleTimePeriod(month.getStart(), month.getEnd()), i);
            final Month nextMonth = (Month) month.next();
            month = nextMonth;
        }

        // s1.add(new SimpleTimePeriod(june.getStart(), june.getEnd()), 74.95);
        // s1.add(new SimpleTimePeriod(january.getStart(), february.getEnd()),
        // 55.75);
        // s1.add(new SimpleTimePeriod(july.getStart(), july.getEnd()), 90.45);
        // s1.add(new SimpleTimePeriod(march.getStart(), may.getEnd()), 105.75);

        final TimePeriodValuesCollection dataset = new TimePeriodValuesCollection();
        dataset.addSeries(series);
        // dataset.setDomainIsPointsInTime(false);

        return dataset;

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
