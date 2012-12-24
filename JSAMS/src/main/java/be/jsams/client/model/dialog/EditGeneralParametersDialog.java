package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconResource;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.util.JsamsConstants;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * General parameters edition dialog.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditGeneralParametersDialog extends JsamsDialog implements JsamsButtonsInterface, ItemListener {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2631309171029588421L;

    /**
     * constant for first remember days
     */
    private static final String FIRST_REMEMBER_DAYS = "firstRememberDays";

    /**
     * constant for second remember days
     */
    private static final String SECOND_REMEMBER_DAYS = "secondRememberDays";

    /**
     * constant for formal notice days
     */
    private static final String FORMAL_NOTICE_DAYS = "formalNoticeDays";

    private JsamsButtonsPanel buttonsPanel;
    private JsamsStatusBar statusBar;
    private JPanel southPanel;
    private SpinnerNumberModel spinnerModelFirst;
    private SpinnerNumberModel spinnerModelSecond;
    private SpinnerNumberModel spinnerModelNotice;
    private JCheckBox proxyToSet = new JCheckBox();
    private JsamsTextField proxyHost = new JsamsTextField();
    private JsamsTextField proxyPort = new JsamsTextField();
    private JCheckBox proxyToAuthenticate = new JCheckBox();
    private JsamsTextField proxyUser = new JsamsTextField();
    private JPasswordField proxyPass = new JPasswordField();
    private JsamsTextField emailSmtp = new JsamsTextField();
    private JsamsTextField emailPort = new JsamsTextField();
    private JsamsTextField emailUser = new JsamsTextField();
    private JPasswordField emailPass = new JPasswordField();
    private JsamsTextField emailDefaultSubject = new JsamsTextField();
    
    /**
     * Constructor.
     */
    public EditGeneralParametersDialog() {
        super(null, I18nResource.TITLE_EDIT_GENERAL_PARAMETERS, IconUtil.TITLE_ICON_PREFIX
                + IconResource.GENERAL_PARAMETERS);
        buttonsPanel = new JsamsButtonsPanel(this, true, false, true);
        setDefaultKeyActions();
        add(buildSouthPanel(), BorderLayout.SOUTH);
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        Preferences rootPreferences = Preferences.userRoot();
        Preferences preferences = rootPreferences.node(JsamsConstants.PACKAGE_ROOT_NAME);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
        JPanel networkPanel = buildNetworkTab(preferences);
        tabbedPane.add(I18nResource.PANEL_NETWORK.getTranslation(), networkPanel);
        JPanel billPanel = buildBillTab(preferences);
        tabbedPane.add(I18nResource.PANEL_BILL.getTranslation(), billPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        DialogUtil.centerComponentOnScreen(this);
        setVisible(true);
        setResizable(false);
    }

    /**
     * Builds the network panel with proxy, email settings.
     * 
     * @param preferences the {@link Preferences} to use
     * @return the built panel
     */
    private JPanel buildNetworkTab(Preferences preferences) {
        FormLayout layout = new FormLayout("right:pref, 3dlu, 60dlu:grow", "pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();

        String emailSmtpProp = String.valueOf(preferences.get(JsamsConstants.EMAIL_SMTP, ""));
        String emailPortProp = String.valueOf(preferences.get(JsamsConstants.EMAIL_PORT, ""));
        String emailUserProp = String.valueOf(preferences.get(JsamsConstants.EMAIL_USER, ""));
        String emailPassProp = String.valueOf(preferences.get(JsamsConstants.EMAIL_PASS, ""));
        String emailDefaultSubjectProp = String.valueOf(preferences.get(JsamsConstants.EMAIL_DEFAULT_SUBJECT, ""));

        builder.appendSeparator(I18nLabelResource.LABEL_SETTINGS_EMAIL.getTranslation());
        emailSmtp.setText(emailSmtpProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_EMAIL_SMTP.getKey(), emailSmtp);
        emailPort.setText(emailPortProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_EMAIL_PORT.getKey(), emailPort);
        emailUser.setText(emailUserProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_EMAIL_USER.getKey(), emailUser);
        emailPass.setText(emailPassProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_EMAIL_PASS.getKey(), emailPass);
        emailDefaultSubject.setText(emailDefaultSubjectProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_EMAIL_DEFAULT_SUBJECT.getKey(), emailDefaultSubject);
        
        Boolean proxyToSetProp = Boolean.valueOf(preferences.get(JsamsConstants.PROXY_TO_SET, "false"));
        String proxyHostProp = String.valueOf(preferences.get(JsamsConstants.PROXY_HOST, ""));
        String proxyPortProp = String.valueOf(preferences.get(JsamsConstants.PROXY_PORT, ""));

        Boolean proxyToAuthenticateProp = Boolean.valueOf(preferences
                .get(JsamsConstants.PROXY_TO_AUTHENTICATE, "false"));
        String proxyUserProp = String.valueOf(preferences.get(JsamsConstants.PROXY_USER, ""));
        String proxyPassProp = String.valueOf(preferences.get(JsamsConstants.PROXY_PASS, ""));
        builder.appendSeparator(I18nLabelResource.LABEL_SETTINGS_PROXY.getTranslation());
        proxyToSet.setSelected(proxyToSetProp);
        proxyToSet.addItemListener(this);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_PROXY_TO_SET.getKey(), proxyToSet);
        builder.nextLine();
        proxyHost.setEnabled(proxyToSetProp);
        proxyHost.setText(proxyHostProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_PROXY_HOST.getKey(), proxyHost);
        proxyPort.setEnabled(proxyToSetProp);
        proxyPort.setText(proxyPortProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_PROXY_PORT.getKey(), proxyPort);
        proxyToAuthenticate.setSelected(proxyToAuthenticateProp);
        proxyToAuthenticate.addItemListener(this);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_PROXY_TO_AUTHENTICATE.getKey(), proxyToAuthenticate);
        builder.nextLine();
        proxyUser.setEnabled(proxyToAuthenticateProp);
        proxyUser.setText(proxyUserProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_PROXY_USER.getKey(), proxyUser);
        proxyPass.setEnabled(proxyToAuthenticateProp);
        proxyPass.setText(proxyPassProp);
        builder.appendI15d(I18nLabelResource.LABEL_SETTINGS_PROXY_PASS.getKey(), proxyPass);

        return builder.getPanel();
    }
    
    /**
     * Builds the bill settings panel.
     * 
     * @param preferences the {@link Preferences} to use
     * @return the built panel
     */
    private JPanel buildBillTab(Preferences preferences) {
        FormLayout layout = new FormLayout("right:pref, 3dlu, 60dlu:grow", "pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();

        int firstRememberDays = Integer.valueOf(preferences.get(FIRST_REMEMBER_DAYS, "0"));
        int secondRememberDays = Integer.valueOf(preferences.get(SECOND_REMEMBER_DAYS, "0"));
        int formalNoticeDays = Integer.valueOf(preferences.get(FORMAL_NOTICE_DAYS, "0"));

        spinnerModelFirst = new SpinnerNumberModel(firstRememberDays, 0, null, 1);
        spinnerModelSecond = new SpinnerNumberModel(secondRememberDays, 0, null, 1);
        spinnerModelNotice = new SpinnerNumberModel(formalNoticeDays, 0, null, 1);

        builder.appendSeparator(I18nLabelResource.LABEL_SETTINGS_BILL.getTranslation());
        builder.appendI15d(I18nLabelResource.LABEL_FIRST_REMEMBER_DAYS.getKey(), new JSpinner(spinnerModelFirst));
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_SECOND_REMEMBER_DAYS.getKey(), new JSpinner(spinnerModelSecond));
        builder.nextLine();
        builder.appendI15d(I18nLabelResource.LABEL_FORMAL_NOTICE_DAYS.getKey(), new JSpinner(spinnerModelNotice));
        return builder.getPanel();
    }

    /**
     * Builds the 'south panel' composed by a {@link JsamsButtonsPanel}
     * {@link JsamsStatusBar}.
     * 
     * @return the 'south panel'
     */
    private JPanel buildSouthPanel() {
        statusBar = new JsamsStatusBar();
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.add(buttonsPanel);
        southPanel.add(statusBar);
        return southPanel;
    }

    /**
     * Sets the default keys actions.
     */
    private void setDefaultKeyActions() {
        // Automatically choose OK when Enter Key is pressed
        int noModifiers = 0;
        KeyStroke okKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, noModifiers, false);
        InputMap inputMap = rootPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(okKey, JsamsButtonsPanel.OK_ACTION_KEY);
        rootPane.getActionMap().put(JsamsButtonsPanel.OK_ACTION_KEY, buttonsPanel.getButtonOk().getAction());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node(JsamsConstants.PACKAGE_ROOT_NAME);
        jsamsPrefs.put(JsamsConstants.EMAIL_SMTP, emailSmtp.getText());
        jsamsPrefs.put(JsamsConstants.EMAIL_PORT, emailPort.getText());
        jsamsPrefs.put(JsamsConstants.EMAIL_USER, emailUser.getText());
        jsamsPrefs.put(JsamsConstants.EMAIL_PASS, emailPass.getPassword().toString());
        jsamsPrefs.put(JsamsConstants.EMAIL_DEFAULT_SUBJECT, emailDefaultSubject.getText());
        jsamsPrefs.putBoolean(JsamsConstants.PROXY_TO_SET, proxyToSet.isSelected());
        jsamsPrefs.put(JsamsConstants.PROXY_HOST, proxyHost.getText());
        jsamsPrefs.put(JsamsConstants.PROXY_PORT, proxyPort.getText());
        jsamsPrefs.putBoolean(JsamsConstants.PROXY_TO_AUTHENTICATE, proxyToAuthenticate.isSelected());
        jsamsPrefs.put(JsamsConstants.PROXY_USER, proxyUser.getText());
        jsamsPrefs.put(JsamsConstants.PROXY_PASS, proxyPass.getPassword().toString());
        jsamsPrefs.put(FIRST_REMEMBER_DAYS, spinnerModelFirst.getValue().toString());
        jsamsPrefs.put(SECOND_REMEMBER_DAYS, spinnerModelSecond.getValue().toString());
        jsamsPrefs.put(FORMAL_NOTICE_DAYS, spinnerModelNotice.getValue().toString());
        this.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
        this.dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performReset() {
        spinnerModelFirst.setValue(0);
        spinnerModelSecond.setValue(0);
        spinnerModelNotice.setValue(0);
        emailSmtp.setText("");
        emailPort.setText("");
        emailUser.setText("");
        emailPass.setText("");
        emailDefaultSubject.setText("");
        proxyToSet.setSelected(false);
        proxyHost.setText("");
        proxyPort.setText("");
        proxyToAuthenticate.setSelected(false);
        proxyUser.setText("");
        proxyPass.setText("");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getItemSelectable();
        if (source == proxyToSet) {
            proxyHost.setEnabled(proxyToSet.isSelected());
            proxyPort.setEnabled(proxyToSet.isSelected());
        } else if (source == proxyToAuthenticate) {
            proxyUser.setEnabled(proxyToAuthenticate.isSelected());
            proxyPass.setEnabled(proxyToAuthenticate.isSelected());
        }
    }

}
