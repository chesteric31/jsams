package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.model.management.Customer;
import be.jsams.server.service.management.CustomerService;

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
     * @param customerDao
     *            the {@link CustomerDao} to set
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * {@inheritDoc}
     */
    public CustomerBean create(final CustomerBean bean) {
        Customer customer = new Customer(bean);
        customerDao.add(customer);
        return new CustomerBean(customer);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final CustomerBean bean) {
        Customer customer = customerDao.findById(bean.getId());
        customerDao.delete(customer);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        customerDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<CustomerBean> findAll() {
        List<Customer> customers = customerDao.findAll();
        List<CustomerBean> beans = new ArrayList<CustomerBean>();
        for (Customer customer : customers) {
            beans.add(new CustomerBean(customer));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public CustomerBean findById(final Long id) {
        Customer customer = customerDao.findById(id);
        CustomerBean bean = new CustomerBean(customer);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final CustomerBean bean) {
        Customer customer = new Customer(bean);
        customerDao.update(customer);
    }

    /**
     * {@inheritDoc}
     */
    public List<CustomerBean> findByCriteria(final CustomerBean criteria) {
        List<Customer> customers = customerDao.findByCriteria(criteria);
        List<CustomerBean> beans = new ArrayList<CustomerBean>();
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
                beans.add(new CustomerBean(customer));
            }
        }
        return beans;
    }

}
