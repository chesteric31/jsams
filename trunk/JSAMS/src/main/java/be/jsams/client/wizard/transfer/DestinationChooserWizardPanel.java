package be.jsams.client.wizard.transfer;

import javax.swing.JRadioButton;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.validator.wizard.DestinationTypeValidator;
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
public class DestinationChooserWizardPanel extends JsamsWizardPanel<TransferBean, DestinationTypeValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private JRadioButton creditNoteRadioButton;
    private static final int COMMAND_SELECTED = 1;
    private static final int DELIVERY_ORDER_SELECTED = 2;
    private static final int BILL_SELECTED = 3;
    private static final int CREDIT_NOTE_SELECTED = 4;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link DestinationTypeValidator}
     */
    public DestinationChooserWizardPanel(TransferWizardDialog parent,
            JsamsWizardComponent component, TransferBean model, DestinationTypeValidator validator) {
        super(parent, component, model, I18nLabelResource.LABEL_TRANSFER_CHOOSE_DESTINATION, validator);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        ViewFactory<TransferBean> viewFactory = getViewFactory();
        commandRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.DESTINATION_TYPE_PROPERTY,
                COMMAND_SELECTED, true, false);

        deliveryOrderRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.DESTINATION_TYPE_PROPERTY, DELIVERY_ORDER_SELECTED, true, false);

        billRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.DESTINATION_TYPE_PROPERTY,
                BILL_SELECTED, true, false);

        creditNoteRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.DESTINATION_TYPE_PROPERTY, CREDIT_NOTE_SELECTED, true, false);

        FormLayout layout = new FormLayout("left:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(I18nLabelResource.LABEL_COMMAND.getKey(), commandRadioButton);
        builder.appendI15d(I18nLabelResource.LABEL_DELIVERY_ORDER.getKey(), deliveryOrderRadioButton);
        builder.appendI15d(I18nLabelResource.LABEL_BILL.getKey(), billRadioButton);
        builder.appendI15d(I18nLabelResource.LABEL_CREDIT_NOTE.getKey(), creditNoteRadioButton);
        this.add(builder.getPanel());
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
        getModel().setDestinationType(0);
        getParentDialog().getStatusBar().clear();
        switchPanel(TransferWizardDialog.FIRST_PANEL);
    }

}
