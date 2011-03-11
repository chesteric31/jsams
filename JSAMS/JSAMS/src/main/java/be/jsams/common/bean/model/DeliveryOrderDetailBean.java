package be.jsams.common.bean.model;

import be.jsams.common.bean.view.DeliveryOrderDetailBeanView;
import be.jsams.server.model.DeliveryOrderDetail;

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
}