package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.CustomerBean;

/**
 * Customer service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerService extends Service<CustomerBean> {

    /**
     * Finds all {@link CustomerBean} following the criteria customer.
     * 
     * @param criteria
     *            the criteria customer
     * @return a list of {@link CustomerBean}
     */
    List<CustomerBean> findByCriteria(final CustomerBean criteria);

}