package be.jsams.client.wizard.transfer;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
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
import com.jgoodies.validation.Validator;
import com.toedter.calendar.JDateChooser;

/**
 * {@link JsamsWizardPanel} to choose the document to transfer.
 * 
 * @param <V> the {@link Validator}
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractDocumentChooserWizardPanel<V extends Validator<TransferBean>> extends
        JsamsWizardPanel<TransferBean, V> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8795118733995010793L;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link Validator}
     * @param panelTitle the {@link I18nString} title
     */
    public AbstractDocumentChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component,
            TransferBean model, V validator, I18nString panelTitle) {
        super(parent, component, model, panelTitle, validator);
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
        setFinishButtonEnabled(false);
        setBackButtonEnabled(true);
        updateContainer();
        super.update();
    }

    /**
     * Update the panel container. 
     */
    public abstract void updateContainer();

    /**
     * Build a new bill view customized for this purpose.
     * 
     * @param bill the {@link BillBean}
     * @return the built {@link BillBeanView}
     */
    protected BillBeanView buildBillView(BillBean bill) {
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
    protected DeliveryOrderBeanView buildDeliveryOrderView(DeliveryOrderBean deliveryOrder) {
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
    protected CommandBeanView buildCommandView(CommandBean command) {
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
    protected EstimateBeanView buildEstimateView(EstimateBean estimate) {
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
        if (prePerformNext()) {
            remove(getComponentCount() - 1);
            switchPanel(TransferWizardDialog.SUMMARY_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        remove(getComponentCount() - 1);
        switchPanel(TransferWizardDialog.THIRD_PANEL);
    }

}
