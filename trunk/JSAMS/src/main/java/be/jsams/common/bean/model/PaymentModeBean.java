package be.jsams.common.bean.model;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.view.PaymentModeBeanView;
import be.jsams.server.model.PaymentMode;

import com.jgoodies.common.collect.ArrayListModel;

/**
 * Bean model for {@link PaymentMode} object.
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
        list.add(null);
        for (PaymentMode mode : JsamsApplicationContext.getPaymentModeDao().findAll()) {
            list.add(new PaymentModeBean(mode));
        }
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
