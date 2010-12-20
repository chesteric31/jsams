package be.jsams.client.desktop;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.LegalFormComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButtonsFrame;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Edit society {@link JsamsFrame}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditSocietyFrame extends JsamsButtonsFrame {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4225744372592399187L;

	private static final int MAX_COLUMN_SPAN = 9;

	private static final int MAX_CHARACTERS = 50;

	private static final int MAX_NUMBERS = 10;

	private LegalFormDao legalFormDao;

	public JTextField textFieldName = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldStreet = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldNumber = new JTextField(MAX_NUMBERS);

	public JTextField textFieldBox = new JTextField(MAX_NUMBERS);

	public JTextField textFieldZipCode = new JTextField(MAX_NUMBERS);

	public JTextField textFieldCity = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldCountry = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldPhone = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldFax = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldMobile = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldEmail = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldWebsite = new JTextField(MAX_CHARACTERS);

	public JComboBox comboBoxLegalForm;

	public JTextField textFieldCapital = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldActivity = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldResponsible = new JTextField(MAX_CHARACTERS);

	public JTextField textFieldVatNumber = new JTextField(MAX_CHARACTERS);

	private Society model;

	public EditSocietyFrame(final I18nString title, Society model) {
		super(title);
		this.model = model;
		initComponents();
	}

	public Society getModel() {
		return model;
	}

	public void setModel(Society model) {
		this.model = model;
	}

	public LegalFormDao getLegalFormDao() {
		return legalFormDao;
	}

	public void setLegalFormDao(LegalFormDao legalFormDao) {
		this.legalFormDao = legalFormDao;
	}

	private void initComponents() {
		setTitle(JsamsI18nResource.TITLE_EDIT_SOCIETY);
		fillData();
		FormLayout layoutAddress = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layoutAddress);
		builder.setDefaultDialogBorder();
		builder.append(JsamsI18nResource.LABEL_NAME.getTranslation(),
				textFieldName, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nResource.LABEL_ADDRESS
				.getTranslation());
		builder.append(JsamsI18nResource.LABEL_STREET.getTranslation(),
				textFieldStreet, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_NUMBER.getTranslation(),
				textFieldNumber);
		builder.append(JsamsI18nResource.LABEL_BOX.getTranslation(),
				textFieldBox, 1);
		builder.append(JsamsI18nResource.LABEL_ZIP_CODE.getTranslation(),
				textFieldZipCode, 1);
		builder.append(JsamsI18nResource.LABEL_CITY.getTranslation(),
				textFieldCity, MAX_COLUMN_SPAN);
		builder.append(JsamsI18nResource.LABEL_COUNTRY.getTranslation(),
				textFieldCountry, MAX_COLUMN_SPAN);
		builder.nextLine();

		builder.appendSeparator(JsamsI18nResource.LABEL_CONTACT_INFORMATIONS
				.getTranslation());
		builder.append(JsamsI18nResource.LABEL_PHONE.getTranslation(),
				textFieldPhone, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_FAX.getTranslation(),
				textFieldFax, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_MOBILE.getTranslation(),
				textFieldMobile, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_EMAIL.getTranslation(),
				textFieldEmail, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_WEBSITE.getTranslation(),
				textFieldWebsite, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nResource.LABEL_MISC.getTranslation());

		builder.append(JsamsI18nResource.LABEL_LEGAL_FORM.getTranslation(),
				comboBoxLegalForm, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_CAPITAL.getTranslation(),
				textFieldCapital, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_ACTIVITY.getTranslation(),
				textFieldActivity, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_RESPONSIBLE.getTranslation(),
				textFieldResponsible, MAX_COLUMN_SPAN);
		builder.nextLine();
		builder.append(JsamsI18nResource.LABEL_VAT_NUMBER.getTranslation(),
				textFieldVatNumber, MAX_COLUMN_SPAN);
		builder.nextLine();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), BorderLayout.CENTER);
		add(mainPanel);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	@Override
	protected void performCancel() {
		this.dispose();
	}

	@Override
	protected void performOk() {
		if (getModel() == null) {
			// Create new one
			Society newSociety = new Society();
			newSociety.setActivity(textFieldActivity.getText());
			Address address = new Address();
			address.setBox(textFieldBox.getText());
			address.setCity(textFieldCity.getText());
			address.setCountry(textFieldCountry.getText());
			address.setNumber(textFieldNumber.getText());
			address.setStreet(textFieldStreet.getText());
			address.setZipCode(Integer.parseInt(textFieldZipCode.getText()));
			newSociety.setAddress(address);
			newSociety.setCapital(new BigDecimal(textFieldCapital.getText()));
			ContactInformation contactInformation = new ContactInformation();
			contactInformation.setEmail(textFieldEmail.getText());
			contactInformation.setFax(textFieldFax.getText());
			contactInformation.setMobile(textFieldMobile.getText());
			contactInformation.setPhone(textFieldPhone.getText());
			contactInformation.setWebsite(textFieldWebsite.getText());
			newSociety.setContactInformation(contactInformation);
			newSociety.setLegalForm((LegalForm) comboBoxLegalForm
					.getSelectedItem());
			newSociety.setName(textFieldName.getText());
			newSociety.setResponsible(textFieldResponsible.getText());
			newSociety.setVatNumber(textFieldVatNumber.getText());
			JsamsApplicationContext.getSocietyService().create(newSociety);
		} else {
			// Update the current society
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
				if (value instanceof JTextField) {
					((JTextField) value).setText(null);
				} else if (value instanceof JComboBox) {
					((JComboBox) value).setSelectedIndex(0);
				}
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void fillData() {
		List<LegalForm> allLegalForms = JsamsApplicationContext
				.getLegalFormService().findAll();
		comboBoxLegalForm = new JComboBox(allLegalForms.toArray());
		if (getModel() != null) {
			Society society = JsamsApplicationContext.getSocietyService()
					.findById(getModel().getId());
			fillAddress(society);
			fillContactInformation(society);
			comboBoxLegalForm.setSelectedItem(society.getLegalForm());
			System.out.println(comboBoxLegalForm.getSelectedItem());
			textFieldActivity.setText(society.getActivity());
			textFieldCapital.setText(society.getCapital().toPlainString());
			textFieldName.setText(society.getName());
			textFieldResponsible.setText(society.getResponsible());
			textFieldVatNumber.setText(society.getVatNumber());
		}
		comboBoxLegalForm.setRenderer(new LegalFormComboBoxRenderer());
	}

	private void fillAddress(final Society society) {
		Address address = society.getAddress();
		textFieldBox.setText(address.getBox());
		textFieldCity.setText(address.getCity());
		textFieldCountry.setText(address.getCountry());
		textFieldNumber.setText(address.getNumber());
		textFieldStreet.setText(address.getStreet());
		textFieldZipCode.setText(Integer.toString(address.getZipCode()));
	}

	private void fillContactInformation(final Society society) {
		ContactInformation information = society.getContactInformation();
		textFieldEmail.setText(information.getEmail());
		textFieldFax.setText(information.getFax());
		textFieldMobile.setText(information.getMobile());
		textFieldPhone.setText(information.getPhone());
		textFieldWebsite.setText(information.getWebsite());
	}

}
