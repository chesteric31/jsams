package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
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
        return new CustomerBean(customer, bean.getSociety());
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
        SocietyBeanBuilder builder = getSocietyBeanBuilder();
        for (Customer customer : customers) {
            builder.setModel(customer.getSociety());
            SocietyBean bean = builder.build(false);
            beans.add(new CustomerBean(customer, bean));
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
        CustomerBean bean = new CustomerBean(customer, societyBean);
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
        criteria.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<Customer> customers = customerDao.findByCriteria(criteria);
        List<CustomerBean> beans = new ArrayList<CustomerBean>();
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
                beans.add(new CustomerBean(customer, criteria.getSociety()));
            }
        }
        return beans;
    }

}
