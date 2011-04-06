package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.common.bean.model.PaymentModeBean;

/**
 * Implementation of all sorts of views for {@link PaymentModeBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PaymentModeBeanView extends AbstractView<PaymentModeBean, JsamsComboBox, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3753335078032689398L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link PaymentModeBean}
     */
    public PaymentModeBeanView(PaymentModeBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JsamsComboBox createEditView() {
        ViewFactory<PaymentModeBean> helper = new ViewFactory<PaymentModeBean>();
        JsamsComboBox comboBox = helper.createBindingComboComponent(getBean(), true, false,
                new TranslatableComboBoxRenderer());
        return comboBox;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
