package be.jsams.client.wizard.transfer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.validator.wizard.DestinationValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * {@link JsamsWizardPanel} to choose the destination document transferred.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DestinationChooserWizardPanel extends JsamsWizardPanel<TransferBean, DestinationValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private JRadioButton creditNoteRadioButton;
    private final int commandSelected = 1;
    private final int deliveryOrderSelected = 2;
    private final int billSelected = 3;
    private final int creditNoteSelected = 4;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link DestinationValidator}
     */
    public DestinationChooserWizardPanel(TransferWizardDialog parent,
            JsamsWizardComponent component, TransferBean model, DestinationValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_DESTINATION, validator);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        ViewFactory<TransferBean> viewFactory = getViewFactory();
        commandRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.DESTINATION_TYPE_PROPERTY,
                commandSelected, true, false);
        commandRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });
        deliveryOrderRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.DESTINATION_TYPE_PROPERTY, deliveryOrderSelected, true, false);
        deliveryOrderRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });
        billRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.DESTINATION_TYPE_PROPERTY,
                billSelected, true, false);
        billRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });
        creditNoteRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.DESTINATION_TYPE_PROPERTY, creditNoteSelected, true, false);
        creditNoteRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });

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
        setFinishButtonEnabled(false);
        setBackButtonEnabled(true);
        super.update();
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (prePerformNext()) {
            switchPanel(TransferWizardDialog.THIRD_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        switchPanel(TransferWizardDialog.FIRST_PANEL);
    }

}
