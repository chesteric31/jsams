package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.sale.DeliveryOrderDetailBeanView;
import be.jsams.server.model.sale.DeliveryOrderDetail;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailBean extends AbstractIdentityBean<DeliveryOrderDetail, DeliveryOrderDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1224722469323652563L;

    /**
     * Constructor
     * 
     * @param model
     *            the {@link DeliveryOrderDetail}
     */
    public DeliveryOrderDetailBean(DeliveryOrderDetail model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderDetailBeanView getView() {
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
