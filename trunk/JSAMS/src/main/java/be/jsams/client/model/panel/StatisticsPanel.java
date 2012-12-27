package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyVetoException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.EstimateBean;
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

        JInternalFrame estimatesFrame = buildEstimatesFrame(society);
        pane.add(estimatesFrame);
        
        JInternalFrame customersFrame = buildCustomersFrame(society);
        pane.add(customersFrame);

        JInternalFrame productsFrame = buildProductsFrame(society);
        pane.add(productsFrame);

        add(pane, BorderLayout.NORTH);

        JDesktopPane turnoverPane = buildTurnoverPane(society);
        add(turnoverPane, BorderLayout.CENTER);
    }

    /**
     * Builds the {@link JDesktopPane} with turnover statistics.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the built {@link JDesktopPane}
     */
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
        JInternalFrame globalTurnoverFrame = new JInternalFrame(
                I18nResource.TITLE_GLOBAL_TURNOVER_EVOLUTION.getTranslation(), true, false, true, true);
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

    /**
     * Builds the {@link JInternalFrame} for the statistics of the products.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the built {@link JInternalFrame}
     */
    private JInternalFrame buildProductsFrame(SocietyBean society) {
        JInternalFrame productsFrame = new JInternalFrame(I18nResource.MENU_ITEM_PRODUCTS.getTranslation(), true,
                false, true, true);
        FormLayout layout = new FormLayout("left:p, 3dlu, p, 5dlu, left:p, 3dlu, p:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15dSeparator(I18nLabelResource.LABEL_PRODUCTS_TOP_5.getKey());
        Map<Double, ProductBean> products = getService().findTop5Products(society);
        if (products != null) {
            Iterator<Entry<Double, ProductBean>> iterator = products.entrySet().iterator();
            for (int i = 0; i < 5; i++) {
                int j = i + 1;
                builder.append(new JsamsLabel("" + j));
                JsamsTextField textField = new JsamsTextField(15);
                textField.setEnabled(false);
                JsamsFormattedTextField amount = new JsamsFormattedTextField(DecimalFormat.getCurrencyInstance());
                amount.setEnabled(false);
                if (products != null && products.size() > i) {
                    Entry<Double, ProductBean> next = iterator.next();
                    textField.setText(next.getValue().getName());
                    amount.setValue(next.getKey());
                }
                builder.append(textField);
                builder.appendI15d(I18nLabelResource.LABEL_AMOUNT.getKey(), amount);
            }
        }
        productsFrame.add(builder.getPanel());
        productsFrame.pack();
        productsFrame.setVisible(true);
        return productsFrame;
    }

    /**
     * Builds the {@link JInternalFrame} for the statistics of the customers.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the built {@link JInternalFrame}
     */
    private JInternalFrame buildCustomersFrame(SocietyBean society) {
        JInternalFrame customersFrame = new JInternalFrame(I18nResource.MENU_ITEM_CUSTOMERS.getTranslation(), true,
                false, true, true);
        FormLayout layout = new FormLayout("left:p, 3dlu, p, 5dlu, left:p, 3dlu, p:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15dSeparator(I18nLabelResource.LABEL_CUSTOMERS_TOP_5.getKey());
        Map<Double, CustomerBean> customers = getService().findTop5Customers(society);
        if (customers != null) {
            Iterator<Entry<Double, CustomerBean>> iterator = customers.entrySet().iterator();
            for (int i = 0; i < 5; i++) {
                int j = i + 1;
                builder.append(new JsamsLabel("" + j));
                JsamsTextField textField = new JsamsTextField();
                textField.setEnabled(false);
                JsamsFormattedTextField amount = new JsamsFormattedTextField(DecimalFormat.getCurrencyInstance());
                amount.setEnabled(false);
                if (customers != null && customers.size() > i) {
                    Entry<Double, CustomerBean> next = iterator.next();
                    textField.setText(next.getValue().getName());
                    amount.setValue(next.getKey());
                }
                builder.append(textField);
                builder.appendI15d(I18nLabelResource.LABEL_AMOUNT.getKey(), amount);
            }
        }
        builder.appendI15dSeparator(I18nLabelResource.LABEL_CUSTOMERS_WITH_ESTIMATE.getKey());
        List<CustomerBean> withEstimates = getService().findCustomersWithEstimates(society);
        JsamsTextField withEstimatesNumber = new JsamsTextField();
        withEstimatesNumber.setEnabled(false);
        withEstimatesNumber.setText(String.valueOf(withEstimates.size()));
        builder.appendI15d(I18nLabelResource.LABEL_QUANTITY.getKey(), withEstimatesNumber);
        builder.nextLine();
        builder.appendI15dSeparator(I18nLabelResource.LABEL_CUSTOMERS_WITH_BILL.getKey());
        List<CustomerBean> withBills = getService().findCustomersWithBills(society);
        JsamsTextField withBillsNumber = new JsamsTextField();
        withBillsNumber.setEnabled(false);
        withBillsNumber.setText(String.valueOf(withBills.size()));
        builder.appendI15d(I18nLabelResource.LABEL_QUANTITY.getKey(), withBillsNumber);
        customersFrame.add(builder.getPanel());
        customersFrame.pack();
        customersFrame.setVisible(true);
        return customersFrame;
    }

    /**
     * Builds the {@link JInternalFrame} for the statistics of the estimates.
     * 
     * @param society the {@link SocietyBean} to use
     * @return the built {@link JInternalFrame}
     */
    private JInternalFrame buildEstimatesFrame(SocietyBean society) {
        JInternalFrame estimatesFrame = new JInternalFrame(I18nResource.MENU_ITEM_ESTIMATE.getTranslation(), true,
                false, true, true);
        FormLayout layout = new FormLayout("left:p, 3dlu, p:grow");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15dSeparator(I18nLabelResource.LABEL_ESTIMATE_NOT_TRANSFERRED.getKey());
        Map<Double, List<EstimateBean>> notTransferredEstimates = getService().findNotTransferredEstimates(society);
        updateFrame(builder, notTransferredEstimates);
        estimatesFrame.add(builder.getPanel());
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
        FormLayout layout = new FormLayout("left:p, 3dlu, p, 5dlu, left:p, 3dlu, p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15dSeparator(I18nLabelResource.LABEL_BILL_NOT_PAID.getKey());
        Map<Double, List<BillBean>> notPaidBills = getService().findNotPaidBills(society);
        updateFrame(builder, notPaidBills);
        builder.appendI15dSeparator(I18nLabelResource.LABEL_BILL_TO_THROW_BACK.getKey());
        Map<Double, List<BillBean>> toThrowBackBills = getService().findToThrowBackBills(society);
        updateFrame(builder, toThrowBackBills);
        builder.appendI15dSeparator(I18nLabelResource.LABEL_BILL_EXPIRED.getKey());
        Map<Double, List<BillBean>> expiredBills = getService().findExpiredBills(society);
        updateFrame(builder, expiredBills);
        builder.appendI15dSeparator(I18nLabelResource.LABEL_BILL_OPENED.getKey());
        Map<Double, List<BillBean>> openedBills = getService().findOpenedBills(society);
        updateFrame(builder, openedBills);
        billsFrame.add(builder.getPanel());
        billsFrame.pack();
        billsFrame.setVisible(true);
        return billsFrame;
    }

    /**
     * Updates the current {@link DefaultFormBuilder} with the documents.
     * 
     * @param <D> the document type
     * @param builder the {@link DefaultFormBuilder} to update
     * @param documents the documents to use
     */
    private <D extends AbstractDocumentBean<?, ?>> void updateFrame(DefaultFormBuilder builder,
            Map<Double, List<D>> documents) {
        JsamsTextField number = new JsamsTextField();
        number.setEnabled(false);
        double doubleValue = documents.keySet().iterator().next();
        int size = documents.get(doubleValue).size();
        number.setText(String.valueOf(size));
        builder.appendI15d(I18nLabelResource.LABEL_QUANTITY.getKey(), number);
        JsamsFormattedTextField amount = new JsamsFormattedTextField(DecimalFormat.getCurrencyInstance());
        amount.setEnabled(false);
        amount.setValue(doubleValue);
        builder.appendI15d(I18nLabelResource.LABEL_AMOUNT.getKey(), amount);
        builder.nextLine();
        double average = 0D;
        if (size != 0) {
            average = doubleValue / size;
        }
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
