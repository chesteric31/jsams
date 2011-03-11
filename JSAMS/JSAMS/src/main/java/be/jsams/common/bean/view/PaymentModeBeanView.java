package be.jsams.common.bean.view;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.common.bean.model.PaymentModeBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PaymentModeBeanView extends AbstractView<PaymentModeBean, JComboBox, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3753335078032689398L;

    /**
     * Constructor
     * 
     * @param bean the {@link PaymentModeBean}
     */
    public PaymentModeBeanView(PaymentModeBean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public JComboBox createEditView() {
        JComboBox comboBox = null;
        ViewFactory<PaymentModeBean> helper = new ViewFactory<PaymentModeBean>();
        comboBox = helper.createBindingComboComponent(getBean(), true, false, new TranslatableComboBoxRenderer());
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
