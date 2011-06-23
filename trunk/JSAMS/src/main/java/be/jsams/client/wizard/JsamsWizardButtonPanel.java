package be.jsams.client.wizard;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import be.jsams.client.swing.component.JsamsLabel;

/**
 * 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsWizardButtonPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6245229470738731544L;

    private JsamsLabel statusLabel = new JsamsLabel();

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public JsamsWizardButtonPanel(JsamsWizardComponent component) {
        this.setLayout(new GridBagLayout());
        this.add(statusLabel, new GridBagConstraints(0, 0, 1, 1, 0.7, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.BOTH, new Insets(2, 0, 2, 0), 0, 0));
        this.add(component.getBackButton(), new GridBagConstraints(1, 0, 1, 1, 0.1, 0.0, GridBagConstraints.EAST,
                GridBagConstraints.BOTH, new Insets(2, 0, 2, 0), 0, 0));
        this.add(component.getNextButton(), new GridBagConstraints(2, 0, 1, 1, 0.1, 0.0, GridBagConstraints.EAST,
                GridBagConstraints.BOTH, new Insets(2, 0, 2, 0), 0, 0));
        this.add(component.getFinishButton(), new GridBagConstraints(3, 0, 1, 1, 0.1, 0.0, GridBagConstraints.EAST,
                GridBagConstraints.BOTH, new Insets(2, 0, 2, 0), 0, 0));
        this.add(component.getCancelButton(), new GridBagConstraints(4, 0, 1, 1, 0.1, 0.0, GridBagConstraints.EAST,
                GridBagConstraints.BOTH, new Insets(2, 3, 2, 2), 0, 0));
    }
    
}
