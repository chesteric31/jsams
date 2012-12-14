package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.Desktop;
import be.jsams.common.bean.builder.SocietyBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.dao.management.CustomerDao;
import be.jsams.server.model.management.Customer;
import be.jsams.server.service.AbstractService;
import be.jsams.server.service.management.CustomerService;

/**
 * Customer service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerServiceImpl extends AbstractService implements CustomerService {

    private CustomerDao customerDao;

    /**
     * {@inheritDoc}
     */
    public CustomerBean create(final CustomerBean bean) {
        Customer customer = new Customer(bean);
        Customer persistedCustomer = customerDao.add(customer);
        return getCustomerBeanBuilder().build(persistedCustomer, bean.getSociety());
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
    public List<CustomerBean> findAll(SocietyBean currentSociety) {
        customerDao.setCurrentSociety(currentSociety);
        List<Customer> customers = customerDao.findAll();
        List<CustomerBean> beans = new ArrayList<CustomerBean>();
        for (Customer customer : customers) {
            beans.add(getCustomerBeanBuilder().build(customer, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public CustomerBean findById(final Long id) {
        Customer customer = customerDao.findById(id);
        SocietyBeanBuilder builder = getSocietyBeanBuilder();
        builder.setModel(customer.getSociety());
        SocietyBean societyBean = builder.build(false);
        return getCustomerBeanBuilder().build(customer, societyBean);
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
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        customerDao.setCurrentSociety(currentSociety);
        List<Customer> customers = customerDao.findByCriteria(criteria);
        List<CustomerBean> beans = new ArrayList<CustomerBean>();
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
                beans.add(getCustomerBeanBuilder().build(customer, currentSociety));
            }
        }
        return beans;
    }

    /**
     * @return the {@link CustomerDao}
     */
    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    /**
     * @param customerDao the {@link CustomerDao} to set
     */
    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
