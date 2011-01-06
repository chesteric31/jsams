package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.Product;

/**
 * Product service interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ProductService extends Service<Product> {

	/**
	 * Finds all {@link Product} following the criteria product.
	 * 
	 * @param criteria
	 *            the criteria product
	 * @return a list of {@link Product}
	 */
	List<Product> findByCriteria(final Product criteria);

}
