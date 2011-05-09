package be.jsams.common.bean.view.management;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.action.SearchCustomerAction;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.Borders;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

/**
 * {@link AbstractBeanView} for {@link CustomerBean}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerBeanView extends AbstractBeanView<CustomerBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8477376815949387506L;

    private JsamsButton buttonSearchCustomer = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "apps/system-users.png");

    /**
     * Constructor
     * 
     * @param bean the {@link CustomerBean}
     */
    public CustomerBeanView(CustomerBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        JPanel panel = new JPanel();

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        JPanel generalPanel = buildGeneralTab();
        tabbedPane.add(JsamsI18nResource.PANEL_GENERAL.getTranslation(), generalPanel);
        JPanel billingAddressPanel = buildBillingAddressTab();
        tabbedPane.add(JsamsI18nResource.PANEL_BILLING_ADDRESS.getTranslation(), billingAddressPanel);
        JPanel deliveryAddressPanel = buildDeliveryAddressTab();
        tabbedPane.add(JsamsI18nResource.PANEL_DELIVERY_ADDRESS.getTranslation(), deliveryAddressPanel);
        JPanel contactInformationsPanel = buildContactInformationsTab();
        tabbedPane.add(JsamsI18nResource.PANEL_CONTACT_INFORMATIONS.getTranslation(), contactInformationsPanel);
        JPanel miscPanel = buildMiscTab();
        tabbedPane.add(JsamsI18nResource.PANEL_MISC.getTranslation(), miscPanel);
        panel.add(tabbedPane);
        return panel;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        CustomerBean bean = getBean();
        ViewFactory<CustomerBean> helper = new ViewFactory<CustomerBean>();
        JTextField textFieldName = helper.createBindingTextComponent(bean, CustomerBean.NAME_PROPERTY, false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 5;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().getView()
                .createEditView());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), bean.getLegalForm().getView()
                .createEditView());
        builder.nextLine();
        ViewFactory<AddressBean> addressHelper = new ViewFactory<AddressBean>();
        JTextField textFieldZipCode = addressHelper.createBindingTextComponent(bean.getBillingAddress(),
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        ViewFactory<ContactInformationBean> contactHelper = new ViewFactory<ContactInformationBean>();
        JTextField textFieldPhone = contactHelper.createBindingTextComponent(bean.getContactInformation(),
                ContactInformationBean.PHONE_PROPERTY, false, false);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone, 1);

        return builder.getPanel();
    }

    /**
     * Builds the general tab panel.
     * 
     * @return the general tab panel
     */
    private JPanel buildGeneralTab() {
        CustomerBean bean = getBean();
        ViewFactory<CustomerBean> helper = new ViewFactory<CustomerBean>();
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, CustomerBean.NAME_PROPERTY, true, false);
        JsamsTextField textFieldVatNumber = helper.createBindingTextComponent(bean, CustomerBean.VATNUMBER_PROPERTY,
                false, false);
        JsamsTextField textFieldBank1 = helper.createBindingTextComponent(bean, CustomerBean.BANK1_PROPERTY, false,
                false);
        JsamsTextField textFieldBank2 = helper.createBindingTextComponent(bean, CustomerBean.BANK2_PROPERTY, false,
                false);
        JsamsFormattedTextField textFieldCreditLimit = helper.createBindingDecimalComponent(bean,
                CustomerBean.CREDITLIMIT_PROPERTY, false, false);
        JsamsFormattedTextField textFieldVatApplicable = helper.createBindingDecimalComponent(bean,
                CustomerBean.VATAPPLICABLE_PROPERTY, true, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(), bean.getCivility().getView()
                .createEditView());
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), bean.getLegalForm().getView()
                .createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getKey(), textFieldVatNumber, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BANK1.getKey(), textFieldBank1, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BANK2.getKey(), textFieldBank2, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREDIT_LIMIT.getKey(), textFieldCreditLimit);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getKey(), textFieldVatApplicable);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().getView()
                .createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_AGENT.getKey(), bean.getAgent().getView().createCustomView(),
                maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * Builds the billing address tab panel.
     * 
     * @return the billing address tab panel
     */
    private JPanel buildBillingAddressTab() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        final AddressBean billingAddress = getBean().getBillingAddress();
        JPanel addressPanel = billingAddress.getView().createEditView();
        JsamsButton copyButton = new JsamsButton(JsamsI18nResource.BUTTON_COPY_BILLING_TO_DELIVERY);
        copyButton.setAction(new AbstractAction(copyButton.getText()) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -8289823872912834435L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                AddressBean deliveryAddress = getBean().getDeliveryAddress();
                deliveryAddress.setBox(billingAddress.getBox());
                deliveryAddress.setCity(billingAddress.getCity());
                deliveryAddress.setCountry(billingAddress.getCountry());
                deliveryAddress.setId(billingAddress.getId());
                deliveryAddress.setListModel(billingAddress.getListModel());
                deliveryAddress.setNumber(billingAddress.getNumber());
                deliveryAddress.setSelection(billingAddress.getSelection());
                deliveryAddress.setStreet(billingAddress.getStreet());
                deliveryAddress.setZipCode(billingAddress.getZipCode());
            }
        });
        mainPanel.add(addressPanel, BorderLayout.CENTER);
        mainPanel.add(ButtonBarFactory.buildCenteredBar(copyButton), BorderLayout.SOUTH);
        mainPanel.setBorder(Borders.DIALOG_BORDER);
        return mainPanel;
    }

    /**
     * Builds the delivery address tab panel.
     * 
     * @return the delivery address tab panel
     */
    private JPanel buildDeliveryAddressTab() {
        AddressBean deliveryAddress = getBean().getDeliveryAddress();
        JPanel panel = deliveryAddress.getView().createEditView();
        panel.setBorder(Borders.DIALOG_BORDER);
        return panel;
    }

    /**
     * Builds the contact informations tab panel.
     * 
     * @return the contact informations tab panel
     */
    private JPanel buildContactInformationsTab() {
        ContactInformationBean contactInformation = getBean().getContactInformation();
        JPanel panel = contactInformation.getView().createEditView();
        panel.setBorder(Borders.DIALOG_BORDER);
        return panel;
    }

    /**
     * Builds the miscellaneous tab panel.
     * 
     * @return the miscellaneous tab panel
     */
    private JPanel buildMiscTab() {
        CustomerBean bean = getBean();
        ViewFactory<CustomerBean> helper = new ViewFactory<CustomerBean>();
        JsamsFormattedTextField textFieldDefaultDiscountRate = helper.createBindingDecimalComponent(bean,
                CustomerBean.DEFAULT_DISCOUNT_RATE_PROPERTY, false, false);
        JTextArea textAreaDescription = helper.createBindingTextAreaComponent(bean, CustomerBean.DESCRIPTION_PROPERTY,
                false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxRows = 9;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), textFieldDefaultDiscountRate);
        builder.nextLine();
        textAreaDescription.setRows(maxRows);
        textAreaDescription.setWrapStyleWord(true);
        textAreaDescription.setLineWrap(true);
        JScrollPane areaScrollPane = new JScrollPane(textAreaDescription);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        builder.appendI15d(JsamsI18nLabelResource.LABEL_DESCRIPTION.getKey(), areaScrollPane);
        return builder.getPanel();
    }

    /**
     * Creates a custom view
     * 
     * @return a {@link JPanel}
     */
    public JPanel createCustomView() {
        final CustomerBean bean = getBean();
        final JsamsDialog dialog = new JsamsDialog(null, JsamsI18nResource.TITLE_SEARCH_CUSTOMER,
                IconUtil.TITLE_ICON_PREFIX + "apps/system-users.png");
        ViewFactory<CustomerBean> helper = new ViewFactory<CustomerBean>();
        buttonSearchCustomer.setAction(new SearchCustomerAction("", buttonSearchCustomer.getIcon(), bean, dialog));
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, CustomerBean.NAME_PROPERTY, false, true);
        FormLayout layout = new FormLayout("175dlu, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.append(textFieldName);
        builder.append(buttonSearchCustomer);

        return builder.getPanel();
    }

}
