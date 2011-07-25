package be.jsams.client.wizard.transfer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * {@link JsamsWizardPanel} to choose the destination document transferred.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DestinationChooserWizardPanel extends JsamsWizardPanel<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private JRadioButton creditNoteRadioButton;
    private ButtonGroup buttonGroup;
    private final int noOneSelected = 0;
    private final int commandSelected = 1;
    private final int deliveryOrderSelected = 2;
    private final int billSelected = 3;
    private final int creditNoteSelected = 4;
    private final int finishSelected = 5;
    private int selectedOption = noOneSelected; // 0 is no selected option

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     */
    public DestinationChooserWizardPanel(JsamsWizardComponent component, TransferBean model) {
        super(component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DESTINATION);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        commandRadioButton = new JRadioButton();
        commandRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = commandSelected;
                    update();
                }
            }
        });
        deliveryOrderRadioButton = new JRadioButton();
        deliveryOrderRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = deliveryOrderSelected;
                    update();
                }
            }
        });
        billRadioButton = new JRadioButton();
        billRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = billSelected;
                    update();
                }
            }
        });
        creditNoteRadioButton = new JRadioButton();
        creditNoteRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = creditNoteSelected;
                    update();
                }
            }
        });
        buttonGroup = new ButtonGroup();
        buttonGroup.add(commandRadioButton);
        buttonGroup.add(deliveryOrderRadioButton);
        buttonGroup.add(billRadioButton);
        buttonGroup.add(creditNoteRadioButton);

        FormLayout layout = new FormLayout("left:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COMMAND.getKey(), commandRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DELIVERY_ORDER.getKey(), deliveryOrderRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BILL.getKey(), billRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CREDIT_NOTE.getKey(), creditNoteRadioButton);

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
        setBackButtonEnabled(true);
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (isRadioSelected()) {
            getModel().setDestinationType(selectedOption);
            switchPanel(TransferWizardDialog.THIRD_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        switchPanel(TransferWizardDialog.FIRST_PANEL);
    }

    /**
     * @return true if one radio is selected, false otherwise
     */
    private boolean isRadioSelected() {
        return selectedOption == commandSelected || selectedOption == deliveryOrderSelected
                || selectedOption == billSelected || selectedOption == creditNoteSelected;
    }

}
