package be.jsams.common.bean.view;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.common.bean.model.PaymentModeBean;

/**
 * Implementation of view for {@link PaymentModeBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PaymentModeBeanView extends AbstractBeanView<PaymentModeBean> implements Editable<JsamsComboBox> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3753335078032689398L;

    /**
     * Constructor.
     * 
     * @param bean the {@link PaymentModeBean}
     */
    public PaymentModeBeanView(PaymentModeBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JsamsComboBox createEditView() {
        ViewFactory<PaymentModeBean> viewFactory = getViewFactory();
        JsamsComboBox comboBox = viewFactory.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
        return comboBox;
    }

}
