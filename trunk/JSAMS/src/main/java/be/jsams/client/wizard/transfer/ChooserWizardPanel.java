package be.jsams.client.wizard.transfer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

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
    
    private final int noOneSelected = 0;
    private final int fullModeSelected = 1;
    private final int partialModeSelected = 2;
    private final int fullGroupedModeSelected = 3;
    private final int partialGroupedModeSelected = 4;
    private final int finishSelected = 5;
    private int selectedOption = noOneSelected; // 0 is no selected option

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public ChooserWizardPanel(JsamsWizardComponent component) {
        super(component, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_TRANSFER_MODE);
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
                    selectedOption = fullModeSelected;
                    update();
                }
            }
        });
        partialTranferRadioButton = new JRadioButton();
        partialTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = partialModeSelected;
                    update();
                }
            }
        });
        fullGroupedTranferRadioButton = new JRadioButton();
        fullGroupedTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = fullGroupedModeSelected;
                    update();
                }
            }
        });
        partialGroupedTranferRadioButton = new JRadioButton();
        partialGroupedTranferRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = partialGroupedModeSelected;
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
        if (isRadioSelected()) {
            nextEnabled = true;
        }
        setNextButtonEnabled(nextEnabled);
        boolean finishEnabled = selectedOption == finishSelected;
        setFinishButtonEnabled(finishEnabled);
        setBackButtonEnabled(false); // there is no way back
    }

    /**
     * @return true if one radio is selected, false otherwise
     */
    private boolean isRadioSelected() {
        return selectedOption == fullModeSelected || selectedOption == partialModeSelected
                || selectedOption == fullGroupedModeSelected || selectedOption == partialGroupedModeSelected;
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (isRadioSelected()) {
            switchPanel(TransferWizardDialog.SECOND_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

}
