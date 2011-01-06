package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.model.ProductCategory;
import be.jsams.server.service.ProductCategoryService;

/**
 * Product category service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryServiceImpl implements ProductCategoryService {

	private ProductCategoryDao productCategoryDao;

	public ProductCategoryDao getProductCategoryDao() {
		return productCategoryDao;
	}

	public void setProductCategoryDao(ProductCategoryDao productCategoryDao) {
		this.productCategoryDao = productCategoryDao;
	}

	/**
	 * {@inheritDoc}
	 */
	public void create(ProductCategory model) {
		productCategoryDao.add(model);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(ProductCategory model) {
		productCategoryDao.remove(model);
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Long id) {
		productCategoryDao.remove(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ProductCategory> findAll() {
		return productCategoryDao.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public ProductCategory findById(Long id) {
		return productCategoryDao.findById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(ProductCategory model) {
		productCategoryDao.update(model);
	}

}
