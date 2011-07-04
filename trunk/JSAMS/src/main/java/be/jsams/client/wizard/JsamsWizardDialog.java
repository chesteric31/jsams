package be.jsams.client.wizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.JsamsDialog;
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
     * @param iconFileName the icon path name
     * @param logoFileName the file name to the logo to display
     */
    public JsamsWizardDialog(JsamsMainFrame parent, I18nString title, String iconFileName, String logoFileName) {
        super(parent, title, iconFileName);
        component = new DefaultJsamsWizardComponent();
        this.logoFileName = logoFileName;
        initComponents();
    }

    /**
     * Initialize all the components.
     */
    private void initComponents() {
        this.getContentPane().setLayout(new GridBagLayout());
        if (logoFileName != null) {
            JPanel logoPanel = new JPanel();
            logoPanel.add(new JLabel(logoFileName), BorderLayout.CENTER);
            logoPanel.setBackground(Color.WHITE);
            this.getContentPane().add(
                    logoPanel,
                    new GridBagConstraints(0, 0, 1, 1, 0.3, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));
            this.getContentPane().add(
                    component.getPanelsContainer(),
                    new GridBagConstraints(1, 0, 1, 1, 0.7, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));

            this.getContentPane().add(
                    new JSeparator(),
                    new GridBagConstraints(0, 1, 2, 0, 1.0, .01, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                            new Insets(1, 1, 1, 1), 0, 0));
        } else {
            this.getContentPane().add(
                    component.getPanelsContainer(),
                    new GridBagConstraints(0, 0, 1, 1, 1.0, 0.9, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                            new Insets(0, 0, 0, 0), 0, 0));

            this.getContentPane().add(
                    new JSeparator(),
                    new GridBagConstraints(0, 1, 1, 0, 1.0, 0.01, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                            new Insets(1, 1, 1, 1), 0, 0));
        }

        buttonPanel = new JsamsWizardButtonPanel(component);
        this.getContentPane().add(
                buttonPanel,
                new GridBagConstraints(0, 2, 1, 1, 1.0, 0.9, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

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

    }
    
    /**
     * {@inheritDoc}
     */
    public void show() {
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
