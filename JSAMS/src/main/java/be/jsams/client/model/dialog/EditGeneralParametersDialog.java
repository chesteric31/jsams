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
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;

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
     * properties path name
     */
    private static final String BE_JSAMS = "be.jsams";

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

    /**
     * constant for proxy to set
     */
    private static final String PROXY_TO_SET = "proxyToSet";

    /**
     * constant for proxy host
     */
    private static final String PROXY_HOST = "proxyHost";

    /**
     * constant for proxy port
     */
    private static final String PROXY_PORT = "proxyPort";

    /**
     * constant for proxy to authenticate
     */
    private static final String PROXY_TO_AUTHENTICATE = "proxyToAuthenticate";

    /**
     * constant for proxy user
     */
    private static final String PROXY_USER = "proxyUser";

    /**
     * constant for proxy pass
     */
    private static final String PROXY_PASS = "proxyPass";

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

    /**
     * Constructor
     */
    public EditGeneralParametersDialog() {
        super(null, JsamsI18nResource.TITLE_EDIT_GENERAL_PARAMETERS, IconUtil.TITLE_ICON_PREFIX
                + "actions/system-shutdown.png");
        buttonsPanel = new JsamsButtonsPanel(this, true, false, true);
        setDefaultKeyActions();
        add(buildSouthPanel(), BorderLayout.SOUTH);
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        FormLayout layout = new FormLayout("right:pref, 3dlu, 60dlu:grow", "pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node(BE_JSAMS);

        Boolean proxyToSetProp = Boolean.valueOf(jsamsPrefs.get(PROXY_TO_SET, "false"));
        String proxyHostProp = String.valueOf(jsamsPrefs.get(PROXY_HOST, ""));
        String proxyPortProp = String.valueOf(jsamsPrefs.get(PROXY_PORT, ""));

        Boolean proxyToAuthenticateProp = Boolean.valueOf(jsamsPrefs.get(PROXY_TO_AUTHENTICATE, "false"));
        String proxyUserProp = String.valueOf(jsamsPrefs.get(PROXY_USER, ""));
        String proxyPassProp = String.valueOf(jsamsPrefs.get(PROXY_PASS, ""));
        
        int firstRememberDays = Integer.valueOf(jsamsPrefs.get(FIRST_REMEMBER_DAYS, "0"));
        int secondRememberDays = Integer.valueOf(jsamsPrefs.get(SECOND_REMEMBER_DAYS, "0"));
        int formalNoticeDays = Integer.valueOf(jsamsPrefs.get(FORMAL_NOTICE_DAYS, "0"));

        spinnerModelFirst = new SpinnerNumberModel(firstRememberDays, 0, null, 1);
        spinnerModelSecond = new SpinnerNumberModel(secondRememberDays, 0, null, 1);
        spinnerModelNotice = new SpinnerNumberModel(formalNoticeDays, 0, null, 1);
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY.getTranslation());
        proxyToSet.setSelected(proxyToSetProp);
        proxyToSet.addItemListener(this);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY_TO_SET.getKey(), proxyToSet);
        builder.nextLine();
        proxyHost.setEnabled(proxyToSetProp);
        proxyHost.setText(proxyHostProp);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY_HOST.getKey(), proxyHost);
        proxyPort.setEnabled(proxyToSetProp);
        proxyPort.setText(proxyPortProp);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY_PORT.getKey(), proxyPort);
        proxyToAuthenticate.setSelected(proxyToAuthenticateProp);
        proxyToAuthenticate.addItemListener(this);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY_TO_AUTHENTICATE.getKey(), proxyToAuthenticate);
        builder.nextLine();
        proxyUser.setEnabled(proxyToAuthenticateProp);
        proxyUser.setText(proxyUserProp);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY_USER.getKey(), proxyUser);
        proxyPass.setEnabled(proxyToAuthenticateProp);
        proxyPass.setText(proxyPassProp);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SETTINGS_PROXY_PASS.getKey(), proxyPass);
        builder.appendSeparator(JsamsI18nLabelResource.LABEL_SETTINGS_BILL.getTranslation());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FIRST_REMEMBER_DAYS.getKey(), new JSpinner(spinnerModelFirst));
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FIRST_REMEMBER_DAYS.getKey(), new JSpinner(spinnerModelFirst));
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_SECOND_REMEMBER_DAYS.getKey(),
                new JSpinner(spinnerModelSecond));
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FORMAL_NOTICE_DAYS.getKey(), new JSpinner(spinnerModelNotice));
        builder.nextLine();
        JPanel panel = builder.getPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        DialogUtil.centerComponentOnScreen(this);
        setVisible(true);
        setResizable(false);
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
        Preferences jsamsPrefs = prefsRoot.node(BE_JSAMS);
        jsamsPrefs.putBoolean(PROXY_TO_SET, proxyToSet.isSelected());
        jsamsPrefs.put(PROXY_HOST, proxyHost.getText());
        jsamsPrefs.put(PROXY_PORT, proxyPort.getText());
        jsamsPrefs.putBoolean(PROXY_TO_AUTHENTICATE, proxyToAuthenticate.isSelected());
        jsamsPrefs.put(PROXY_USER, proxyUser.getText());
        jsamsPrefs.put(PROXY_PASS, proxyPass.getPassword().toString());
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
