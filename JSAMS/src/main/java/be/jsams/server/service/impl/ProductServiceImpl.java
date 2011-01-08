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

    /**
     * 
     * @return the {@link ProductDao}
     */
    public ProductDao getProductDao() {
        return productDao;
    }

    /**
     * 
     * @param productDao the {@link ProductDao} to set
     */
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    /**
     * {@inheritDoc}
     */
    public void create(final Product product) {
        productDao.add(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Product product) {
        productDao.remove(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
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
    public List<Product> findByCriteria(final Product criteria) {
        return productDao.findByCriteria(criteria);
    }

    /**
     * {@inheritDoc}
     */
    public Product findById(final Long id) {
        return productDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Product product) {
        productDao.update(product);
    }

}
