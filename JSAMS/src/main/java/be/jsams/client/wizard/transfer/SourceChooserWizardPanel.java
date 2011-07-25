package be.jsams.client.wizard.transfer;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JRadioButton;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.swing.component.AbstractJsamsFrame;
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
public class SourceChooserWizardPanel extends JsamsWizardPanel<TransferBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4364616680816295101L;
    private JRadioButton estimateRadioButton;
    private JRadioButton commandRadioButton;
    private JRadioButton deliveryOrderRadioButton;
    private JRadioButton billRadioButton;
    private final int estimateSelected = 1;
    private final int commandSelected = 2;
    private final int deliveryOrderSelected = 3;
    private final int billSelected = 4;

    /**
     * Constructor.
     * 
     * @param parent the {@link TransferWizardDialog}
     * @param component the {@link JsamsWizardComponent}
     * @param model the model
     */
    public SourceChooserWizardPanel(TransferWizardDialog parent, JsamsWizardComponent component, TransferBean model) {
        super(parent, component, model, JsamsI18nLabelResource.LABEL_TRANSFER_CHOOSE_SOURCE);
        initComponents();
    }

    /**
     * Initialize the components.
     */
    private void initComponents() {
        ViewFactory<TransferBean> viewFactory = getViewFactory();
        estimateRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.SOURCE_TYPE_PROPERTY,
                estimateSelected, true, false);
        estimateRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });
        commandRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.SOURCE_TYPE_PROPERTY,
                commandSelected, true, false);
        commandRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });
        deliveryOrderRadioButton = viewFactory.createBindingRadioComponent(getModel(),
                TransferBean.SOURCE_TYPE_PROPERTY, deliveryOrderSelected, true, false);
        deliveryOrderRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });
        billRadioButton = viewFactory.createBindingRadioComponent(getModel(), TransferBean.SOURCE_TYPE_PROPERTY,
                billSelected, true, false);
        billRadioButton.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    update();
                }
            }
        });

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
        if (sourceTypeIsSelected()) {
            nextEnabled = true;
        }
        setNextButtonEnabled(nextEnabled);
        setFinishButtonEnabled(false);
        setBackButtonEnabled(false);
        super.update();
    }

    /**
     * {@inheritDoc}
     */
    public void next() {
        if (sourceTypeIsSelected() && prePerformNext(getModel())) {
            switchPanel(TransferWizardDialog.SECOND_PANEL);
        }
    }

    /**
     * @return true if a source type is selected, false otherwise
     */
    private boolean sourceTypeIsSelected() {
        return getModel().getSourceType() != 0;
    }

    /**
     * {@inheritDoc}
     */
    public void back() {
    }

}
