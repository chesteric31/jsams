package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.model.sale.DeliveryOrder;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderBean extends AbstractIdentityBean<DeliveryOrder, DeliveryOrderBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6774873021985339541L;

    /**
     * Constructor
     * 
     * @param model
     *            the {@link DeliveryOrder}
     */
    public DeliveryOrderBean(DeliveryOrder model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        // TODO Auto-generated method stub
        
    }

}
