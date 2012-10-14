package be.jsams.client.wizard.transfer;

import javax.swing.JRadioButton;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.validator.wizard.SourceTypeValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * {@link JsamsWizardPanel} to choose the source document to transfer.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SourceChooserWizardPanel extends JsamsWizardPanel<TransferBean, SourceTypeValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton estimateRadioButton;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private static final int ESTIMATE_SELECTED = 1;
    private static final int COMMAND_SELECTED = 2;
    private static final int DELIVERY_ORDER_SELECTED = 3;
    private static final int BILL_SELECTED = 4;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog}
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link SourceTypeValidator}
     */
    public SourceChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component, TransferBean model,
            SourceTypeValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_SOURCE, validator);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        ViewFactory<TransferBean> viewFactory = getViewFactory();
        estimateRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.SOURCE_TYPE_PROPERTY,
                ESTIMATE_SELECTED, true, false);
        
        commandRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.SOURCE_TYPE_PROPERTY,
                COMMAND_SELECTED, true, false);
        
        deliveryOrderRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.SOURCE_TYPE_PROPERTY, DELIVERY_ORDER_SELECTED, true, false);
        
        billRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.SOURCE_TYPE_PROPERTY,
                BILL_SELECTED, true, false);

        FormLayout layout = new FormLayout("left:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ESTIMATE.getKey(), estimateRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_COMMAND.getKey(), commandRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_DELIVERY_ORDER.getKey(), deliveryOrderRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_BILL.getKey(), billRadioButton);
        this.add(builder.getPanel());
        super.update();
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (prePerformNext()) {
            switchPanel(TransferWizardDialog.SECOND_PANEL);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

}
