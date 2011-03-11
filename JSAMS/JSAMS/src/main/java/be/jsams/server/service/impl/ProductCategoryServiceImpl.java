package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.ProductCategoryBean;
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

    /**
     * 
     * @return the {@link ProductCategoryDao}
     */
    public ProductCategoryDao getProductCategoryDao() {
        return productCategoryDao;
    }

    /**
     * 
     * @param productCategoryDao the {@link ProductCategoryDao} to set
     */
    public void setProductCategoryDao(ProductCategoryDao productCategoryDao) {
        this.productCategoryDao = productCategoryDao;
    }

    /**
     * {@inheritDoc}
     */
    public ProductCategoryBean create(final ProductCategoryBean bean) {
        ProductCategory category = new ProductCategory(bean);
        productCategoryDao.add(category);
        return new ProductCategoryBean(category);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final ProductCategoryBean bean) {
        ProductCategory category = new ProductCategory(bean);
        productCategoryDao.delete(category);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        productCategoryDao.delete(id);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductCategoryBean> findAll() {
        List<ProductCategory> categories = productCategoryDao.findAll();
        List<ProductCategoryBean> beans = new ArrayList<ProductCategoryBean>();
        for (ProductCategory category : categories) {
            beans.add(new ProductCategoryBean(category));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public ProductCategoryBean findById(final Long id) {
        ProductCategory category = productCategoryDao.findById(id);
        ProductCategoryBean bean = new ProductCategoryBean(category);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final ProductCategoryBean bean) {
        ProductCategory category = new ProductCategory(bean);
        productCategoryDao.update(category);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductCategoryBean> findByCriteria(final ProductCategoryBean criteria) {
        List<ProductCategory> categories = productCategoryDao.findByCriteria(criteria);
        List<ProductCategoryBean> beans = new ArrayList<ProductCategoryBean>();
        for (ProductCategory category : categories) {
            beans.add(new ProductCategoryBean(category));
        }
        return beans;
    }

}
