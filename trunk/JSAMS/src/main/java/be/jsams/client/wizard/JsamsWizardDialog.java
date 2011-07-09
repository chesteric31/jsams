package be.jsams.client.wizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.client.wizard.action.CancelAction;
import be.jsams.client.wizard.action.FinishAction;

/**
 * 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsWizardDialog extends JsamsDialog {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2855036709879288192L;

    private DefaultJsamsWizardComponent component;
    private JPanel buttonPanel;
    private String logoFileName;
    
    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} translatable String
     * @param iconFileName the icon path name of the dialog
     * @param logoFileName the file name to the logo to display
     */
    public JsamsWizardDialog(JsamsMainFrame parent, I18nString title, String iconFileName, String logoFileName) {
        super(parent, title, IconUtil.TITLE_ICON_PREFIX + iconFileName);
        component = new DefaultJsamsWizardComponent();
        this.logoFileName = logoFileName;
        initComponents();
    }

    /**
     * Initialize all the components.
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        JPanel centerPanel = new JPanel();
        if (logoFileName != null) {
            Image defaultlogo = IconUtil.buildIcon(logoFileName);
            JPanel logoPanel = new JPanel();
            logoPanel.add(new JsamsLabel(new ImageIcon(defaultlogo)));
            logoPanel.setBackground(Color.WHITE);
            centerPanel.add(logoPanel);
        }
        centerPanel.add(component.getPanelsContainer());
        add(centerPanel, BorderLayout.CENTER);
        
        buttonPanel = new JsamsWizardButtonPanel(component);
        component.setFinishAction(new FinishAction(component) {
            public void performAction() {
                dispose();
            }
        });
        component.setCancelAction(new CancelAction(component) {
            public void performAction() {
                dispose();
            }
        });
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Display the dialog.
     */
    public void display() {
        component.updateComponents();
        super.setVisible(true);
    }

    /**
     * @return the component
     */
    public DefaultJsamsWizardComponent getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(DefaultJsamsWizardComponent component) {
        this.component = component;
    }
    
}
