package be.jsams.client.wizard.transfer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import jwizardcomponent.example.DynamicJWizard;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * First wizard panel in the wizard process of transfer: first step: choose
 * between full, partial, full grouped, partial grouped transfer.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class ChooserWizardPanel extends JsamsWizardPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6391869347344419550L;
    private JRadioButton fullTransferRadioButton;
    private JRadioButton partialTranferRadioButton;
    private JRadioButton fullGroupedTranferRadioButton;
    private JRadioButton partialGroupedTranferRadioButton;
    private ButtonGroup buttonGroup;
    private int selectedOption = 0; // 0 is no selected option

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
        fullTransferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 1;
                    update();
                }
            }
        });
        partialTranferRadioButton = new JRadioButton();
        partialTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 2;
                    update();
                }
            }
        });
        fullGroupedTranferRadioButton = new JRadioButton();
        fullGroupedTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 3;
                    update();
                }
            }
        });
        partialGroupedTranferRadioButton = new JRadioButton();
        partialGroupedTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 4;
                    update();
                }
            }
        });
        buttonGroup = new ButtonGroup();
        buttonGroup.add(fullTransferRadioButton);
        buttonGroup.add(partialTranferRadioButton);
        buttonGroup.add(fullGroupedTranferRadioButton);
        buttonGroup.add(partialGroupedTranferRadioButton);

        FormLayout layout = new FormLayout("left:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FULL_TRANSFER.getKey(), fullTransferRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PARTIAL_TRANSFER.getKey(), partialTranferRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FULL_GROUPED_TRANSFER.getKey(), fullGroupedTranferRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PARTIAL_GROUPED_TRANSFER.getKey(),
                partialGroupedTranferRadioButton);

        builder.nextLine();
        this.add(builder.getPanel());
    }

    /**
     * {@inheritDoc}
     */
    public void update() {
        boolean nextEnabled = false;
        if (selectedOption == 1 || selectedOption == 2 || selectedOption == 3 || selectedOption == 4) {
            nextEnabled = true;
        }
        setNextButtonEnabled(nextEnabled);
        boolean finishEnabled = selectedOption == 5;
        setFinishButtonEnabled(finishEnabled);
        setBackButtonEnabled(false); // there is no way back
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (selectedOption == 1) {
            switchPanel(DynamicJWizard.PANEL_OPTION_A);
        } else if (selectedOption == 2) {
            switchPanel(DynamicJWizard.PANEL_OPTION_B);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

}
