package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.plaf.FontUIResource;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.utils.DialogUtil;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.builder.SocietyBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Open society {@link JsamsDialog}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class OpenSocietyDialog extends JsamsDialog implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 237617341189579756L;

    private JsamsComboBox comboBox = null;
    private JsamsButtonsPanel buttonsPanel;
    private SocietyBean bean;
    private JsamsStatusBar statusBar;
    private JPanel southPanel;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     */
    public OpenSocietyDialog(final I18nString title) {
        super(null, title, IconUtil.TITLE_ICON_PREFIX + "categories/applications-office.png");
        buttonsPanel = new JsamsButtonsPanel(this, true, false, false);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setDefaultKeyActions();
        add(buildSouthPanel(), BorderLayout.SOUTH);
        initComponents();
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        FormLayout layout = new FormLayout("right:pref, 3dlu, pref, 3dlu, pref", "pref, 5dlu");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        JsamsLabel label = new JsamsLabel(JsamsI18nLabelResource.LABEL_OPEN_SOCIETY);
        label.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        builder.append(label);
        builder.nextLine();
        builder.appendSeparator();
        ViewFactory<SocietyBean> helper = new ViewFactory<SocietyBean>();
        SocietyBeanBuilder societyBuilder = getSocietyBeanBuilder();
        bean = societyBuilder.build(false);
        comboBox = helper.createBindingComboComponent(bean, true, false, new NamedComboBoxRenderer());
        builder.append(JsamsI18nLabelResource.LABEL_AVAILABLES_SOCIETIES.getTranslation(), comboBox);
        JsamsButton buttonNewSociety = buildButtonNewSociety();
        builder.append(buttonNewSociety);
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
     * {@inheritDoc}
     */
    public void performOk() {
        SocietyBean selectedSociety = (SocietyBean) bean.getSelection();
        if (selectedSociety != null) {
            JsamsDesktop jsamsDesktop = JsamsDesktop.getInstance();
            jsamsDesktop.setCurrentSociety(selectedSociety);
            Object[] parameters = new Object[1];
            parameters[0] = selectedSociety.getName();
            I18nString newTitle = new I18nString("title.application", parameters);
            JsamsMainFrame mainWindow = jsamsDesktop.getMainWindow();
            mainWindow.setTitle(newTitle);
            mainWindow.enableAllMenuItems(true);
            mainWindow.enableTabbedPane(true);
            mainWindow.getShortcutToolBar().enableButtons(true);
            dispose();
        } else {
            statusBar.removeAll();
            statusBar.repaint();
            JsamsLabel label = new JsamsLabel(JsamsI18nLabelResource.LABEL_ERROR_MANDATORY_SOCIETY,
                    ValidationResultViewFactory.getErrorIcon());
            statusBar.addComponent(label);
            statusBar.validate();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void performCancel() {
        dispose();
    }

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        comboBox.setSelectedIndex(0);
    }

    /**
     * Builds the new society button.
     * 
     * @return the new society button
     */
    private JsamsButton buildButtonNewSociety() {
        JsamsButton buttonNewSociety = new JsamsButton(JsamsI18nResource.BUTTON_OPEN_SOCIETIES_NEW,
                IconUtil.MENU_ICON_PREFIX + "actions/folder-new.png");
        buttonNewSociety.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                SocietyBeanBuilder builder = getSocietyBeanBuilder();
                SocietyBean societyBean = builder.build(true);
                EditSocietyDialog dialog = new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY, societyBean);
                if (dialog.isSuccess()) {
                    dispose();
                }
            }
        });
        return buttonNewSociety;
    }
    
    /**
     * 
     * @return the {@link SocietyBeanBuilder}
     */
    public SocietyBeanBuilder getSocietyBeanBuilder() {
        return JsamsApplicationContext.getSocietyBeanBuilder();
    }

    /**
     * Builds the 'south panel' composed by a {@link JsamsButtonsPanel} {@link JsamsStatusBar}.
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
    
}
