package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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
import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.server.service.statistics.StatisticsService;

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

        JInternalFrame billsFrame = buildBillsFrame(society);
        pane.add(billsFrame);

        JInternalFrame estimatesFrame = buildEstimatesFrame();
        pane.add(estimatesFrame);
        
        JInternalFrame customersFrame = buildCustomersFrame();
        pane.add(customersFrame);

        JInternalFrame productsFrame = buildProductsFrame();
        pane.add(productsFrame);

        add(pane, BorderLayout.NORTH);

        JDesktopPane turnoverPane = buildTurnoverPane(society);
        add(turnoverPane, BorderLayout.CENTER);
    }

    private JDesktopPane buildTurnoverPane(SocietyBean society) {
        final XYDataset data1 = createDataset();
        final XYItemRenderer renderer1 = new XYBarRenderer();
        
        final DateAxis domainAxis = new DateAxis("Date");
        final ValueAxis rangeAxis = new NumberAxis("Value");
        
        final XYPlot plot = new XYPlot(data1, domainAxis, rangeAxis, renderer1);

        final JFreeChart chart = new JFreeChart(plot);
        final ChartPanel chartPanel = new ChartPanel(chart);
        Double globalTurnover = getService().findGlobalTurnover(society);
        JDesktopPane pane1 = new JDesktopPane();
        JInternalFrame globalTurnoverFrame = new JInternalFrame("Global turnover evolution", true, false, true, true);
        JPanel globalTurnoverPanel = new JPanel();
        globalTurnoverPanel.add(new JsamsLabel(I18nLabelResource.LABEL_GLOBAL_TURNOVER));
        JsamsFormattedTextField textField = new JsamsFormattedTextField(DecimalFormat.getCurrencyInstance());
        textField.setEnabled(false);
        textField.setValue(globalTurnover);
        globalTurnoverPanel.add(textField);
        globalTurnoverFrame.add(globalTurnoverPanel, BorderLayout.WEST);
        globalTurnoverFrame.add(chartPanel, BorderLayout.CENTER);
        globalTurnoverFrame.pack();
        globalTurnoverFrame.setVisible(true);
        pane1.add(globalTurnoverFrame);
        try {
            globalTurnoverFrame.setMaximum(true);
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
        return pane1;
    }

    private JInternalFrame buildProductsFrame() {
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
        return productsFrame;
    }

    private JInternalFrame buildCustomersFrame() {
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
        return customersFrame;
    }

    private JInternalFrame buildEstimatesFrame() {
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
        return estimatesFrame;
    }

    /**
     * Builds the {@link JInternalFrame} for the statistics of the bills.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the built {@link JInternalFrame}
     */
    private JInternalFrame buildBillsFrame(SocietyBean society) {
        JInternalFrame billsFrame = new JInternalFrame(I18nResource.MENU_ITEM_BILL.getTranslation(), true, false, true,
                true);
        FormLayout layout = new FormLayout("left:p, 3dlu, p, 5dlu, left:p, 3dlu, p, 5dlu, left:p, 3dlu, p:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15dSeparator(I18nLabelResource.LABEL_BILL_NOT_PAID.getKey());
        Map<Double, List<BillBean>> notPaidBills = getService().findNotPaidBills(society);
        updateFrame(builder, notPaidBills);
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
        builder.appendI15dSeparator(I18nLabelResource.LABEL_BILL_OPENED.getKey());
        Map<Double, List<BillBean>> openedBills = getService().findOpenedBills(society);
        updateFrame(builder, openedBills);
        billsFrame.add(builder.getPanel());
        billsFrame.pack();
        billsFrame.setVisible(true);
        return billsFrame;
    }

    private void updateFrame(DefaultFormBuilder builder, Map<Double, List<BillBean>> notPaidBills) {
        JsamsTextField number = new JsamsTextField();
        number.setEnabled(false);
        int size = notPaidBills.size();
        number.setText(String.valueOf(size));
        builder.appendI15d(I18nLabelResource.LABEL_QUANTITY.getKey(), number);
        JsamsFormattedTextField amount = new JsamsFormattedTextField(DecimalFormat.getCurrencyInstance());
        amount.setEnabled(false);
        double doubleValue = notPaidBills.keySet().iterator().next();
        amount.setValue(doubleValue);
        builder.appendI15d(I18nLabelResource.LABEL_AMOUNT.getKey(), amount);
        builder.nextLine();
        double average = doubleValue / size;
        JsamsFormattedTextField averageAmount = new JsamsFormattedTextField(DecimalFormat.getCurrencyInstance());
        averageAmount.setEnabled(false);
        averageAmount.setValue(average);
        builder.appendI15d(I18nLabelResource.LABEL_AVERAGE_AMOUNT.getKey(), averageAmount);
        builder.nextLine();
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
     * @return the {@link StatisticsService}
     */
    private StatisticsService getService() {
        return ApplicationContext.getStatisticsService();
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
