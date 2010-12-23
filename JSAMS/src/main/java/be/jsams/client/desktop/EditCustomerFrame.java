package be.jsams.client.desktop;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.GenericComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButtonsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Customer;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Edit Customer {@link JsamsButtonsFrame}, to create or update a Customer
 * object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class EditCustomerFrame extends JsamsButtonsFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6898471936119469349L;

	private static final int DEFAULT_COLUMN_SPAN = 1;

	private static final int MAX_CHARACTERS = 50;

	private static final int MAX_NUMBERS = 10;

	public JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_NAME);

	public JComboBox comboBoxLegalForm;

	public JComboBox comboBoxCivility;

	public JComboBox comboBoxPaymentMode;

	public JsamsTextField textFieldVatNumber = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_VAT_NUMBER);

	public JsamsTextField textFieldVatApplicable = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_VAT_APPLICABLE);

	public JsamsTextField textFieldBank1 = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_BANK1);

	public JsamsTextField textFieldBank2 = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_BANK2);

	public JsamsTextField textFieldCreditLimit = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_CREDIT_LIMIT);

	public JsamsTextField textFieldAgent = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_AGENT);

	public JsamsTextField textFieldBillingStreet = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_STREET);

	public JsamsTextField textFieldBillingNumber = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_NUMBER);

	public JsamsTextField textFieldBillingBox = new JsamsTextField(MAX_NUMBERS,
			JsamsI18nResource.LABEL_BOX);

	public JsamsTextField textFieldBillingZipCode = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_ZIP_CODE);

	public JsamsTextField textFieldBillingCity = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_CITY);

	public JsamsTextField textFieldBillingCountry = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_COUNTRY);

	public JsamsTextField textFieldDeliveryStreet = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_STREET);

	public JsamsTextField textFieldDeliveryNumber = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_NUMBER);

	public JsamsTextField textFieldDeliveryBox = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_BOX);

	public JsamsTextField textFieldDeliveryZipCode = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_ZIP_CODE);

	public JsamsTextField textFieldDeliveryCity = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_CITY);

	public JsamsTextField textFieldDeliveryCountry = new JsamsTextField(
			MAX_CHARACTERS, JsamsI18nResource.LABEL_COUNTRY);

	public JsamsTextField textFieldDefaultDiscountRate = new JsamsTextField(5,
			JsamsI18nResource.LABEL_DEFAULT_DISCOUNT_RATE);

	public JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_PHONE);

	public JsamsTextField textFieldFax = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_FAX);

	public JsamsTextField textFieldMobile = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_MOBILE);

	public JsamsTextField textFieldEmail = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_EMAIL);

	public JsamsTextField textFieldWebsite = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_WEBSITE);

	public JTextArea textAreaDescription = new JTextArea();

	private Customer model;

	private JTabbedPane tabbedPane;

	private JPanel generalPanel;

	private JPanel billingAddressPanel;

	private JPanel deliveryAddressPanel;

	private JPanel contactInformationsPanel;

	private JPanel miscPanel;

	public EditCustomerFrame(final I18nString title, Customer model) {
		super(title, IconUtil.TITLE_ICON_PREFIX + "apps/system-users.png");
		this.model = model;
		initComponents();
	}

	public Customer getModel() {
		return model;
	}

	public void setModel(Customer model) {
		this.model = model;
	}

	private void initComponents() {
		setTitle(JsamsI18nResource.TITLE_EDIT_CUSTOMER);
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
		pack();
	}

	private void fillData() {
		List<LegalForm> allLegalForms = JsamsApplicationContext
				.getLegalFormDao().findAll();
		comboBoxLegalForm = new JComboBox(allLegalForms.toArray());
		List<Civility> allCivilities = JsamsApplicationContext.getCivilityDao()
				.findAll();
		comboBoxCivility = new JComboBox(allCivilities.toArray());
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
		comboBoxLegalForm.setRenderer(new GenericComboBoxRenderer());
		comboBoxCivility.setRenderer(new GenericComboBoxRenderer());
		comboBoxPaymentMode.setRenderer(new GenericComboBoxRenderer());
	}

	private JPanel buildGeneralTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 100dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.append(textFieldName.getLabel(), textFieldName,
				DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_LEGAL_FORM.getTranslation(),
				comboBoxLegalForm, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_CIVILITY.getTranslation(),
				comboBoxCivility, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(textFieldVatNumber.getLabel(), textFieldVatNumber,
				DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(textFieldBank1.getLabel(), textFieldBank1,
				DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(textFieldBank2.getLabel(), textFieldBank2,
				DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(textFieldCreditLimit.getLabel(), textFieldCreditLimit,
				DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(textFieldVatApplicable.getLabel(),
				textFieldVatApplicable, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_PAYMENT_MODE.getTranslation(),
				comboBoxPaymentMode, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.append(textFieldAgent.getLabel(), textFieldAgent,
				DEFAULT_COLUMN_SPAN);
		return builder.getPanel();
	}

	private JPanel buildBillingAddressTab() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.append(textFieldBillingStreet.getLabel(),
				textFieldBillingStreet, 9);
		builder.nextLine();
		builder.append(textFieldBillingNumber.getLabel(),
				textFieldBillingNumber, 1);
		builder.append(textFieldBillingBox.getLabel(), textFieldBillingBox, 1);
		builder.append(textFieldBillingZipCode.getLabel(),
				textFieldBillingZipCode, 1);
		builder
				.append(textFieldBillingCity.getLabel(), textFieldBillingCity,
						9);
		builder.append(textFieldBillingCountry.getLabel(),
				textFieldBillingCountry, 9);
		return builder.getPanel();
	}

	private JPanel buildDeliveryAddressTab() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.append(textFieldDeliveryStreet.getLabel(),
				textFieldDeliveryStreet, 9);
		builder.nextLine();
		builder.append(textFieldDeliveryNumber.getLabel(),
				textFieldDeliveryNumber, 1);
		builder
				.append(textFieldDeliveryBox.getLabel(), textFieldDeliveryBox,
						1);
		builder.append(textFieldDeliveryZipCode.getLabel(),
				textFieldDeliveryZipCode, 1);
		builder.append(textFieldDeliveryCity.getLabel(), textFieldDeliveryCity,
				9);
		builder.append(textFieldDeliveryCountry.getLabel(),
				textFieldDeliveryCountry, 9);
		return builder.getPanel();
	}

	private JPanel buildContactInformationsTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 150dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.append(textFieldPhone.getLabel(), textFieldPhone);
		builder.nextLine();
		builder.append(textFieldFax.getLabel(), textFieldFax);
		builder.nextLine();
		builder.append(textFieldMobile.getLabel(), textFieldMobile);
		builder.nextLine();
		builder.append(textFieldEmail.getLabel(), textFieldEmail);
		builder.nextLine();
		builder.append(textFieldWebsite.getLabel(), textFieldWebsite);
		return builder.getPanel();
	}

	private JPanel buildMiscTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 150dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setDefaultDialogBorder();
		builder.append(textFieldDefaultDiscountRate.getLabel(),
				textFieldDefaultDiscountRate);
		builder.nextLine();
		textAreaDescription.setRows(5);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		JScrollPane areaScrollPane = new JScrollPane(textAreaDescription);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		builder.append(JsamsI18nResource.LABEL_DESCRIPTION.getTranslation(),
				areaScrollPane);
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

	@Override
	protected void performCancel() {
		this.dispose();
	}

	@Override
	protected void performOk() {
		if (getModel() == null) {
			// Create new one
			Customer newCustomer = new Customer();
			// TODO: see for agent object
			newCustomer.setName(textFieldName.getText());
			newCustomer.setLegalForm((LegalForm) comboBoxLegalForm
					.getSelectedItem());
			newCustomer.setCivility((Civility) comboBoxCivility
					.getSelectedItem());
			newCustomer.setPaymentMode((PaymentMode) comboBoxPaymentMode
					.getSelectedItem());
			newCustomer.setBank1(textFieldBank1.getText());
			newCustomer.setBank2(textFieldBank2.getText());
			newCustomer.setVatApplicable(new BigDecimal(textFieldVatApplicable
					.getText()));
			newCustomer.setVatNumber(textFieldVatNumber.getText());
			newCustomer.setCreditLimit(new BigDecimal(textFieldCreditLimit
					.getText()));
			Address billingAddress = new Address();
			billingAddress.setBox(textFieldBillingBox.getText());
			billingAddress.setCity(textFieldBillingCity.getText());
			billingAddress.setCountry(textFieldBillingCountry.getText());
			billingAddress.setNumber(textFieldBillingNumber.getText());
			billingAddress.setStreet(textFieldBillingStreet.getText());
			billingAddress.setZipCode(Integer.parseInt(textFieldBillingZipCode
					.getText()));
			newCustomer.setBillingAddress(billingAddress);
			Address deliveryAddress = new Address();
			deliveryAddress.setBox(textFieldDeliveryBox.getText());
			deliveryAddress.setCity(textFieldDeliveryCity.getText());
			deliveryAddress.setCountry(textFieldDeliveryCountry.getText());
			deliveryAddress.setNumber(textFieldDeliveryNumber.getText());
			deliveryAddress.setStreet(textFieldDeliveryStreet.getText());
			deliveryAddress.setZipCode(Integer
					.parseInt(textFieldDeliveryZipCode.getText()));
			newCustomer.setDeliveryAddress(deliveryAddress);

			newCustomer.setDefaultDiscountRate(new BigDecimal(
					textFieldDefaultDiscountRate.getText()));
			newCustomer.setDescription(textAreaDescription.getText());

			ContactInformation contactInformation = new ContactInformation();
			contactInformation.setEmail(textFieldEmail.getText());
			contactInformation.setFax(textFieldFax.getText());
			contactInformation.setMobile(textFieldMobile.getText());
			contactInformation.setPhone(textFieldPhone.getText());
			contactInformation.setWebsite(textFieldWebsite.getText());
			newCustomer.setContactInformation(contactInformation);
			JsamsApplicationContext.getCustomerService().create(newCustomer);
		} else {
			// Update the current customer
			Customer updatedCustomer = new Customer();
			// TODO: see for agent object
			updatedCustomer.setName(textFieldName.getText());
			updatedCustomer.setLegalForm((LegalForm) comboBoxLegalForm
					.getSelectedItem());
			updatedCustomer.setCivility((Civility) comboBoxCivility
					.getSelectedItem());
			updatedCustomer.setPaymentMode((PaymentMode) comboBoxPaymentMode
					.getSelectedItem());
			updatedCustomer.setBank1(textFieldBank1.getText());
			updatedCustomer.setBank2(textFieldBank2.getText());
			updatedCustomer.setVatApplicable(new BigDecimal(textFieldVatApplicable
					.getText()));
			updatedCustomer.setVatNumber(textFieldVatNumber.getText());
			updatedCustomer.setCreditLimit(new BigDecimal(textFieldCreditLimit
					.getText()));
			Address billingAddress = new Address();
			billingAddress.setBox(textFieldBillingBox.getText());
			billingAddress.setCity(textFieldBillingCity.getText());
			billingAddress.setCountry(textFieldBillingCountry.getText());
			billingAddress.setNumber(textFieldBillingNumber.getText());
			billingAddress.setStreet(textFieldBillingStreet.getText());
			billingAddress.setZipCode(Integer.parseInt(textFieldBillingZipCode
					.getText()));
			updatedCustomer.setBillingAddress(billingAddress);
			Address deliveryAddress = new Address();
			deliveryAddress.setBox(textFieldDeliveryBox.getText());
			deliveryAddress.setCity(textFieldDeliveryCity.getText());
			deliveryAddress.setCountry(textFieldDeliveryCountry.getText());
			deliveryAddress.setNumber(textFieldDeliveryNumber.getText());
			deliveryAddress.setStreet(textFieldDeliveryStreet.getText());
			deliveryAddress.setZipCode(Integer
					.parseInt(textFieldDeliveryZipCode.getText()));
			updatedCustomer.setDeliveryAddress(deliveryAddress);

			updatedCustomer.setDefaultDiscountRate(new BigDecimal(
					textFieldDefaultDiscountRate.getText()));
			updatedCustomer.setDescription(textAreaDescription.getText());

			ContactInformation contactInformation = new ContactInformation();
			contactInformation.setEmail(textFieldEmail.getText());
			contactInformation.setFax(textFieldFax.getText());
			contactInformation.setMobile(textFieldMobile.getText());
			contactInformation.setPhone(textFieldPhone.getText());
			contactInformation.setWebsite(textFieldWebsite.getText());
			updatedCustomer.setContactInformation(contactInformation);

			if (!getModel().equals(updatedCustomer)) {
				JsamsApplicationContext.getCustomerService().update(
						updatedCustomer);
			}
		}
		dispose();
	}

	@Override
	protected void performReset() {
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			try {
				Object value = field.get(this);
				if (value instanceof JsamsTextField) {
					((JsamsTextField) value).setText(null);
				} else if (value instanceof JComboBox) {
					((JComboBox) value).setSelectedIndex(0);
				} else if (value instanceof JTextArea) {
					((JTextArea) value).setText(null);
				}
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
	}

}
