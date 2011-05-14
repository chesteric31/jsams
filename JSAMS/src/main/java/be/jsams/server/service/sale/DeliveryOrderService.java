package be.jsams.server.service.sale;

import java.util.List;

import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.service.Service;

/**
 * Delivery Order service interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface DeliveryOrderService extends Service<DeliveryOrderBean> {

    /**
     * Finds all {@link DeliveryOrderBean} following the criteria
     * {@link DeliveryOrderBean}.
     * 
     * @param criteria the criteria {@link DeliveryOrderBean}
     * @return a list of {@link DeliveryOrderBean}
     */
    List<DeliveryOrderBean> findByCriteria(final DeliveryOrderBean criteria);

}
