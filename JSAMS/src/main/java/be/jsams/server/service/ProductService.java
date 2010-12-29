package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Product;

/**
 * Product service interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductService {

	public void create(Product product);

	public void delete(Product product);

	public void delete(Long id);

	public void update(Product product);

	public Product findById(Long id);

	public List<Product> findAll();

	public List<Product> findByCriteria(final Product criteria);

}
