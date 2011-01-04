package be.jsams.client.model.dialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsDialog;
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
 * Edit Customer {@link JsamsDialog}, to create or update a Customer object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditCustomerDialog extends EditDialog<Customer, CustomerValidator, CustomerService> {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6898471936119469349L;

	protected final Log LOGGER = LogFactory.getLog(EditCustomerDialog.class);

	private static final int DEFAULT_COLUMN_SPAN = 1;

	private static final int MAX_CHARACTERS = 50;

	private static final int MAX_NUMBERS = 10;

	public JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);

	public JComboBox comboBoxLegalForm;

	public JComboBox comboBoxCivility;

	public JComboBox comboBoxPaymentMode;

	public JsamsTextField textFieldVatNumber = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldVatApplicable = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldBank1 = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldBank2 = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldCreditLimit = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldAgent = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldBillingStreet = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldBillingNumber = new JsamsTextField(
			MAX_NUMBERS);

	public JsamsTextField textFieldBillingBox = new JsamsTextField(MAX_NUMBERS);

	public JsamsTextField textFieldBillingZipCode = new JsamsTextField(
			MAX_NUMBERS);

	public JsamsTextField textFieldBillingCity = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldBillingCountry = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldDeliveryStreet = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldDeliveryNumber = new JsamsTextField(
			MAX_NUMBERS);

	public JsamsTextField textFieldDeliveryBox = new JsamsTextField(MAX_NUMBERS);

	public JsamsTextField textFieldDeliveryZipCode = new JsamsTextField(
			MAX_NUMBERS);

	public JsamsTextField textFieldDeliveryCity = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldDeliveryCountry = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldDefaultDiscountRate = new JsamsTextField(5);

	public JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldFax = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldMobile = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldEmail = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldWebsite = new JsamsTextField(MAX_CHARACTERS);

	public JTextArea textAreaDescription = new JTextArea();

	private JTabbedPane tabbedPane;

	private JPanel generalPanel;

	private JPanel billingAddressPanel;

	private JPanel deliveryAddressPanel;

	private JPanel contactInformationsPanel;

	private JPanel miscPanel;

	/**
	 * Constructor
	 * 
	 * @param parent
	 *            the {@link JsamsMainFrame}
	 * @param title
	 *            the {@link I18nString} title
	 * @param model
	 *            the {@link Customer} model
	 */
	public EditCustomerDialog(final JsamsMainFrame parent,
			final I18nString title, Customer model) {
		super(parent, title, IconUtil.TITLE_ICON_PREFIX
				+ "apps/system-users.png");
		super.setModel(model);
		super.setValidator(new CustomerValidator());
		super.setService(JsamsApplicationContext.getCustomerService());
		initComponents();
	}

	/**
	 * Initializes all the components
	 */
	private void initComponents() {
		fillData();
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		generalPanel = buildGeneralTab();
		tabbedPane.add(JsamsI18nResource.PANEL_GENERAL.getTranslation(),
				generalPanel);
		billingAddressPanel = buildBillingAddressTab();
		tabbedPane.add(
				JsamsI18nResource.PANEL_BILLING_ADDRESS.getTranslation(),
				billingAddressPanel);
		deliveryAddressPanel = buildDeliveryAddressTab();
		tabbedPane.add(JsamsI18nResource.PANEL_DELIVERY_ADDRESS
				.getTranslation(), deliveryAddressPanel);
		contactInformationsPanel = buildContactInformationsTab();
		tabbedPane.add(JsamsI18nResource.PANEL_CONTACT_INFORMATIONS
				.getTranslation(), contactInformationsPanel);
		miscPanel = buildMiscTab();
		tabbedPane
				.add(JsamsI18nResource.PANEL_MISC.getTranslation(), miscPanel);
		getContentPane().add(tabbedPane);

		setMandatoryFields();

		ValidationComponentUtils
				.updateComponentTreeMandatoryBackground(this);

		pack();
	}

	private void fillData() {
		List<LegalForm> allLegalForms = JsamsApplicationContext
				.getLegalFormDao().findAll();
		ArrayList<LegalForm> allWithNull = new ArrayList<LegalForm>();
		allWithNull.add(null);
		allWithNull.addAll(allLegalForms);
		comboBoxLegalForm = new JComboBox(allWithNull.toArray());
		List<Civility> allCivilities = JsamsApplicationContext.getCivilityDao()
				.findAll();
		ArrayList<Civility> allWithNullCivilities = new ArrayList<Civility>();
		allWithNullCivilities.add(null);
		allWithNullCivilities.addAll(allCivilities);
		comboBoxCivility = new JComboBox(allWithNullCivilities.toArray());
		List<PaymentMode> allPaymentModes = JsamsApplicationContext
				.getPaymentModeDao().findAll();
		comboBoxPaymentMode = new JComboBox(allPaymentModes.toArray());

		if (getModel() != null) {
			fillBillingAddress();
			fillDeliveryAddress();
			fillContactInformation();
			fillMisc();
			comboBoxLegalForm.setSelectedItem(getModel().getLegalForm());
			textFieldName.setText(getModel().getName());
			textFieldVatNumber.setText(getModel().getVatNumber());
		}

		comboBoxLegalForm.setRenderer(new TranslatableComboBoxRenderer());
		comboBoxCivility.setRenderer(new TranslatableComboBoxRenderer());
		comboBoxPaymentMode.setRenderer(new TranslatableComboBoxRenderer());
	}

	private JPanel buildGeneralTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, p:grow", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(),
				textFieldName, 2);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(),
				comboBoxLegalForm, 2);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(),
				comboBoxCivility, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getKey(),
				textFieldVatNumber, 2);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_BANK1.getKey(),
				textFieldBank1, 2);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_BANK2.getKey(),
				textFieldBank2, 2);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_CREDIT_LIMIT.getKey(),
				textFieldCreditLimit, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(
				JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getKey(),
				textFieldVatApplicable, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(),
				comboBoxPaymentMode, 2);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_AGENT.getKey(),
				textFieldAgent, 2);
		return builder.getPanel();
	}

	private JPanel buildBillingAddressTab() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(),
				textFieldBillingStreet, 9);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(),
				textFieldBillingNumber, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(),
				textFieldBillingBox, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(),
				textFieldBillingZipCode, 1);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(),
				textFieldBillingCity, 9);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(),
				textFieldBillingCountry, 9);
		return builder.getPanel();
	}

	private JPanel buildDeliveryAddressTab() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(),
				textFieldDeliveryStreet, 9);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(),
				textFieldDeliveryNumber, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(),
				textFieldDeliveryBox, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(),
				textFieldDeliveryZipCode, 1);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(),
				textFieldDeliveryCity, 9);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(),
				textFieldDeliveryCountry, 9);
		return builder.getPanel();
	}

	private JPanel buildContactInformationsTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 100dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(),
				textFieldPhone);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_FAX.getKey(),
				textFieldFax);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_MOBILE.getKey(),
				textFieldMobile);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_EMAIL.getKey(),
				textFieldEmail);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_WEBSITE.getKey(),
				textFieldWebsite);
		return builder.getPanel();
	}

	private JPanel buildMiscTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, p:grow", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_DEFAULT_DISCOUNT_RATE
				.getKey(), textFieldDefaultDiscountRate, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		textAreaDescription.setRows(5);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		JScrollPane areaScrollPane = new JScrollPane(textAreaDescription);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		builder.appendI15d(JsamsI18nLabelResource.LABEL_DESCRIPTION.getKey(),
				areaScrollPane, 2);
		return builder.getPanel();
	}

	private void fillBillingAddress() {
		Address billingAddress = getModel().getBillingAddress();
		textFieldBillingBox.setText(billingAddress.getBox());
		textFieldBillingCity.setText(billingAddress.getCity());
		textFieldBillingCountry.setText(billingAddress.getCountry());
		textFieldBillingNumber.setText(billingAddress.getNumber());
		textFieldBillingStreet.setText(billingAddress.getStreet());
		textFieldBillingZipCode.setText(Integer.toString(billingAddress
				.getZipCode()));
	}

	private void fillDeliveryAddress() {
		Address deliveryAddress = getModel().getDeliveryAddress();
		textFieldDeliveryBox.setText(deliveryAddress.getBox());
		textFieldDeliveryCity.setText(deliveryAddress.getCity());
		textFieldDeliveryCountry.setText(deliveryAddress.getCountry());
		textFieldDeliveryNumber.setText(deliveryAddress.getNumber());
		textFieldDeliveryStreet.setText(deliveryAddress.getStreet());
		textFieldDeliveryZipCode.setText(Integer.toString(deliveryAddress
				.getZipCode()));
	}

	private void fillContactInformation() {
		ContactInformation contactInformation = getModel()
				.getContactInformation();
		textFieldPhone.setText(contactInformation.getPhone());
		textFieldFax.setText(contactInformation.getFax());
		textFieldMobile.setText(contactInformation.getMobile());
		textFieldEmail.setText(contactInformation.getEmail());
		textFieldWebsite.setText(contactInformation.getWebsite());

	}

	private void fillMisc() {
		textFieldDefaultDiscountRate.setText(getModel()
				.getDefaultDiscountRate().toPlainString());
		textAreaDescription.setText(getModel().getDescription());
	}

	public void performOk() {
		Customer customer = new Customer();
		// TODO: see for agent object
		customer.setName(textFieldName.getText());
		if (comboBoxLegalForm.getSelectedItem() != null) {
			customer.setLegalForm((LegalForm) comboBoxLegalForm
					.getSelectedItem());
		}
		if (comboBoxCivility.getSelectedItem() != null) {
			customer.setCivility((Civility) comboBoxCivility.getSelectedItem());
		}
		customer.setPaymentMode((PaymentMode) comboBoxPaymentMode
				.getSelectedItem());
		if (!StringUtils.isNullOrEmpty(textFieldBank1.getText())) {
			customer.setBank1(textFieldBank1.getText());
		}
		if (!StringUtils.isNullOrEmpty(textFieldBank2.getText())) {
			customer.setBank2(textFieldBank2.getText());
		}
		if (!StringUtils.isNullOrEmpty(textFieldVatApplicable.getText())) {
			customer.setVatApplicable(new BigDecimal(textFieldVatApplicable
					.getText()));
		}

		if (!StringUtils.isNullOrEmpty(textFieldVatNumber.getText())) {
			customer.setVatNumber(textFieldVatNumber.getText());
		}
		if (!StringUtils.isNullOrEmpty(textFieldCreditLimit.getText())) {
			customer.setCreditLimit(new BigDecimal(textFieldCreditLimit
					.getText()));
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
			billingAddress.setZipCode(Integer.parseInt(textFieldBillingZipCode
					.getText()));
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
			deliveryAddress.setZipCode(Integer
					.parseInt(textFieldDeliveryZipCode.getText()));
		}
		customer.setDeliveryAddress(deliveryAddress);

		if (!StringUtils.isNullOrEmpty(textFieldDefaultDiscountRate.getText())) {
			customer.setDefaultDiscountRate(new BigDecimal(
					textFieldDefaultDiscountRate.getText()));
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
//		TODO: find a way to color the background of combobox into windows l&f
	}

}
