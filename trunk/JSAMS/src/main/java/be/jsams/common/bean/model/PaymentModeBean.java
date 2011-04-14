package be.jsams.common.bean.model;

import be.jsams.common.bean.view.PaymentModeBeanView;
import be.jsams.server.model.PaymentMode;

import com.jgoodies.common.collect.ObservableList;

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

    /**
     * Constructor.
     * 
     * @param model the {@link PaymentMode}
     */
    public PaymentModeBean(PaymentMode model) {
        super(model);
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     */
    public PaymentModeBean(ObservableList<PaymentModeBean> list) {
        super();
        setListModel(list);
        setSelection(list.get(0));
    }

    /**
     * Constructor
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link PaymentMode} object
     */
    public PaymentModeBean(ObservableList<PaymentModeBean> list, PaymentMode model) {
        this(model);
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
