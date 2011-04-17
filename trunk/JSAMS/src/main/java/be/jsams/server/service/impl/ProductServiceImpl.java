package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.dao.ProductDao;
import be.jsams.server.dao.impl.ProductDaoImpl;
import be.jsams.server.model.Product;
import be.jsams.server.service.ProductService;

/**
 * Product service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao dao;

    /**
     * 
     * @return the {@link ProductDao}
     */
    public ProductDao getProductDao() {
        return dao;
    }

    /**
     * 
     * @param productDao
     *            the {@link ProductDao} to set
     */
    public void setProductDao(ProductDao productDao) {
        this.dao = productDao;
    }

    /**
     * {@inheritDoc}
     */
    public ProductBean create(final ProductBean bean) {
        Product product = new Product(bean);
        dao.add(product);
        return new ProductBean(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final ProductBean bean) {
        Product product = new Product(bean);
        dao.delete(product);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        dao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductBean> findAll() {
        ((ProductDaoImpl) dao).setCurrentSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<Product> products = dao.findAll();
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
        ((ProductDaoImpl) dao).setCurrentSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<Product> products = dao.findByCriteria(criteria);
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
        Product product = dao.findById(id);
        ProductBean bean = new ProductBean(product);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final ProductBean bean) {
        Product product = new Product(bean);
        dao.update(product);
    }

}
