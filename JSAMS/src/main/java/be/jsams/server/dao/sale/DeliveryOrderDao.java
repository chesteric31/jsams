package be.jsams.server.dao.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.sale.DeliveryOrder;

/**
 * Delivery Order DAO interface.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface DeliveryOrderDao extends Dao<DeliveryOrder> {

    /**
     * Finds a list of {@link DeliveryOrder} following the criteria model.
     * 
     * @param criteria the criteria model
     * @return a list of {@link DeliveryOrder}
     */
    List<DeliveryOrder> findByCriteria(final DeliveryOrderBean criteria);
    
}
