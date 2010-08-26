package be.jsams.server.dao.impl;

import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.model.ProductCategory;

/**
 * Product Category DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryDaoImpl extends GenericDaoImpl<ProductCategory>
		implements ProductCategoryDao {

	public ProductCategoryDaoImpl(Class<ProductCategory> type) {
		super(type);
	}

}
