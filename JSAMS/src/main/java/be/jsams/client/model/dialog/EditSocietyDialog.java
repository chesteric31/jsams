package be.jsams.client.model.dialog;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.validator.SocietyValidator;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.service.SocietyService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.mysql.jdbc.StringUtils;

/**
 * Edit society {@link EditDialog}, to create or update a {@link Society} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditSocietyDialog extends EditDialog<Society, SocietyValidator, SocietyService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4225744372592399187L;

    protected static final Log LOGGER = LogFactory.getLog(EditSocietyDialog.class);

    private static final int DEFAULT_COLUMN_SPAN = 9;

    private static final int MAX_CHARACTERS = 50;

    private static final int MAX_NUMBERS = 10;

    private JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldStreet = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldNumber = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldBox = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldZipCode = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldCity = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldCountry = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldFax = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldMobile = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldEmail = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldWebsite = new JsamsTextField(MAX_CHARACTERS);

    private JComboBox comboBoxLegalForm;

    private JsamsFormattedTextField textFieldCapital = new JsamsFormattedTextField();

    private JsamsTextField textFieldActivity = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldResponsible = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldVatNumber = new JsamsTextField(MAX_CHARACTERS);

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
        super.setModel(model);
        super.setValidator(new SocietyValidator());
        super.setService(JsamsApplicationContext.getSocietyService());
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
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu, 3dlu, right:p, 3dlu, 50dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_ADDRESS.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(), textFieldStreet, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(), textFieldNumber, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(), textFieldBox, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity, DEFAULT_COLUMN_SPAN);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(), textFieldCountry, DEFAULT_COLUMN_SPAN);
        builder.nextLine();

        builder.appendSeparator(JsamsI18nLabelResource.LABEL_CONTACT_INFORMATIONS.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FAX.getKey(), textFieldFax, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_MOBILE.getKey(), textFieldMobile, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_EMAIL.getKey(), textFieldEmail, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_WEBSITE.getKey(), textFieldWebsite, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_MISC.getTranslation());

        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), comboBoxLegalForm, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CAPITAL.getKey(), textFieldCapital, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ACTIVITY.getKey(), textFieldActivity, DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder
                .appendI15d(JsamsI18nLabelResource.LABEL_RESPONSIBLE.getKey(), textFieldResponsible,
                        DEFAULT_COLUMN_SPAN);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_VAT_NUMBER.getKey(), textFieldVatNumber, DEFAULT_COLUMN_SPAN);
        builder.nextLine();

        getContentPane().add(builder.getPanel());

        setMandatoryFields();

        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);

        pack();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        Society society = new Society();

        society.setActivity(textFieldActivity.getText());
        Address address = new Address();
        if (getModel() != null && getModel().getAddress() != null && getModel().getAddress().getId() != null) {
            address.setId(getModel().getAddress().getId());
        }
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
        BigDecimal capital = null;
        Object value = textFieldCapital.getValue();
        if (value instanceof Long) {
            capital = BigDecimal.valueOf((Long) value);
        } else if (value instanceof Double) {
            capital = BigDecimal.valueOf((Double) value);
        } else {
            capital = (BigDecimal) value;
        }
        if (capital != null) {
            capital = capital.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            society.setCapital(capital);
        }
        ContactInformation contactInformation = new ContactInformation();
        if (getModel() != null && getModel().getContactInformation() != null
                && getModel().getContactInformation().getId() != null) {
            contactInformation.setId(getModel().getContactInformation().getId());
        }
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
            society.setLegalForm((LegalForm) comboBoxLegalForm.getSelectedItem());
        }
        society.setName(textFieldName.getText());
        if (!StringUtils.isNullOrEmpty(textFieldResponsible.getText())) {
            society.setResponsible(textFieldResponsible.getText());
        }
        if (!StringUtils.isNullOrEmpty(textFieldVatNumber.getText())) {
            society.setVatNumber(textFieldVatNumber.getText());
        }
        if (super.postPerformOk(society)) {
            JsamsDesktop.getInstance().setCurrentSociety(society);
        }
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
        if (getModel() != null) {
            fillAddress();
            fillContactInformation();
            comboBoxLegalForm.setSelectedItem(getModel().getLegalForm());
            textFieldActivity.setText(getModel().getActivity());
            textFieldCapital.setValue(getModel().getCapital());
            textFieldName.setText(getModel().getName());
            textFieldResponsible.setText(getModel().getResponsible());
            textFieldVatNumber.setText(getModel().getVatNumber());
        }
        comboBoxLegalForm.setRenderer(new TranslatableComboBoxRenderer());
    }

    /**
     * Fills the address data.
     */
    private void fillAddress() {
        if (getModel() != null) {
            Address address = getModel().getAddress();
            textFieldBox.setText(address.getBox());
            textFieldCity.setText(address.getCity());
            textFieldCountry.setText(address.getCountry());
            textFieldNumber.setText(address.getNumber());
            textFieldStreet.setText(address.getStreet());
            textFieldZipCode.setText(Integer.toString(address.getZipCode()));
        }
    }

    /**
     * Fills the contact informations data.
     */
    private void fillContactInformation() {
        if (getModel() != null) {
            ContactInformation information = getModel().getContactInformation();
            textFieldEmail.setText(information.getEmail());
            textFieldFax.setText(information.getFax());
            textFieldMobile.setText(information.getMobile());
            textFieldPhone.setText(information.getPhone());
            textFieldWebsite.setText(information.getWebsite());
        }
    }

    /**
     * Sets all mandatory fields.
     */
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
