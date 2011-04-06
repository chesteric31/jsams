package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Customer;

/**
 * Customer service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerService extends Service<Customer> {

    /**
     * Finds all {@link Customer} following the criteria customer.
     * 
     * @param criteria
     *            the criteria customer
     * @return a list of {@link Customer}
     */
    List<Customer> findByCriteria(final Customer criteria);

}
