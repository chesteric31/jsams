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
 * {@link JsamsWizardPanel} to choose the source document to transfer.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SourceChooserWizardPanel extends JsamsWizardPanel<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton estimateRadioButton;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private ButtonGroup buttonGroup;
    private final int noOneSelected = 0;
    private final int estimateSelected = 1;
    private final int commandSelected = 2;
    private final int deliveryOrderSelected = 3;
    private final int billSelected = 4;
    private final int finishSelected = 5;
    private int selectedOption = noOneSelected; // 0 is no selected option

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     */
    public SourceChooserWizardPanel(JsamsWizardComponent component, TransferBean model) {
        super(component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_SOURCE);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        estimateRadioButton = new JRadioButton();
        estimateRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = estimateSelected;
                    update();
                }
            }
        });
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
        buttonGroup = new ButtonGroup();
        buttonGroup.add(estimateRadioButton);
        buttonGroup.add(commandRadioButton);
        buttonGroup.add(deliveryOrderRadioButton);
        buttonGroup.add(billRadioButton);

        FormLayout layout = new FormLayout("left:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ESTIMATE.getKey(), estimateRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COMMAND.getKey(), commandRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DELIVERY_ORDER.getKey(), deliveryOrderRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BILL.getKey(), billRadioButton);

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
        setBackButtonEnabled(false);
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (isRadioSelected()) {
            getModel().setSourceType(selectedOption);
            switchPanel(TransferWizardDialog.SECOND_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

    /**
     * @return true if one radio is selected, false otherwise
     */
    private boolean isRadioSelected() {
        return selectedOption == estimateSelected || selectedOption == commandSelected
                || selectedOption == deliveryOrderSelected || selectedOption == billSelected;
    }

}
