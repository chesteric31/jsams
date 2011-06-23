package be.jsams.client.wizard;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.wizard.action.CancelAction;
import be.jsams.client.wizard.action.FinishAction;

/**
 * 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsWizardFrame extends AbstractJsamsFrame {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1837751453913195941L;

    private JsamsWizardComponent component;
    private JsamsLabel panelTitleLabel;
    
    /**
     * Constructor.
     */
    public JsamsWizardFrame() {
        super();
        initComponents();
    }

    /**
     * Initialize all the components.
     */
    private void initComponents() {
        component = new DefaultJsamsWizardComponent();
        component.addPropertyChangeListener(new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent event) {
                setPanelTitle(((JsamsWizardPanel) event.getNewValue()).getTitle());
            }
        });

        this.getContentPane().setLayout(new GridBagLayout());
        this.getContentPane().add(
                createTitlePanel(),
                new GridBagConstraints(0, 0, 1, 1, 1.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(5, 5, 5, 5), 0, 0));

        this.getContentPane().add(
                new JSeparator(),
                new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(1, 1, 1, 1), 0, 0));

        this.getContentPane().add(
                component.getPanelsContainer(),
                new GridBagConstraints(0, 2, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                        new Insets(0, 0, 0, 0), 0, 0));

        this.getContentPane().add(
                new JSeparator(),
                new GridBagConstraints(0, 3, 1, 1, 1.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH,
                        new Insets(1, 1, 1, 1), 0, 0));

        this.getContentPane().add(
                createButtonPanel(),
                new GridBagConstraints(0, 4, 1, 1, 1.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                        new Insets(5, 5, 5, 5), 0, 0));

        component.setFinishAction(createFinishAction());
        component.setCancelAction(createCancelAction());
        handleWindowClosing();
    }
    
    /**
     * {@inheritDoc}
     */
    public void show() {
        component.updateComponents();
        super.setVisible(true);
    }
    
    /**
     * Set the title.
     * 
     * @param title the title to set
     */
    protected void setPanelTitle(I18nString title) {
        panelTitleLabel.setText(title.getTranslation());
    }
    
    /**
     * Build titled panel.
     * 
     * @return the built titled panel
     */
    protected JPanel createTitlePanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTitleLabel = new JsamsLabel();
        panelTitleLabel.setHorizontalAlignment(SwingConstants.LEADING);
        panel.add(panelTitleLabel);
        return panel;
    }

    /**
     * Build button panel.
     * 
     * @return the built button panel
     */
    protected JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout());
        panel.add(component.getBackButton());
        panel.add(component.getNextButton());
        panel.add(component.getFinishButton());
        panel.add(component.getCancelButton());
        return panel;
    }
    
    /**
     * Build {@link FinishAction}.
     * 
     * @return the built {@link FinishAction}
     */
    protected FinishAction createFinishAction() {
        return new FinishAction(component) {
            public void performAction() {
                dispose();
            }
        };
    }

    /**
     * Build {@link CancelAction}.
     * 
     * @return the built {@link CancelAction}
     */
    protected CancelAction createCancelAction() {
        return new CancelAction(component) {
            public void performAction() {
                dispose();
            }
        };
    }

    /**
     * Handle the window closing.
     */
    protected void handleWindowClosing() {
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                component.getCancelAction().performAction();
            }
        });
    }
    
}
