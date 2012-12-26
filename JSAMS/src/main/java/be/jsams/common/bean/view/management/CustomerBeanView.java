package be.jsams.common.bean.view.management;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.action.SearchCustomerAction;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.common.bean.view.Editable;
import be.jsams.common.bean.view.Searchable;
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
public class CustomerBeanView extends AbstractBeanView<CustomerBean> implements Editable<JPanel>, Searchable<JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -8477376815949387506L;

    private JsamsButton buttonSearchCustomer = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "apps/system-users.png");

    /**
     * Constructor.
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
        tabbedPane.add(I18nResource.PANEL_GENERAL.getTranslation(), generalPanel);
        JPanel billingAddressPanel = buildBillingAddressTab();
        tabbedPane.add(I18nResource.PANEL_BILLING_ADDRESS.getTranslation(), billingAddressPanel);
        JPanel deliveryAddressPanel = buildDeliveryAddressTab();
        tabbedPane.add(I18nResource.PANEL_DELIVERY_ADDRESS.getTranslation(), deliveryAddressPanel);
        JPanel contactInformationsPanel = buildContactInformationsTab();
        tabbedPane.add(I18nResource.PANEL_CONTACT_INFORMATIONS.getTranslation(), contactInformationsPanel);
        JPanel miscPanel = buildMiscTab();
        tabbedPane.add(I18nResource.PANEL_MISC.getTranslation(), miscPanel);
        panel.add(tabbedPane);
        return panel;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        CustomerBean bean = getBean();
        ViewFactory<CustomerBean> viewFactory = getViewFactory();
        JTextField textFieldName = viewFactory.createBindingTextComponent(bean, CustomerBean.NAME_PROPERTY, false,
                false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxColumnSpan = 5;
        builder.appendI15d(I18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().buildView()
                .createEditView());
        builder.appendI15d(I18nLabelResource.LABEL_LEGAL_FORM.getKey(), bean.getLegalForm().buildView()
                .createEditView());
        builder.nextLine();
        AddressBean address = bean.getBillingAddress();
        ViewFactory<AddressBean> viewAddressFactory = address.buildView().getViewFactory();
        JTextField textFieldZipCode = viewAddressFactory.createBindingTextComponent(address,
                AddressBean.ZIP_CODE_PROPERTY, false, false);
        ContactInformationBean contactInformation = bean.getContactInformation();
        ViewFactory<ContactInformationBean> viewContactFactory = contactInformation.buildView().getViewFactory();
        JTextField textFieldPhone = viewContactFactory.createBindingTextComponent(contactInformation,
                ContactInformationBean.PHONE_PROPERTY, false, false);
        builder.appendI15d(I18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
        builder.appendI15d(I18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);

        return builder.getPanel();
    }

    /**
     * Build the general tab panel.
     * 
     * @return the general tab panel
     */
    private JPanel buildGeneralTab() {
        CustomerBean bean = getBean();
        ViewFactory<CustomerBean> helper = new ViewFactory<CustomerBean>();
        JsamsTextField textFieldName = helper.createBindingTextComponent(bean, CustomerBean.NAME_PROPERTY, true, false);
        JsamsTextField textFieldFirstName = helper.createBindingTextComponent(bean, CustomerBean.FIRST_NAME_PROPERTY,
                false, false);
        JsamsTextField textFieldVatNumber = helper.createBindingTextComponent(bean, CustomerBean.VAT_NUMBER_PROPERTY,
                false, false);
        JsamsTextField textFieldBank1 = helper.createBindingTextComponent(bean, CustomerBean.BANK1_PROPERTY, false,
                false);
        JsamsTextField textFieldBank2 = helper.createBindingTextComponent(bean, CustomerBean.BANK2_PROPERTY, false,
                false);
        JsamsFormattedTextField textFieldCreditLimit = helper.createBindingCurrencyComponent(bean,
                CustomerBean.CREDIT_LIMIT_PROPERTY, false, false);
        JsamsFormattedTextField textFieldVatApplicable = helper.createBindingPercentageComponent(bean,
                CustomerBean.VAT_APPLICABLE_PROPERTY, true, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(I18nLabelResource.LABEL_CIVILITY.getKey(), bean.getCivility().buildView()
                .createEditView());
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_FIRST_NAME.getKey(), textFieldFirstName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_LEGAL_FORM.getKey(), bean.getLegalForm().buildView()
                .createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_VAT_NUMBER.getKey(), textFieldVatNumber, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_BANK1.getKey(), textFieldBank1, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_BANK2.getKey(), textFieldBank2, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_CREDIT_LIMIT.getKey(), textFieldCreditLimit);
        builder.appendI15d(I18nLabelResource.LABEL_VAT_APPLICABLE.getKey(), textFieldVatApplicable);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_PAYMENT_MODE.getKey(), bean.getPaymentMode().buildView()
                .createEditView(), maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_AGENT.getKey(), bean.getAgent().buildView().createCustomView(),
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
        JPanel addressPanel = billingAddress.buildView().createEditView();
        JsamsButton copyButton = new JsamsButton(I18nResource.BUTTON_COPY_BILLING_TO_DELIVERY);
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
        JPanel panel = deliveryAddress.buildView().createEditView();
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
        JPanel panel = contactInformation.buildView().createEditView();
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
        JsamsFormattedTextField textFieldDefaultDiscountRate = helper.createBindingPercentageComponent(bean,
                CustomerBean.DEFAULT_DISCOUNT_RATE_PROPERTY, false, false);
        JTextArea textAreaDescription = helper.createBindingTextAreaComponent(bean, CustomerBean.DESCRIPTION_PROPERTY,
                false, false);
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        final int maxRows = 9;
        builder.appendI15d(I18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE.getKey(), textFieldDefaultDiscountRate);
        builder.nextLine();
        textAreaDescription.setRows(maxRows);
        textAreaDescription.setWrapStyleWord(true);
        textAreaDescription.setLineWrap(true);
        JScrollPane areaScrollPane = new JScrollPane(textAreaDescription);
        areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        builder.appendI15d(I18nLabelResource.LABEL_DESCRIPTION.getKey(), areaScrollPane);
        return builder.getPanel();
    }

    /**
     * Creates a custom view.
     * 
     * @return a {@link JPanel}
     */
    public JPanel createCustomView() {
        final CustomerBean bean = getBean();
        final JsamsDialog dialog = new JsamsDialog(null, I18nResource.TITLE_SEARCH_CUSTOMER,
                IconUtil.TITLE_ICON_PREFIX + "apps/system-users.png");
        ViewFactory<CustomerBean> viewFactory = getViewFactory();
        buttonSearchCustomer.setAction(new SearchCustomerAction("", buttonSearchCustomer.getIcon(), bean, dialog));
        JsamsTextField textFieldName = viewFactory.createBindingTextComponent(bean, CustomerBean.NAME_PROPERTY, false,
                true);
        FormLayout layout = new FormLayout("p:grow, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.append(textFieldName);
        builder.append(buttonSearchCustomer);

        return builder.getPanel();
    }

}
