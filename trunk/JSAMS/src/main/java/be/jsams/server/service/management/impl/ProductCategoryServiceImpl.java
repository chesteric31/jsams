package be.jsams.server.service.management.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.desktop.Desktop;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.management.ProductCategory;
import be.jsams.server.service.management.ProductCategoryService;

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
     * @param productCategoryDao
     *            the {@link ProductCategoryDao} to set
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
        return new ProductCategoryBean(category, bean.getSociety());
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
    public List<ProductCategoryBean> findAll(SocietyBean currentSociety) {
        productCategoryDao.setCurrentSociety(currentSociety);
        List<ProductCategory> categories = productCategoryDao.findAll();
        List<ProductCategoryBean> beans = new ArrayList<ProductCategoryBean>();
        for (ProductCategory category : categories) {
            beans.add(new ProductCategoryBean(category, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public ProductCategoryBean findById(final Long id) {
        ProductCategory category = productCategoryDao.findById(id);
        return new ProductCategoryBean(category, Desktop.getInstance().getCurrentSociety());
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
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        productCategoryDao.setCurrentSociety(currentSociety);
        List<ProductCategory> categories = productCategoryDao.findByCriteria(criteria);
        List<ProductCategoryBean> beans = new ArrayList<ProductCategoryBean>();
        for (ProductCategory category : categories) {
            beans.add(new ProductCategoryBean(category, currentSociety));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ProductCategoryBean> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
