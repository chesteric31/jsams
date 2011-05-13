package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.dao.management.impl.ProductDaoImpl;
import be.jsams.server.model.management.Product;
import be.jsams.server.service.management.ProductService;

/**
 * Product service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao dao;
    private ProductBeanBuilder builder;

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
        builder.setModel(product);
        ProductBean productBean = builder.build(false, false);
        return productBean;
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
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        ((ProductDaoImpl) dao).setCurrentSociety(society);
        List<Product> products = dao.findAll();
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for (Product product : products) {
            builder.setModel(product);
            builder.setSociety(society);
            ProductBean productBean = builder.build(false, false);
            beans.add(productBean);
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductBean> findByCriteria(final ProductBean criteria) {
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        ((ProductDaoImpl) dao).setCurrentSociety(society);
        List<Product> products = dao.findByCriteria(criteria);
        List<ProductBean> beans = new ArrayList<ProductBean>();
        for (Product product : products) {
            builder.setModel(product);
            builder.setSociety(society);
            ProductBean productBean = builder.build(false, false);
            beans.add(productBean);
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public ProductBean findById(final Long id) {
        Product product = dao.findById(id);
        builder.setModel(product);
        ProductBean productBean = builder.build(false, false);
        return productBean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final ProductBean bean) {
        Product product = new Product(bean);
        dao.update(product);
    }

    /**
     * @return the builder
     */
    public ProductBeanBuilder getBuilder() {
        return builder;
    }

    /**
     * @param builder the builder to set
     */
    public void setBuilder(ProductBeanBuilder builder) {
        this.builder = builder;
    }

    /**
     * @return the dao
     */
    public ProductDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ProductDao dao) {
        this.dao = dao;
    }

}
