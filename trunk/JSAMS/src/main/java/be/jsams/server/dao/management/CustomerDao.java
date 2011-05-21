package be.jsams.server.dao.management;

import java.util.List;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.dao.Dao;
import be.jsams.server.model.management.Customer;

/**
 * Customer DAO interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerDao extends Dao<Customer> {

    /**
     * Find a list of {@link Customer} following the criteria model
     * 
     * @param criteria the criteria model
     * @return a list of {@link Customer}
     */
    List<Customer> findByCriteria(final CustomerBean criteria);

}
