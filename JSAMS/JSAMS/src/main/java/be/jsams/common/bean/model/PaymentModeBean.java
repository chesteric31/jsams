package be.jsams.common.bean.model;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.view.PaymentModeBeanView;
import be.jsams.server.model.PaymentMode;

import com.jgoodies.common.collect.ArrayListModel;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PaymentModeBean extends AbstractTranslatableIdentityBean<PaymentMode, PaymentModeBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3641238456200690731L;

    private static ArrayListModel<PaymentModeBean> list = new ArrayListModel<PaymentModeBean>();
    static {
        List<PaymentMode> allPaymentModes = JsamsApplicationContext.getPaymentModeDao().findAll();
        List<PaymentModeBean> beans = new ArrayList<PaymentModeBean>();
        for (PaymentMode paymentMode : allPaymentModes) {
            beans.add(new PaymentModeBean(paymentMode));
        }
        list.add(null);
        list.addAll(beans);
    }

    /**
     * Default constructor.
     */
    public PaymentModeBean() {
        super();
        setListModel(list);
        setSelection(list.get(0));
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link PaymentMode}
     */
    public PaymentModeBean(PaymentMode model) {
        super(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PaymentModeBeanView getView() {
        return new PaymentModeBeanView(this);
    }

}
