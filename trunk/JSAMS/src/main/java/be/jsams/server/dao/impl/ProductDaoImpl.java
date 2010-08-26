package be.jsams.server.dao.impl;

import be.jsams.server.dao.ProductDao;
import be.jsams.server.model.Product;

/**
 * Product DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductDaoImpl extends GenericDaoImpl<Product> implements
		ProductDao {

	public ProductDaoImpl(Class<Product> type) {
		super(type);
	}
	
}
