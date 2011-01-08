package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.CustomerDao;
import be.jsams.server.model.Customer;
import be.jsams.server.service.CustomerService;

/**
 * Customer service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    /**
     * 
     * @return the {@link CustomerDao}
     */
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    /**
     * 
     * @param customerDao the {@link CustomerDao} to set
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * {@inheritDoc}
     */
    public void create(final Customer customer) {
        customerDao.add(customer);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Customer customer) {
        customerDao.remove(customer);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        customerDao.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Customer findById(final Long id) {
        return customerDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Customer customer) {
        customerDao.update(customer);
    }

    /**
     * {@inheritDoc}
     */
    public List<Customer> findByCriteria(final Customer criteria) {
        return customerDao.findByCriteria(criteria);
    }

}
