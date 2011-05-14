package be.jsams.server.dao.sale.impl;

import be.jsams.server.dao.impl.DaoImpl;
import be.jsams.server.dao.sale.DeliveryOrderDetailDao;
import be.jsams.server.model.sale.DeliveryOrderDetail;

/**
 * Delivery order detail DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailDaoImpl extends DaoImpl<DeliveryOrderDetail> implements DeliveryOrderDetailDao {

    /**
     * Constructor
     * 
     * @param type the class type
     */
    public DeliveryOrderDetailDaoImpl(final Class<DeliveryOrderDetail> type) {
        super(type);
    }
    
}
