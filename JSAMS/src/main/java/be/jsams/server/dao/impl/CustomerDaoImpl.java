package be.jsams.server.dao.impl;

import be.jsams.server.dao.CustomerDao;
import be.jsams.server.model.Customer;

/**
 * Customer DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerDaoImpl extends GenericDaoImpl<Customer> implements CustomerDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public CustomerDaoImpl(Class<Customer> type) {
        super(type);
    }

}
