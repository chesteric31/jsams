package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Customer;

/**
 * Customer service interface.
 *
 * @author Eric
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CustomerService {
	
	public void create(Customer customer);

	public void delete(Customer customer);

	public void delete(Long id);

	public void update(Customer customer);

	public Customer findById(Long id);

	public List<Customer> findAll();

}
