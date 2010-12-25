package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.CustomerDao;
import be.jsams.server.model.Customer;
import be.jsams.server.service.CustomerService;

/**
 * Customer service implementation.
 *
 * @author Eric
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;

	public CustomerDao getCustomerDao() {
		return customerDao;
	}

	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	public void create(Customer customer) {
		customerDao.add(customer);
	}

	public void delete(Customer customer) {
		customerDao.remove(customer);
	}

	public void delete(Long id) {
		customerDao.remove(id);
	}

	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	public Customer findById(Long id) {
		return customerDao.findById(id);
	}

	public void update(Customer customer) {
		customerDao.update(customer);
	}

}