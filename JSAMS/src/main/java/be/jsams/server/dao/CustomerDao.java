package be.jsams.server.dao;

import java.util.List;

import be.jsams.server.model.Customer;

/**
 * Customer DAO interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerDao extends GenericDao<Customer> {

    /**
     * Finds a list of {@link Customer} following the criteria model
     * 
     * @param criteria
     *            the criteria model
     * @return a list of {@link Customer}
     */
    List<Customer> findByCriteria(final Customer criteria);

}
