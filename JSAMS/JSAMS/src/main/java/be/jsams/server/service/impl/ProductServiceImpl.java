package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.ProductBean;
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
    public ProductBean create(final ProductBean bean) {
        Product product = new Product(bean);
        productDao.add(product);
        return new ProductBean(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final ProductBean bean) {
        Product product = new Product(bean);
        productDao.delete(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        productDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductBean> findAll() {
        List<Product> products = productDao.findAll();
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for (Product product : products) {
            beans.add(new ProductBean(product));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductBean> findByCriteria(final ProductBean criteria) {
        List<Product> products = productDao.findByCriteria(criteria);
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for (Product product : products) {
            beans.add(new ProductBean(product));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public ProductBean findById(final Long id) {
        Product product = productDao.findById(id);
        ProductBean bean = new ProductBean(product);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final ProductBean bean) {
        Product product = new Product(bean);
        productDao.update(product);
    }

}
