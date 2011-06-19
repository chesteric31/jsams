package be.jsams.common.bean.builder;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.dao.management.ProductDao;
import be.jsams.server.model.management.Product;

/**
 * Builder that makes a {@link ProductBean} from DAO list model and entity
 * model.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductBeanBuilder {

    private Product model;

    private ProductDao dao;

    private SocietyBean society;

    /**
     * Build the {@link ProductBean}.
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
        } else {
            bean = new ProductBean(model, society);
        }
        return bean;
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

}
