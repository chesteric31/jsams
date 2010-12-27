package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
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
 * Edit Customer {@link JsamsDialog}, to create or update a Customer object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class EditCustomerDialog extends JsamsDialog implements
		JsamsButtonsInterface {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 6898471936119469349L;

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

	private Customer model;

	private JTabbedPane tabbedPane;

	private JPanel generalPanel;

	private JPanel billingAddressPanel;

	private JPanel deliveryAddressPanel;

	private JPanel contactInformationsPanel;

	private JPanel miscPanel;

	private JsamsButtonsPanel buttonsPanel;

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
		this.model = model;
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		add(buttonsPanel, BorderLayout.SOUTH);
		initComponents();
	}

	/**
	 * 
	 * @return the {@link Customer}
	 */
	public Customer getModel() {
		return model;
	}

	/**
	 * 
	 * @param model the {@link Customer} to set
	 */
	public void setModel(Customer model) {
		this.model = model;
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
		comboBoxLegalForm.setRenderer(new TranslatableComboBoxRenderer());
		comboBoxCivility.setRenderer(new TranslatableComboBoxRenderer());
		comboBoxPaymentMode.setRenderer(new TranslatableComboBoxRenderer());
	}

	private JPanel buildGeneralTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 100dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nResource.LABEL_NAME.getKey(),
				textFieldName, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_LEGAL_FORM.getKey(),
				comboBoxLegalForm, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_CIVILITY.getKey(),
				comboBoxCivility, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_VAT_NUMBER.getKey(),
				textFieldVatNumber, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_BANK1.getKey(),
				textFieldBank1, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_BANK2.getKey(),
				textFieldBank2, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_CREDIT_LIMIT.getKey(),
				textFieldCreditLimit, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_VAT_APPLICABLE.getKey(),
				textFieldVatApplicable, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_PAYMENT_MODE.getKey(),
				comboBoxPaymentMode, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_AGENT.getKey(),
				textFieldAgent, DEFAULT_COLUMN_SPAN);
		return builder.getPanel();
	}

	private JPanel buildBillingAddressTab() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nResource.LABEL_STREET.getKey(),
				textFieldBillingStreet, 9);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_NUMBER.getKey(),
				textFieldBillingNumber, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_BOX.getKey(),
				textFieldBillingBox, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_ZIP_CODE.getKey(),
				textFieldBillingZipCode, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_CITY.getKey(),
				textFieldBillingCity, 9);
		builder.appendI15d(JsamsI18nResource.LABEL_COUNTRY.getKey(),
				textFieldBillingCountry, 9);
		return builder.getPanel();
	}

	private JPanel buildDeliveryAddressTab() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nResource.LABEL_STREET.getKey(),
				textFieldDeliveryStreet, 9);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_NUMBER.getKey(),
				textFieldDeliveryNumber, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_BOX.getKey(),
				textFieldDeliveryBox, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_ZIP_CODE.getKey(),
				textFieldDeliveryZipCode, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_CITY.getKey(),
				textFieldDeliveryCity, 9);
		builder.appendI15d(JsamsI18nResource.LABEL_COUNTRY.getKey(),
				textFieldDeliveryCountry, 9);
		return builder.getPanel();
	}

	private JPanel buildContactInformationsTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 150dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nResource.LABEL_PHONE.getKey(),
				textFieldPhone);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_FAX.getKey(), textFieldFax);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_MOBILE.getKey(),
				textFieldMobile);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_EMAIL.getKey(),
				textFieldEmail);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_WEBSITE.getKey(),
				textFieldWebsite);
		return builder.getPanel();
	}

	private JPanel buildMiscTab() {
		FormLayout layout = new FormLayout("right:p, 3dlu, 150dlu", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nResource.LABEL_DEFAULT_DISCOUNT_RATE
				.getKey(), textFieldDefaultDiscountRate);
		builder.nextLine();
		textAreaDescription.setRows(5);
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		JScrollPane areaScrollPane = new JScrollPane(textAreaDescription);
		areaScrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		builder.appendI15d(JsamsI18nResource.LABEL_DESCRIPTION.getKey(),
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

	public void performCancel() {
		this.dispose();
	}

	public void performOk() {
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
			updatedCustomer.setVatApplicable(new BigDecimal(
					textFieldVatApplicable.getText()));
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

	public void performReset() {
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
