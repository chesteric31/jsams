package be.jsams.client.wizard.transfer;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.model.panel.sale.SearchBillPanel;
import be.jsams.client.model.panel.sale.SearchCommandPanel;
import be.jsams.client.model.panel.sale.SearchDeliveryOrderPanel;
import be.jsams.client.model.panel.sale.SearchEstimatePanel;
import be.jsams.client.model.table.BillTableModel;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.model.table.DeliveryOrderTableModel;
import be.jsams.client.model.table.EstimateTableModel;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.BillTableMouseListener;
import be.jsams.client.swing.listener.CommandTableMouseListener;
import be.jsams.client.swing.listener.DeliveryOrderTableMouseListener;
import be.jsams.client.swing.listener.EstimateTableMouseListener;
import be.jsams.client.validator.SearchBillValidator;
import be.jsams.client.validator.SearchCommandValidator;
import be.jsams.client.validator.SearchDeliveryOrderValidator;
import be.jsams.client.validator.SearchEstimateValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.common.bean.view.ViewFactory;
import be.jsams.common.bean.view.sale.BillBeanView;
import be.jsams.common.bean.view.sale.CommandBeanView;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.common.bean.view.sale.EstimateBeanView;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.toedter.calendar.JDateChooser;

/**
 * {@link JsamsWizardPanel} to choose the document to transfer. 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DocumentChooserWizardPanel extends JsamsWizardPanel<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8795118733995010793L;
    private final int noOneSelected = 0;
    private final int finishSelected = 5;
    private int selectedOption = noOneSelected; // 0 is no selected option

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     */
    public DocumentChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component, TransferBean model) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DOCUMENT);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        boolean nextEnabled = false;
        if (isRadioSelected()) {
            nextEnabled = true;
        }
        setNextButtonEnabled(nextEnabled);
        boolean finishEnabled = selectedOption == finishSelected;
        setFinishButtonEnabled(finishEnabled);
        setBackButtonEnabled(true);
        updateContainer();
        super.update();
    }
    
    /**
     * Update the panel container. 
     */
    private void updateContainer() {
        int source = getModel().getSourceType();
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);

        switch (source) {
        case 1:
            EstimateBean estimate = new EstimateBean(currentSociety, customer, agent);
            estimate.setTransferred(false);
            estimate.setView(buildEstimateView(estimate));
            SearchEstimatePanel estimatePanel = new SearchEstimatePanel(estimate, new EstimateTableMouseListener(),
                    JsamsApplicationContext.getEstimateService(), new SearchEstimateValidator(),
                    new EstimateTableModel(), false);
            this.add(estimatePanel);
            break;
        case 2:
            CommandBean command = new CommandBean(currentSociety, customer, agent);
            command.setTransferred(false);
            command.setView(buildCommandView(command));
            SearchCommandPanel commandPanel = new SearchCommandPanel(command, new CommandTableMouseListener(),
                    JsamsApplicationContext.getCommandService(), new SearchCommandValidator(), new CommandTableModel(),
                    false);
            this.add(commandPanel);
            break;
        case 3:
            DeliveryOrderBean deliveryOrder = new DeliveryOrderBean(currentSociety, customer);
            deliveryOrder.setTransferred(false);
            deliveryOrder.setView(buildDeliveryOrderView(deliveryOrder));
            SearchDeliveryOrderPanel deliveryOrderPanel = new SearchDeliveryOrderPanel(deliveryOrder,
                    new DeliveryOrderTableMouseListener(), JsamsApplicationContext.getDeliveryOrderService(),
                    new SearchDeliveryOrderValidator(), new DeliveryOrderTableModel(), false);
            this.add(deliveryOrderPanel);
            break;
        case 4:
            PaymentModeBeanBuilder builder = JsamsApplicationContext.getPaymentModeBeanBuilder();
            PaymentModeBean mode = builder.build();
            BillBean bill = new BillBean(currentSociety, customer, mode);
            bill.setView(buildBillView(bill));
//            bill.setTransferred(false);
            SearchBillPanel billPanel = new SearchBillPanel(bill, new BillTableMouseListener(),
                    JsamsApplicationContext.getBillService(), new SearchBillValidator(), new BillTableModel(), false);
            this.add(billPanel);
            break;
        default:
            break;
        }
    }

    /**
     * Build a new bill view customized for this purpose.
     * 
     * @param bill the {@link BillBean}
     * @return the built {@link BillBeanView}
     */
    private BillBeanView buildBillView(BillBean bill) {
        return new BillBeanView(bill) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -7161723746961251878L;
            
            /**
             * {@inheritDoc}
             */
            public JPanel createSearchView() {
                BillBean bean = getBean();
                ViewFactory<BillBean> viewFactory = getViewFactory();
                JCheckBox paid = viewFactory.createBindingBooleanComponent(bean, BillBean.PAID_PROPERTY, false, false);
                PeriodBean period = bean.getPeriod();
                ViewFactory<PeriodBean> viewPeriodFactory = period.getView().getViewFactory();
                JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.START_DATE_PROPERTY, false, false);
                JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.END_DATE_PROPERTY, false, false);
                AddressBean address = bean.getBillingAddress();
                ViewFactory<AddressBean> viewAddressFactory = address.getView().getViewFactory();
                JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.CITY_PROPERTY, false, false);
                JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.ZIP_CODE_PROPERTY, false, false);
                FormLayout layout = new FormLayout(
                        "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
                DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
                builder.setDefaultDialogBorder();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                        .createCustomView());
                builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
                builder.nextLine();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().getView()
                        .createEditView());
                builder.nextLine();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_PAID.getKey(), paid);

                return builder.getPanel();
            }
        };
    }

    /**
     * Build a new delivery order view customized for this purpose.
     * 
     * @param deliveryOrder the {@link deliveryOrder}
     * @return the built {@link DeliveryOrderBeanView}
     */
    private DeliveryOrderBeanView buildDeliveryOrderView(DeliveryOrderBean deliveryOrder) {
        return new DeliveryOrderBeanView(deliveryOrder) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 5909517403817126700L;
            
            /**
             * {@inheritDoc}
             */
            public JPanel createSearchView() {
                DeliveryOrderBean bean = getBean();
                PeriodBean period = bean.getPeriod();
                ViewFactory<PeriodBean> viewPeriodFactory = period.getView().getViewFactory();
                JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.START_DATE_PROPERTY, false, false);
                JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.END_DATE_PROPERTY, false, false);
                AddressBean address = bean.getDeliveryAddress();
                ViewFactory<AddressBean> viewAddressFactory = address.getView().getViewFactory();
                JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.CITY_PROPERTY, false, false);
                JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.ZIP_CODE_PROPERTY, false, false);
                FormLayout layout = new FormLayout(
                        "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
                DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
                builder.setDefaultDialogBorder();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                        .createCustomView());
                builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
                builder.nextLine();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

                return builder.getPanel();
            }
        };
    }

    /**
     * Build a new estimate view customized for this purpose.
     * 
     * @param command the {@link EstimateBean}
     * @return the built {@link EstimateBeanView}
     */
    private CommandBeanView buildCommandView(CommandBean command) {
        return new CommandBeanView(command) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -7326892997601840469L;

            /**
             * {@inheritDoc}
             */
            public JPanel createSearchView() {
                CommandBean bean = getBean();
                PeriodBean period = bean.getPeriod();
                ViewFactory<PeriodBean> viewPeriodFactory = period.getView().getViewFactory();
                JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.START_DATE_PROPERTY, false, false);
                JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.END_DATE_PROPERTY, false, false);
                AddressBean address = bean.getBillingAddress();
                ViewFactory<AddressBean> viewAddressFactory = address.getView().getViewFactory();
                JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.CITY_PROPERTY, false, false);
                JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.ZIP_CODE_PROPERTY, false, false);
                FormLayout layout = new FormLayout(
                        "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
                DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
                builder.setDefaultDialogBorder();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                        .createCustomView());
                builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
                builder.nextLine();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

                return builder.getPanel();
            }
        };
    }

    /**
     * Build a new estimate view customized for this purpose.
     * 
     * @param estimate the {@link EstimateBean}
     * @return the built {@link EstimateBeanView}
     */
    private EstimateBeanView buildEstimateView(EstimateBean estimate) {
        return new EstimateBeanView(estimate) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 3895840413879361058L;
            
            /**
             * {@inheritDoc}
             */
            public JPanel createSearchView() {
                EstimateBean bean = getBean();

                PeriodBean period = bean.getPeriod();
                ViewFactory<PeriodBean> viewPeriodFactory = period.getView().getViewFactory();
                JDateChooser startDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.START_DATE_PROPERTY, false, false);
                JDateChooser endDate = viewPeriodFactory.createBindingDateComponent(period,
                        PeriodBean.END_DATE_PROPERTY, false, false);
                AddressBean address = bean.getBillingAddress();
                ViewFactory<AddressBean> viewAddressFactory = address.getView().getViewFactory();
                JsamsTextField textFieldCity = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.CITY_PROPERTY, false, false);
                JsamsTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                        AddressBean.ZIP_CODE_PROPERTY, false, false);
                FormLayout layout = new FormLayout(
                        "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
                DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
                builder.setDefaultDialogBorder();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CUSTOMER_NAME.getKey(), bean.getCustomer().getView()
                        .createCustomView());
                builder.appendI15d(JsamsI18nLabelResource.LABEL_START_DATE.getKey(), startDate);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_END_DATE.getKey(), endDate);
                builder.nextLine();
                builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
                builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);

                return builder.getPanel();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        remove(getComponentCount() - 1);
        switchPanel(TransferWizardDialog.THIRD_PANEL);
    }
    

    /**
     * @return true if one radio is selected, false otherwise
     */
    private boolean isRadioSelected() {
        return true;
    }

}
