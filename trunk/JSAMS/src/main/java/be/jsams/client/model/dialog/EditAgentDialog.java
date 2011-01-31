package be.jsams.client.model.dialog;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.validator.AgentValidator;
import be.jsams.server.model.Address;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.service.AgentService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Agent {@link EditDialog}, to create or update a {@link Agent} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditAgentDialog extends EditDialog<Agent, AgentValidator, AgentService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2514472162732492120L;

    private static final int MAX_CHARACTERS = 50;

    private static final int MAX_NUMBERS = 10;

    private JTabbedPane tabbedPane;

    private JPanel generalPanel;

    private JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldFunction = new JsamsTextField(MAX_CHARACTERS);

    private JComboBox comboBoxCivility;

    private JPanel addressPanel;

    private JsamsTextField textFieldStreet = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldNumber = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldBox = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldZipCode = new JsamsTextField(MAX_NUMBERS);

    private JsamsTextField textFieldCity = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldCountry = new JsamsTextField(MAX_CHARACTERS);

    private JPanel contactInformationsPanel;

    private JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldFax = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldMobile = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldEmail = new JsamsTextField(MAX_CHARACTERS);

    private JsamsTextField textFieldWebsite = new JsamsTextField(MAX_CHARACTERS);

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link Agent} model
     */
    public EditAgentDialog(final I18nString title, Agent model) {
        super(null, title, "categories/applications-development.png");
        super.setModel(model);
        super.setValidator(new AgentValidator());
        super.setService(JsamsApplicationContext.getAgentService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    @Override
    protected void initComponents() {
        fillData();
        tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        generalPanel = buildGeneralTab();
        tabbedPane.add(JsamsI18nResource.PANEL_GENERAL.getTranslation(), generalPanel);
        addressPanel = buildAddressTab();
        tabbedPane.add(JsamsI18nResource.PANEL_ADDRESS.getTranslation(), addressPanel);
        contactInformationsPanel = buildContactInformationsTab();
        tabbedPane.add(JsamsI18nResource.PANEL_CONTACT_INFORMATIONS.getTranslation(), contactInformationsPanel);

        setMandatoryFields();
        add(tabbedPane);

        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);

        pack();
    }

    /**
     * Builds the general tab panel.
     * 
     * @return the general tab panel
     */
    private JPanel buildGeneralTab() {
        FormLayout layout = new FormLayout("right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(), comboBoxCivility);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FUNCTION.getKey(), textFieldFunction, maxColumnSpan);
        return builder.getPanel();
    }

    /**
     * Builds the address tab panel.
     * 
     * @return the address tab panel
     */
    private JPanel buildAddressTab() {
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu, 3dlu, right:p, 3dlu, 75dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 9;
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_STREET.getKey(), textFieldStreet, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NUMBER.getKey(), textFieldNumber);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BOX.getKey(), textFieldBox);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity, maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COUNTRY.getKey(), textFieldCountry, maxColumnSpan);
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
     * Fills all the data in case of update.
     */
    private void fillData() {
        List<Civility> allCivilities = JsamsApplicationContext.getCivilityDao().findAll();
        ArrayList<Civility> allWithNullCivilities = new ArrayList<Civility>();
        allWithNullCivilities.add(null);
        allWithNullCivilities.addAll(allCivilities);
        comboBoxCivility = new JComboBox(allWithNullCivilities.toArray());
        if (getModel() != null) {
            fillAddress();
            fillContactInformations();
            comboBoxCivility.setSelectedItem(getModel().getCivility());
            textFieldName.setText(getModel().getName());
            textFieldFunction.setText(getModel().getFunction());
        }
        comboBoxCivility.setRenderer(new TranslatableComboBoxRenderer());
    }

    @Override
    public void performOk() {
        // TODO Auto-generated method stub

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
     * Sets all mandatory fields.
     */
    private void setMandatoryFields() {
        ValidationComponentUtils.setMandatory(textFieldCity, true);
        ValidationComponentUtils.setMandatory(textFieldCountry, true);
        ValidationComponentUtils.setMandatory(textFieldNumber, true);
        ValidationComponentUtils.setMandatory(textFieldStreet, true);
        ValidationComponentUtils.setMandatory(textFieldZipCode, true);

        ValidationComponentUtils.setMandatory(textFieldName, true);
        ValidationComponentUtils.setMandatory(textFieldFunction, true);
        ValidationComponentUtils.setMandatory(textFieldPhone, true);
    }

}
