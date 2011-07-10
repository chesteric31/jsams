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
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DestinationChooserWizardPanel extends JsamsWizardPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private JRadioButton creditNoteRadioButton;
    private ButtonGroup buttonGroup;
    private int selectedOption = 0; // 0 is no selected option

    /**
     * Constructor.
     * 
     * @param component the {@link JsamsWizardComponent}
     */
    public DestinationChooserWizardPanel(JsamsWizardComponent component) {
        super(component, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_SOURCE);
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
                    selectedOption = 1;
                    update();
                }
            }
        });
        deliveryOrderRadioButton = new JRadioButton();
        deliveryOrderRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 2;
                    update();
                }
            }
        });
        billRadioButton = new JRadioButton();
        billRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 3;
                    update();
                }
            }
        });
        creditNoteRadioButton = new JRadioButton();
        creditNoteRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    selectedOption = 4;
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
        if (selectedOption == 1 || selectedOption == 2 || selectedOption == 3 || selectedOption == 4
                || selectedOption == 5) {
            nextEnabled = true;
        }
        setNextButtonEnabled(nextEnabled);
        boolean finishEnabled = selectedOption == 6;
        setFinishButtonEnabled(finishEnabled);
        setBackButtonEnabled(true);
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        if (selectedOption == 0) {
            switchPanel(TransferWizardDialog.SECOND_PANEL);
        }
    }

}
