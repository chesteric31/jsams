package be.jsams.client.wizard.transfer;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import jwizardcomponent.example.DynamicJWizard;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;

/**
 * First wizard panel in the wizard process of transfer:
 * first step: choose between full or partial transfer.
 *
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public class ChooserWizardPanel extends JsamsWizardPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6391869347344419550L;
    private JRadioButton fullTransferRadioButton;
    private JRadioButton partialTranferRadioButton;
    private ButtonGroup buttonGroup;
    private char selectedOption = 'N'; // 'N' is no option selected
    // 'A' & 'B' stands for options
    
    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public ChooserWizardPanel(JsamsWizardComponent component) {
        super(component);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        fullTransferRadioButton = new JRadioButton();
        partialTranferRadioButton = new JRadioButton();
        buttonGroup = new ButtonGroup();
        buttonGroup.add(fullTransferRadioButton);
        buttonGroup.add(partialTranferRadioButton);
        this.setLayout(new GridBagLayout());

        this.add(fullTransferRadioButton, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        fullTransferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 'A';
                    update();
                }
            }
        });
        this.add(new JsamsLabel(JsamsI18nLabelResource.LABEL_FULL_TRANSFER), new GridBagConstraints(1, 0, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        this.add(partialTranferRadioButton, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST,
                GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
        partialTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 'B';
                    update();
                }
            }
        });
        this.add(new JsamsLabel(JsamsI18nLabelResource.LABEL_PARTIAL_TRANSFER), new GridBagConstraints(1, 1, 1, 1, 0.0,
                0.0, GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        setNextButtonEnabled((selectedOption == 'A') || (selectedOption == 'B'));
        setFinishButtonEnabled(selectedOption == 'F');
        setBackButtonEnabled(false); // there is no way back
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (selectedOption == 'A') {
            switchPanel(DynamicJWizard.PANEL_OPTION_A);
        } else if (selectedOption == 'B') {
            switchPanel(DynamicJWizard.PANEL_OPTION_B);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

}
