package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.util.prefs.Preferences;

import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
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
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditGeneralParametersDialog extends JsamsDialog implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2631309171029588421L;
    
    private JsamsButtonsPanel buttonsPanel;
    private JsamsStatusBar statusBar;
    private JPanel southPanel;
    private SpinnerNumberModel spinnerModelFirst;
    private SpinnerNumberModel spinnerModelSecond;
    private SpinnerNumberModel spinnerModelNotice;
    
    /**
     * Constructor
     */
    public EditGeneralParametersDialog() {
        super(null, JsamsI18nResource.TITLE_EDIT_GENERAL_PARAMETERS, IconUtil.TITLE_ICON_PREFIX
                + "actions/system-shutdown.png");
        buttonsPanel = new JsamsButtonsPanel(this, true, false, false);
        setDefaultKeyActions();
        add(buildSouthPanel(), BorderLayout.SOUTH);
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        FormLayout layout = new FormLayout("right:pref, 3dlu, pref", "pref");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        Preferences prefsRoot = Preferences.userRoot();
        Preferences jsamsPrefs = prefsRoot.node("be.jsams");
        int firstRememberDays = Integer.valueOf(jsamsPrefs.get("firstRememberDays", "0"));
        int secondRememberDays = Integer.valueOf(jsamsPrefs.get("secondRememberDays", "0"));
        int formalNoticeDays = Integer.valueOf(jsamsPrefs.get("formalNoticeDays", "0"));
        spinnerModelFirst = new SpinnerNumberModel(firstRememberDays, 0, null, 1);
        spinnerModelSecond = new SpinnerNumberModel(secondRememberDays, 0, null, 1);
        spinnerModelNotice = new SpinnerNumberModel(formalNoticeDays, 0, null, 1);
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
     * Build the 'south panel' composed by a {@link JsamsButtonsPanel} {@link JsamsStatusBar}
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
     * Set the default keys actions.
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
        Preferences jsamsPrefs = prefsRoot.node("be.jsams");
        jsamsPrefs.put("firstRememberDays", spinnerModelFirst.getValue().toString());
        jsamsPrefs.put("secondRememberDays", spinnerModelSecond.getValue().toString());
        jsamsPrefs.put("formalNoticeDays", spinnerModelNotice.getValue().toString());
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
    }
    
}
