package be.jsams.common.bean.builder;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;

/**
 * Builder that builds a {@link ProductBean} from DAO list model and entity
 * model.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductBeanBuilder {

    private Product model;

    private ProductDao dao;

    private SocietyBean society;

    private ProductCategoryBeanBuilder productCategoryBeanBuilder;

    /**
     * Builds the {@link ProductBean}.
     * 
     * @param newOne to create a new one {@link ProductBean} from scratch
     * @param categoryCanBeNull indicates if the category can be null
     * 
     * @return the built {@link ProductBean}
     */
    public ProductBean build(boolean newOne, boolean categoryCanBeNull) {
        ProductBean bean = null;
        if (newOne) {
            bean = new ProductBean(categoryCanBeNull, society);
            bean.setCategory(buildCategory(categoryCanBeNull, society, null));
        } else {
            bean = new ProductBean(model, society);
            ProductCategory productCategory = model.getCategory();
            bean.setCategory(buildCategory(false, society, productCategory));
        }
        return bean;
    }

    /**
     * Builds a {@link ProductCategoryBean}.
     * 
     * @param categoryCanBeNull to specify if the category can be null in the
     *            combo box
     * @param society the {@link SocietyBean} to use
     * @param productCategory the {@link ProductCategory} to use
     * @return the built {@link ProductCategoryBean}
     */
    private ProductCategoryBean buildCategory(boolean categoryCanBeNull, SocietyBean society,
            ProductCategory productCategory) {
        productCategoryBeanBuilder.setModel(productCategory);
        productCategoryBeanBuilder.setSociety(society);
        return productCategoryBeanBuilder.build();
    }

    /**
     * @return the model
     */
    public Product getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Product model) {
        this.model = model;
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

    /**
     * @return the society
     */
    public SocietyBean getSociety() {
        return society;
    }

    /**
     * @param society the society to set
     */
    public void setSociety(SocietyBean society) {
        this.society = society;
    }

    /**
     * @return the productCategoryBeanBuilder
     */
    public ProductCategoryBeanBuilder getProductCategoryBeanBuilder() {
        return productCategoryBeanBuilder;
    }

    /**
     * @param productCategoryBeanBuilder the productCategoryBeanBuilder to set
     */
    public void setProductCategoryBeanBuilder(ProductCategoryBeanBuilder productCategoryBeanBuilder) {
        this.productCategoryBeanBuilder = productCategoryBeanBuilder;
    }

}
