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
    public void create(final ProductCategory model) {
        productCategoryDao.add(model);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final ProductCategory model) {
        productCategoryDao.delete(model);
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
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public ProductCategory findById(final Long id) {
        return productCategoryDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final ProductCategory model) {
        productCategoryDao.update(model);
    }

    /**
     * {@inheritDoc}
     */
    public List<ProductCategory> findByCriteria(final ProductCategory criteria) {
        return productCategoryDao.findByCriteria(criteria);
    }

}
