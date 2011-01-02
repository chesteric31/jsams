package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.validator.SocietyValidator;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.jgoodies.validation.view.ValidationResultViewFactory;
import com.mysql.jdbc.StringUtils;

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

    protected final Log LOGGER = LogFactory.getLog(this.getClass());

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

	private ValidationResultModel validationResultModel = new DefaultValidationResultModel();

	private JsamsStatusBar statusBar;

	private JPanel southPanel;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            the {@link I18nString} title
	 * @param model
	 *            the {@link Society} model
	 */
	public EditSocietyDialog(final I18nString title, Society model) {
		super(null, title);
		this.model = model;
		initComponents();
	}

	public ValidationResultModel getValidationResultModel() {
		return validationResultModel;
	}

	public void setValidationResultModel(
			ValidationResultModel validationResultModel) {
		this.validationResultModel = validationResultModel;
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
	 * @param model
	 *            the {@link Society} to set
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
		builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(),
				textFieldName, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nLabelResource.LABEL_ADDRESS
				.getTranslation());
		builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(),
				textFieldStreet, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(),
				textFieldNumber, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(),
				textFieldBox, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(),
				textFieldZipCode, 1);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(),
				textFieldCity, DEFAULT_COLUMN_SPAN);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(),
				textFieldCountry, DEFAULT_COLUMN_SPAN);
		builder.nextLine();

		builder
				.appendSeparator(JsamsI18nLabelResource.LABEL_CONTACT_INFORMATIONS
						.getTranslation());
		builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(),
				textFieldPhone, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_FAX.getKey(),
				textFieldFax, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_MOBILE.getKey(),
				textFieldMobile, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_EMAIL.getKey(),
				textFieldEmail, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_WEBSITE.getKey(),
				textFieldWebsite, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendSeparator(JsamsI18nLabelResource.LABEL_MISC
				.getTranslation());

		builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(),
				comboBoxLegalForm, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_CAPITAL.getKey(),
				textFieldCapital, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_ACTIVITY.getKey(),
				textFieldActivity, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_RESPONSIBLE.getKey(),
				textFieldResponsible, DEFAULT_COLUMN_SPAN);
		builder.nextLine();
		builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getKey(),
				textFieldVatNumber, DEFAULT_COLUMN_SPAN);
		builder.nextLine();

		setMandatoryFields();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), BorderLayout.CENTER);
		ValidationComponentUtils
				.updateComponentTreeMandatoryBackground(mainPanel);
		add(mainPanel);

		buildSouthPanel();
		add(southPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}

	private void buildSouthPanel() {
		statusBar = new JsamsStatusBar();
		southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		southPanel.add(buttonsPanel);
		southPanel.add(statusBar);
	}

	public void performCancel() {
		this.dispose();
	}

	public void performOk() {
		Society society = new Society();
		society.setActivity(textFieldActivity.getText());
		Address address = new Address();
		if (!StringUtils.isNullOrEmpty(textFieldBox.getText())) {
			address.setBox(textFieldBox.getText());
		}
		address.setCity(textFieldCity.getText());
		address.setCountry(textFieldCountry.getText());
		address.setNumber(textFieldNumber.getText());
		address.setStreet(textFieldStreet.getText());
		if (!StringUtils.isNullOrEmpty(textFieldZipCode.getText())) {
			address.setZipCode(Integer.parseInt(textFieldZipCode.getText()));
		}
		society.setAddress(address);
		if (!StringUtils.isNullOrEmpty(textFieldCapital.getText())) {
			society.setCapital(new BigDecimal(textFieldCapital.getText()));
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
		society.setContactInformation(contactInformation);
		if (comboBoxLegalForm.getSelectedItem() != null) {
			society.setLegalForm((LegalForm) comboBoxLegalForm
					.getSelectedItem());
		}
		society.setName(textFieldName.getText());
		if (!StringUtils.isNullOrEmpty(textFieldResponsible.getText())) {
			society.setResponsible(textFieldResponsible.getText());
		}
		if (!StringUtils.isNullOrEmpty(textFieldVatNumber.getText())) {
			society.setVatNumber(textFieldVatNumber.getText());
		}
		SocietyValidator validator = new SocietyValidator();
		ValidationResult result = validator.validate(society);
		validationResultModel.setResult(result);
		if (result.hasMessages()) {
			statusBar.removeAll();
			List<ValidationMessage> messages = validationResultModel
					.getResult().getMessages();
			for (ValidationMessage message : messages) {
				JsamsLabel label = new JsamsLabel(message.formattedText()
						.replace(".", ""));
				if (message.severity() == Severity.ERROR) {
					label.setIcon(ValidationResultViewFactory.getErrorIcon());
				} else if (message.severity() == Severity.WARNING) {
					label.setIcon(ValidationResultViewFactory.getWarningIcon());
				}
				statusBar.addJComponent(label);
			}
			statusBar.revalidate();
		} else {
			if (getModel() == null) {
				JsamsApplicationContext.getSocietyService().create(society);
				JsamsDesktop.getInstance().setCurrentSociety(society);
			} else {
				if (!getModel().equals(society)) {
					JsamsApplicationContext.getSocietyService().update(society);
					JsamsDesktop.getInstance().setCurrentSociety(society);
				}
			}
			dispose();
		}
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
				LOGGER.error(e1);
			} catch (IllegalAccessException e1) {
				LOGGER.error(e1);
			}
		}
	}

	private void fillData() {
		List<LegalForm> allLegalForms = JsamsApplicationContext
				.getLegalFormDao().findAll();
		ArrayList<LegalForm> allWithNull = new ArrayList<LegalForm>();
		allWithNull.add(null);
		allWithNull.addAll(allLegalForms);
		comboBoxLegalForm = new JComboBox(allWithNull.toArray());
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

	private void setMandatoryFields() {
		ValidationComponentUtils.setMandatory(textFieldActivity, true);
		ValidationComponentUtils.setMandatory(textFieldCapital, true);
		ValidationComponentUtils.setMandatory(textFieldCity, true);
		ValidationComponentUtils.setMandatory(textFieldCountry, true);
		ValidationComponentUtils.setMandatory(textFieldName, true);
		ValidationComponentUtils.setMandatory(textFieldNumber, true);
		ValidationComponentUtils.setMandatory(textFieldPhone, true);
		ValidationComponentUtils.setMandatory(textFieldStreet, true);
		ValidationComponentUtils.setMandatory(textFieldZipCode, true);
	}

}
