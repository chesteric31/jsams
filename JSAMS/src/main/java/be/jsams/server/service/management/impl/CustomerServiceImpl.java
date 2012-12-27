package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        List<Customer> customers = customerDao.findAll(currentSociety.getId());
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
        if (customer != null) {
            SocietyBeanBuilder builder = getSocietyBeanBuilder();
            builder.setModel(customer.getSociety());
            SocietyBean societyBean = builder.build(false);
            return getCustomerBeanBuilder().build(customer, societyBean);
        } else {
            return null;
        }
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
        SocietyBean society = criteria.getSociety();
        List<Customer> customers = customerDao.findByCriteria(society.getId(), criteria);
        List<CustomerBean> beans = mapModelToBean(society, customers);
        return beans;
    }

    /**
     * Maps the customers to the customerBeans.
     * 
     * @param society the {@link SocietyBean} to use
     * @param customers the customers to map
     * @return the mapped customerBeans.
     */
    private List<CustomerBean> mapModelToBean(SocietyBean society, List<Customer> customers) {
        List<CustomerBean> beans = new ArrayList<CustomerBean>();
        if (customers != null && !customers.isEmpty()) {
            for (Customer customer : customers) {
                beans.add(getCustomerBeanBuilder().build(customer, society));
            }
        }
        return beans;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Double, CustomerBean> findTop5Customers(SocietyBean society) {
        List<Object[]> customers = customerDao.findTop5Customers(society.getId());
        Map<Double, CustomerBean> map = new LinkedHashMap<Double, CustomerBean>();
        if (customers != null && !customers.isEmpty()) {
            for (Object[] object : customers) {
                Double amount = (Double) object[1];
                Customer customer = (Customer) object[0];
                CustomerBean customerBean = getCustomerBeanBuilder().build(customer, society);
                map.put(amount, customerBean);
            }
        }
        return map;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findWithEstimates(SocietyBean society) {
        List<Customer> customers = customerDao.findWithEstimates(society.getId());
        return mapModelToBean(society, customers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerBean> findWithBills(SocietyBean society) {
        List<Customer> customers = customerDao.findWithBills(society.getId());
        return mapModelToBean(society, customers);
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


}
