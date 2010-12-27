package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Edit society {@link JsamsDialog}, to create or update a Society object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditSocietyDialog extends JsamsDialog implements
		JsamsButtonsInterface {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4225744372592399187L;

	private static final int DEFAULT_COLUMN_SPAN = 9;

	private static final int MAX_CHARACTERS = 50;

	private static final int MAX_NUMBERS = 10;

	public JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldStreet = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldNumber = new JsamsTextField(MAX_NUMBERS);

	public JsamsTextField textFieldBox = new JsamsTextField(MAX_NUMBERS);

	public JsamsTextField textFieldZipCode = new JsamsTextField(MAX_NUMBERS);

	public JsamsTextField textFieldCity = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldCountry = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldFax = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldMobile = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldEmail = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldWebsite = new JsamsTextField(MAX_CHARACTERS);

	public JComboBox comboBoxLegalForm;

	public JsamsTextField textFieldCapital = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldActivity = new JsamsTextField(MAX_CHARACTERS);

	public JsamsTextField textFieldResponsible = new JsamsTextField(
			MAX_CHARACTERS);

	public JsamsTextField textFieldVatNumber = new JsamsTextField(
			MAX_CHARACTERS);

	private Society model;

	private JsamsButtonsPanel buttonsPanel;

	/**
	 * Constructor
	 * 
	 * @param parent
	 *            the {@link JsamsMainFrame}
	 * @param title
	 *            the {@link I18nString} title
	 * @param model
	 *            the {@link Society} model
	 */
	public EditSocietyDialog(final JsamsMainFrame parent,
			final I18nString title, Society model) {
		super(parent, title);
		this.model = model;
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		add(buttonsPanel, BorderLayout.SOUTH);
		initComponents();
	}

	/**
	 * 
	 * @return the {@link Society}
	 */
	public Society getModel() {
		return model;
	}

	/**
	 * 
	 * @param model the {@link Society} to set
	 */
	public void setModel(Society model) {
		this.model = model;
	}

	/**
	 * Initializes all the components
	 */
	private void initComponents() {
		fillData();
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu",
				"p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.setDefaultDialogBorder();
		builder.appendI15d(JsamsI18nResource.LABEL_NAME.getKey(),
				textFieldName, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nResource.LABEL_ADDRESS
				.getTranslation());
		builder.appendI15d(JsamsI18nResource.LABEL_STREET.getKey(),
				textFieldStreet, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_NUMBER.getKey(),
				textFieldNumber, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_BOX.getKey(), textFieldBox,
				1);
		builder.appendI15d(JsamsI18nResource.LABEL_ZIP_CODE.getKey(),
				textFieldZipCode, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_CITY.getKey(),
				textFieldCity, DEFAULT_COLUMN_SPAN);
		builder.appendI15d(JsamsI18nResource.LABEL_COUNTRY.getKey(),
				textFieldCountry, DEFAULT_COLUMN_SPAN);
		builder.nextLine();

		builder.appendSeparator(JsamsI18nResource.LABEL_CONTACT_INFORMATIONS
				.getTranslation());
		builder.appendI15d(JsamsI18nResource.LABEL_PHONE.getKey(),
				textFieldPhone, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_FAX.getKey(), textFieldFax,
				DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_MOBILE.getKey(),
				textFieldMobile, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_EMAIL.getKey(),
				textFieldEmail, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_WEBSITE.getKey(),
				textFieldWebsite, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nResource.LABEL_MISC.getKey());

		builder.appendI15d(JsamsI18nResource.LABEL_LEGAL_FORM.getKey(),
				comboBoxLegalForm, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_CAPITAL.getKey(),
				textFieldCapital, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_ACTIVITY.getKey(),
				textFieldActivity, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_RESPONSIBLE.getKey(),
				textFieldResponsible, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_VAT_NUMBER.getKey(),
				textFieldVatNumber, DEFAULT_COLUMN_SPAN);
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

	public void performCancel() {
		this.dispose();
	}

	public void performOk() {
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
			Society updatedSociety = new Society();
			updatedSociety.setActivity(textFieldActivity.getText());
			Address updatedAddress = new Address();
			updatedAddress.setBox(textFieldBox.getText());
			updatedAddress.setCity(textFieldCity.getText());
			updatedAddress.setCountry(textFieldCountry.getText());
			updatedAddress.setNumber(textFieldNumber.getText());
			updatedAddress.setStreet(textFieldStreet.getText());
			updatedAddress.setZipCode(Integer.parseInt(textFieldZipCode
					.getText()));
			updatedSociety.setAddress(updatedAddress);
			updatedSociety
					.setCapital(new BigDecimal(textFieldCapital.getText()));
			ContactInformation updatedContactInformation = new ContactInformation();
			updatedContactInformation.setEmail(textFieldEmail.getText());
			updatedContactInformation.setFax(textFieldFax.getText());
			updatedContactInformation.setMobile(textFieldMobile.getText());
			updatedContactInformation.setPhone(textFieldPhone.getText());
			updatedContactInformation.setWebsite(textFieldWebsite.getText());
			updatedSociety.setContactInformation(updatedContactInformation);
			updatedSociety.setLegalForm((LegalForm) comboBoxLegalForm
					.getSelectedItem());
			updatedSociety.setName(textFieldName.getText());
			updatedSociety.setResponsible(textFieldResponsible.getText());
			updatedSociety.setVatNumber(textFieldVatNumber.getText());

			if (!getModel().equals(updatedSociety)) {
				JsamsApplicationContext.getSocietyService().update(
						updatedSociety);
				JsamsDesktop.getInstance().setCurrentSociety(updatedSociety);
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
				.getLegalFormDao().findAll();
		comboBoxLegalForm = new JComboBox(allLegalForms.toArray());
		if (getModel() != null) {
			Society society = JsamsApplicationContext.getSocietyService()
					.findById(getModel().getId());
			fillAddress(society);
			fillContactInformation(society);
			comboBoxLegalForm.setSelectedItem(society.getLegalForm());
			textFieldActivity.setText(society.getActivity());
			textFieldCapital.setText(society.getCapital().toPlainString());
			textFieldName.setText(society.getName());
			textFieldResponsible.setText(society.getResponsible());
			textFieldVatNumber.setText(society.getVatNumber());
		}
		comboBoxLegalForm.setRenderer(new TranslatableComboBoxRenderer());
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
