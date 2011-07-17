package be.jsams.client.wizard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.plaf.FontUIResource;

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

    private JsamsWizardComponent component;
    private I18nString title;
    private JPanel buttonPanel;
    private String logoFileName;
    private JsamsLabel label;

    /**
     * Constructor.
     * 
     * @param parent the {@link JsamsMainFrame} parent
     * @param title the {@link I18nString} translatable String
     * @param iconFileName the icon path name of the dialog
     * @param logoFileName the file name to the logo to display
     * @param component the {@link JsamsWizardComponent} to use, if null, we new
     *            one {@link DefaultJsamsWizardComponent} will be created
     */
    public JsamsWizardDialog(JsamsMainFrame parent, I18nString title, String iconFileName, String logoFileName,
            JsamsWizardComponent component) {
        super(parent, title, IconUtil.TITLE_ICON_PREFIX + iconFileName);
        if (component == null) {
            this.component = new DefaultJsamsWizardComponent();
        } else {
            this.component = component;
        }
        this.logoFileName = logoFileName;
        initComponents();
    }

    /**
     * Initialize all the components.
     */
    private void initComponents() {
        this.setLayout(new BorderLayout());
        label = new JsamsLabel();
        label.setFont(new FontUIResource(Font.SANS_SERIF, Font.BOLD, 12));
        JPanel titlePanel = new JPanel(new BorderLayout());
        label.setAlignmentY(CENTER_ALIGNMENT);
        titlePanel.add(label, BorderLayout.CENTER);
        titlePanel.add(new JSeparator(), BorderLayout.SOUTH);
        add(titlePanel, BorderLayout.NORTH);
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

        // set automatically the new title
        component.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                setPanelTitle(((JsamsWizardPanel) event.getNewValue()).getPanelTitle());
            }
        });
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
    public JsamsWizardComponent getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(JsamsWizardComponent component) {
        this.component = component;
    }

    /**
     * @return the title
     */
    public I18nString getPanelTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setPanelTitle(I18nString title) {
        label.setText(title);
    }

}
