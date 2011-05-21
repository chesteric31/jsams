package be.jsams.server.service.management;

import java.util.List;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.service.Service;

/**
 * Customer service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerService extends Service<CustomerBean> {

    /**
     * Find all {@link CustomerBean} following the criteria customer.
     * 
     * @param criteria the criteria customer
     * @return a list of {@link CustomerBean}
     */
    List<CustomerBean> findByCriteria(final CustomerBean criteria);

}
