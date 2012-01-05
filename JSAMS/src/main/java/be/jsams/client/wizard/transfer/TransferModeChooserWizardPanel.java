package be.jsams.client.wizard.transfer;

import javax.swing.JRadioButton;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
import be.jsams.client.validator.wizard.TransferModeValidator;
import be.jsams.client.wizard.JsamsWizardComponent;
import be.jsams.client.wizard.JsamsWizardPanel;
import be.jsams.common.bean.model.transfer.TransferBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Wizard panel in the wizard process of transfer: choose between full, partial,
 * full grouped, partial grouped transfer.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class TransferModeChooserWizardPanel extends JsamsWizardPanel<TransferBean, TransferModeValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6391869347344419550L;
    private JRadioButton fullTransferRadioButton;
    private JRadioButton partialTranferRadioButton;
    private JRadioButton fullGroupedTranferRadioButton;
    private JRadioButton partialGroupedTranferRadioButton;
    private final int fullModeSelected = 1;
    private final int partialModeSelected = 2;
    private final int fullGroupedModeSelected = 3;
    private final int partialGroupedModeSelected = 4;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog} parent
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     * @param validator the {@link TransferModeValidator}
     */
    public TransferModeChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component,
            TransferBean model, TransferModeValidator validator) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_TRANSFER_MODE, validator);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        ViewFactory<TransferBean> viewFactory = getViewFactory();
        fullTransferRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.TRANSFER_MODE_PROPERTY, fullModeSelected, true, false);

        partialTranferRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.TRANSFER_MODE_PROPERTY, partialModeSelected, true, false);

        fullGroupedTranferRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.TRANSFER_MODE_PROPERTY, fullGroupedModeSelected, true, false);

        partialGroupedTranferRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.TRANSFER_MODE_PROPERTY, partialGroupedModeSelected, true, false);

        FormLayout layout = new FormLayout("left:p, 3dlu, p", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, AbstractJsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FULL_TRANSFER.getKey(), fullTransferRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PARTIAL_TRANSFER.getKey(), partialTranferRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FULL_GROUPED_TRANSFER.getKey(), fullGroupedTranferRadioButton);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PARTIAL_GROUPED_TRANSFER.getKey(),
                partialGroupedTranferRadioButton);
        this.add(builder.getPanel());
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (prePerformNext()) {
            switch (getModel().getTransferMode()) {
            case fullModeSelected:
                switchPanel(TransferWizardDialog.FIRTH_PANEL_FULL_MODE);
                break;
            case partialModeSelected:
                switchPanel(TransferWizardDialog.FIRTH_PANEL_PARTIAL_MODE);
                break;
            case fullGroupedModeSelected:
                switchPanel(TransferWizardDialog.FIRTH_PANEL_FULL_GROUPED_MODE);
                break;
            case partialGroupedModeSelected:
                switchPanel(TransferWizardDialog.FIRTH_PANEL_PARTIAL_GROUPED_MODE);
                break;
            default:
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
        getModel().setTransferMode(0);
        getParentDialog().getStatusBar().clear();
        switchPanel(TransferWizardDialog.SECOND_PANEL);
    }

}
