package be.jsams.client.model.dialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.action.SearchAgentAction;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.client.validator.CustomerValidator;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Customer;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.service.CustomerService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.mysql.jdbc.StringUtils;

/**
 * Edit Customer {@link EditDialog}, to create or update a {@link Customer} object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditCustomerDialog extends EditDialog<Customer, CustomerValidator, CustomerService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6898471936119469349L;

    protected static final Log LOGGER = LogFactory.getLog(EditCustomerDialog.class);

    private static final int MAX_CHARACTERS = 50;

    private static final int MAX_NUMBERS = 10;

    private JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);

    private JComboBox comboBoxLegalForm;

    private JComboBox comboBoxCivility;

    private JComboBox comboBoxPaymentMode;

    private JsamsTextField textFieldVatNumber = new JsamsTextField(MAX_CHARACTERS);

    private JsamsFormattedTextField textFieldVatApplicable = new JsamsFormattedTextField();

    private JsamsTextField textFieldBank1 = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldBank2 = new JsamsTextField(MAX_CHARACTERS);

    private JsamsFormattedTextField textFieldCreditLimit = new JsamsFormattedTextField();

    private JsamsTextField textFieldAgent = new JsamsTextField(MAX_CHARACTERS);

    private JsamsButton buttonSearchAgent = new JsamsButton(IconUtil.MENU_ICON_PREFIX
            + "categories/applications-development.png");

    private JsamsTextField textFieldBillingStreet = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldBillingNumber = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldBillingBox = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldBillingZipCode = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldBillingCity = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldBillingCountry = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldDeliveryStreet = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldDeliveryNumber = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldDeliveryBox = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldDeliveryZipCode = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldDeliveryCity = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldDeliveryCountry = new JsamsTextField(MAX_CHARACTERS);

    private JsamsFormattedTextField textFieldDefaultDiscountRate = new JsamsFormattedTextField();

    private JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldFax = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldMobile = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldEmail = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldWebsite = new JsamsTextField(MAX_CHARACTERS);

    private JTextArea textAreaDescription = new JTextArea();

    private JTabbedPane tabbedPane;

    private JPanel generalPanel;

    private JPanel billingAddressPanel;

    private JPanel deliveryAddressPanel;

    private JPanel contactInformationsPanel;

    private JPanel miscPanel;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link Customer} model
     */
    public EditCustomerDialog(final I18nString title, Customer model) {
        super(null, title, "apps/system-users.png");
        super.setModel(model);
        super.setValidator(new CustomerValidator());
        super.setService(JsamsApplicationContext.getCustomerService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    protected void initComponents() {
        fillData();
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        generalPanel = buildGeneralTab();
        buttonSearchAgent.addActionListener(new SearchAgentAction());
        tabbedPane.add(JsamsI18nResource.PANEL_GENERAL.getTranslation(), generalPanel);
        billingAddressPanel = buildBillingAddressTab();
        tabbedPane.add(JsamsI18nResource.PANEL_BILLING_ADDRESS.getTranslation(), billingAddressPanel);
        deliveryAddressPanel = buildDeliveryAddressTab();
        tabbedPane.add(JsamsI18nResource.PANEL_DELIVERY_ADDRESS.getTranslation(), deliveryAddressPanel);
        contactInformationsPanel = buildContactInformationsTab();
        tabbedPane.add(JsamsI18nResource.PANEL_CONTACT_INFORMATIONS.getTranslation(), contactInformationsPanel);
        miscPanel = buildMiscTab();
        tabbedPane.add(JsamsI18nResource.PANEL_MISC.getTranslation(), miscPanel);

        setMandatoryFields();
        add(tabbedPane);

        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);

        pack();
    }

    /**
     * Fills all the data in case of update.
     */
    private void fillData() {
        List<LegalForm> allLegalForms = JsamsApplicationContext.getLegalFormDao().findAll();
        ArrayList<LegalForm> allWithNull = new ArrayList<LegalForm>();
        allWithNull.add(null);
        allWithNull.addAll(allLegalForms);
        comboBoxLegalForm = new JComboBox(allWithNull.toArray());
        List<Civility> allCivilities = JsamsApplicationContext.getCivilityDao().findAll();
        ArrayList<Civility> allWithNullCivilities = new ArrayList<Civility>();
        allWithNullCivilities.add(null);
        allWithNullCivilities.addAll(allCivilities);
        comboBoxCivility = new JComboBox(allWithNullCivilities.toArray());
        List<PaymentMode> allPaymentModes = JsamsApplicationContext.getPaymentModeDao().findAll();
        comboBoxPaymentMode = new JComboBox(allPaymentModes.toArray());

        if (getModel() != null) {
            fillBillingAddress();
            fillDeliveryAddress();
            fillContactInformations();
            fillMisc();
            comboBoxLegalForm.setSelectedItem(getModel().getLegalForm());
            comboBoxCivility.setSelectedItem(getModel().getCivility());
            textFieldName.setText(getModel().getName());
            textFieldVatNumber.setText(getModel().getVatNumber());
            textFieldVatApplicable.setValue(getModel().getVatApplicable());
        }
        comboBoxLegalForm.setRenderer(new TranslatableComboBoxRenderer());
        comboBoxCivility.setRenderer(new TranslatableComboBoxRenderer());
        comboBoxPaymentMode.setRenderer(new TranslatableComboBoxRenderer());
    }

    /**
     * Builds the general tab panel.
     * 
     * @return the general tab panel
     */
    private JPanel buildGeneralTab() {
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(), comboBoxCivility);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), comboBoxLegalForm, maxColumnSpan);
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
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), comboBoxPaymentMode, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_AGENT.getKey(), textFieldAgent, buttonSearchAgent);
        return builder.getPanel();
    }

    /**
     * Builds the billing address tab panel.
     * 
     * @return the billing address tab panel
     */
    private JPanel buildBillingAddressTab() {
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 9;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(), textFieldBillingStreet, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(), textFieldBillingNumber);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(), textFieldBillingBox);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldBillingZipCode);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldBillingCity, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(), textFieldBillingCountry, maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * Builds the delivery address tab panel.
     * 
     * @return the delivery address tab panel
     */
    private JPanel buildDeliveryAddressTab() {
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 9;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(), textFieldDeliveryStreet, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(), textFieldDeliveryNumber);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(), textFieldDeliveryBox);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldDeliveryZipCode);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldDeliveryCity, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(), textFieldDeliveryCountry, maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * Builds the contact informations tab panel.
     * 
     * @return the contact informations tab panel
     */
    private JPanel buildContactInformationsTab() {
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FAX.getKey(), textFieldFax);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_MOBILE.getKey(), textFieldMobile);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_EMAIL.getKey(), textFieldEmail);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_WEBSITE.getKey(), textFieldWebsite, maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * Builds the miscellaneous tab panel.
     * 
     * @return the miscellaneous tab panel
     */
    private JPanel buildMiscTab() {
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
     * Fills the billing address data.
     */
    private void fillBillingAddress() {
        if (getModel() != null) {
            Address billingAddress = getModel().getBillingAddress();
            textFieldBillingBox.setText(billingAddress.getBox());
            textFieldBillingCity.setText(billingAddress.getCity());
            textFieldBillingCountry.setText(billingAddress.getCountry());
            textFieldBillingNumber.setText(billingAddress.getNumber());
            textFieldBillingStreet.setText(billingAddress.getStreet());
            textFieldBillingZipCode.setText(Integer.toString(billingAddress.getZipCode()));
        }
    }

    /**
     * Fills the delivery address data.
     */
    private void fillDeliveryAddress() {
        if (getModel() != null) {
            Address deliveryAddress = getModel().getDeliveryAddress();
            textFieldDeliveryBox.setText(deliveryAddress.getBox());
            textFieldDeliveryCity.setText(deliveryAddress.getCity());
            textFieldDeliveryCountry.setText(deliveryAddress.getCountry());
            textFieldDeliveryNumber.setText(deliveryAddress.getNumber());
            textFieldDeliveryStreet.setText(deliveryAddress.getStreet());
            textFieldDeliveryZipCode.setText(Integer.toString(deliveryAddress.getZipCode()));
        }
    }

    /**
     * Fills the contact informations data.
     */
    private void fillContactInformations() {
        if (getModel() != null) {
            ContactInformation contactInformations = getModel().getContactInformation();
            textFieldPhone.setText(contactInformations.getPhone());
            textFieldFax.setText(contactInformations.getFax());
            textFieldMobile.setText(contactInformations.getMobile());
            textFieldEmail.setText(contactInformations.getEmail());
            textFieldWebsite.setText(contactInformations.getWebsite());
        }
    }

    /**
     * Fills the miscellaneous data.
     */
    private void fillMisc() {
        if (getModel() != null) {
            if (getModel().getDefaultDiscountRate() != null) {
                textFieldDefaultDiscountRate.setValue(getModel().getDefaultDiscountRate());
            }
            textAreaDescription.setText(getModel().getDescription());
        }
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        Customer customer = new Customer();
        // TODO see for agent object
        customer.setName(textFieldName.getText());
        if (comboBoxLegalForm.getSelectedItem() != null) {
            customer.setLegalForm((LegalForm) comboBoxLegalForm.getSelectedItem());
        }
        if (comboBoxCivility.getSelectedItem() != null) {
            customer.setCivility((Civility) comboBoxCivility.getSelectedItem());
        }
        customer.setPaymentMode((PaymentMode) comboBoxPaymentMode.getSelectedItem());
        if (!StringUtils.isNullOrEmpty(textFieldBank1.getText())) {
            customer.setBank1(textFieldBank1.getText());
        }
        if (!StringUtils.isNullOrEmpty(textFieldBank2.getText())) {
            customer.setBank2(textFieldBank2.getText());
        }

        BigDecimal vatApplicable = null;
        Object value = textFieldVatApplicable.getValue();
        if (value instanceof Long) {
            vatApplicable = BigDecimal.valueOf((Long) value);
        } else if (value instanceof Double) {
            vatApplicable = BigDecimal.valueOf((Double) value);
        } else {
            vatApplicable = (BigDecimal) value;
        }
        if (vatApplicable != null) {
            vatApplicable = vatApplicable.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            customer.setVatApplicable(vatApplicable);
        }
        if (!StringUtils.isNullOrEmpty(textFieldVatNumber.getText())) {
            customer.setVatNumber(textFieldVatNumber.getText());
        }
        String creditLimit = textFieldCreditLimit.getText();
        if (!StringUtils.isNullOrEmpty(creditLimit)) {
            BigDecimal bigDecimal = new BigDecimal(creditLimit);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            customer.setCreditLimit(bigDecimal);
        }
        Address billingAddress = new Address();
        if (!StringUtils.isNullOrEmpty(textFieldBillingBox.getText())) {
            billingAddress.setBox(textFieldBillingBox.getText());
        }
        billingAddress.setCity(textFieldBillingCity.getText());
        billingAddress.setCountry(textFieldBillingCountry.getText());
        billingAddress.setNumber(textFieldBillingNumber.getText());
        billingAddress.setStreet(textFieldBillingStreet.getText());
        if (!StringUtils.isNullOrEmpty(textFieldBillingZipCode.getText())) {
            billingAddress.setZipCode(Integer.parseInt(textFieldBillingZipCode.getText()));
        }
        customer.setBillingAddress(billingAddress);
        Address deliveryAddress = new Address();
        if (!StringUtils.isNullOrEmpty(textFieldDeliveryBox.getText())) {
            deliveryAddress.setBox(textFieldDeliveryBox.getText());
        }
        deliveryAddress.setCity(textFieldDeliveryCity.getText());
        deliveryAddress.setCountry(textFieldDeliveryCountry.getText());
        deliveryAddress.setNumber(textFieldDeliveryNumber.getText());
        deliveryAddress.setStreet(textFieldDeliveryStreet.getText());
        if (!StringUtils.isNullOrEmpty(textFieldDeliveryZipCode.getText())) {
            deliveryAddress.setZipCode(Integer.parseInt(textFieldDeliveryZipCode.getText()));
        }
        customer.setDeliveryAddress(deliveryAddress);
        String defaultDiscountRate = textFieldDefaultDiscountRate.getText();
        if (!StringUtils.isNullOrEmpty(defaultDiscountRate)) {
            BigDecimal bigDecimal = new BigDecimal(defaultDiscountRate);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            customer.setDefaultDiscountRate(bigDecimal);
        }
        if (!StringUtils.isNullOrEmpty(textAreaDescription.getText())) {
            customer.setDescription(textAreaDescription.getText());
        }
        if (!StringUtils.isNullOrEmpty(textFieldDefaultDiscountRate.getText())) {
            customer.setDefaultDiscountRate(new BigDecimal(textFieldDefaultDiscountRate.getText()));
        }
        if (!StringUtils.isNullOrEmpty(textAreaDescription.getText())) {
            customer.setDescription(textAreaDescription.getText());
        }
        ContactInformation contactInformation = new ContactInformation();
        if (!StringUtils.isNullOrEmpty(textFieldEmail.getText())) {
            contactInformation.setEmail(textFieldEmail.getText());
        }
        if (!StringUtils.isNullOrEmpty(textFieldFax.getText())) {
            contactInformation.setFax(textFieldFax.getText());
        }
        if (!StringUtils.isNullOrEmpty(textFieldMobile.getText())) {
            contactInformation.setMobile(textFieldMobile.getText());
        }
        contactInformation.setPhone(textFieldPhone.getText());
        if (!StringUtils.isNullOrEmpty(textFieldWebsite.getText())) {
            contactInformation.setWebsite(textFieldWebsite.getText());
        }
        customer.setContactInformation(contactInformation);
        super.postPerformOk(customer);
    }

    /**
     * Sets all mandatory fields.
     */
    private void setMandatoryFields() {
        ValidationComponentUtils.setMandatory(textFieldBillingCity, true);
        ValidationComponentUtils.setMandatory(textFieldBillingCountry, true);
        ValidationComponentUtils.setMandatory(textFieldBillingNumber, true);
        ValidationComponentUtils.setMandatory(textFieldBillingStreet, true);
        ValidationComponentUtils.setMandatory(textFieldBillingZipCode, true);

        ValidationComponentUtils.setMandatory(textFieldDeliveryCity, true);
        ValidationComponentUtils.setMandatory(textFieldDeliveryCountry, true);
        ValidationComponentUtils.setMandatory(textFieldDeliveryNumber, true);
        ValidationComponentUtils.setMandatory(textFieldDeliveryStreet, true);
        ValidationComponentUtils.setMandatory(textFieldDeliveryZipCode, true);

        ValidationComponentUtils.setMandatory(textFieldName, true);
        ValidationComponentUtils.setMandatory(textFieldPhone, true);
        ValidationComponentUtils.setMandatory(textFieldVatApplicable, true);

        ValidationComponentUtils.setMandatory(comboBoxPaymentMode, true);
        comboBoxPaymentMode
                .setBorder(BorderFactory.createLineBorder(ValidationComponentUtils.getMandatoryForeground()));
    }

}
