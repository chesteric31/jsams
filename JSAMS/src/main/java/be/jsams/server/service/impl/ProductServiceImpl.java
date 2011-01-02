package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.ProductDao;
import be.jsams.server.model.Product;
import be.jsams.server.service.ProductService;

/**
 * Product service implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

    /**
     * {@inheritDoc}
     */
	public void create(Product product) {
		productDao.add(product);
	}

    /**
     * {@inheritDoc}
     */
	public void delete(Product product) {
		productDao.remove(product);
	}

    /**
     * {@inheritDoc}
     */
	public void delete(Long id) {
		productDao.remove(id);
	}

    /**
     * {@inheritDoc}
     */
	public List<Product> findAll() {
		return productDao.findAll();
	}

    /**
     * {@inheritDoc}
     */
	public List<Product> findByCriteria(Product criteria) {
		return productDao.findByCriteria(criteria);
	}

    /**
     * {@inheritDoc}
     */
	public Product findById(Long id) {
		return productDao.findById(id);
	}

    /**
     * {@inheritDoc}
     */
	public void update(Product product) {
		productDao.update(product);
	}

}
