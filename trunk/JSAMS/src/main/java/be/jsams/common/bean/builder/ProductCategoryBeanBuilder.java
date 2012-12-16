package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.management.ProductCategory;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link ProductCategoryBean} from DAO list model and
 * entity model.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryBeanBuilder {

    private ProductCategory model;

    private ObservableList<ProductCategoryBean> listModel;

    private ProductCategoryDao dao;

    private boolean canBeNull;

    private SocietyBean society;

    /**
     * Constructor.
     */
    public ProductCategoryBeanBuilder() {
    }

    /**
     * Builds the {@link ProductCategoryBean}.
     * 
     * @return the built {@link ProductCategoryBean}
     */
    public ProductCategoryBean build() {
        ProductCategoryBean bean = null;
        List<ProductCategory> categories = dao.findAll(society.getId());
        listModel = new ArrayListModel<ProductCategoryBean>();
        for (ProductCategory category : categories) {
            listModel.add(new ProductCategoryBean(category, society));
        }
        if (canBeNull) {
            listModel.add(0, null);
        }
        if (model == null) {
            bean = new ProductCategoryBean(listModel, society);
        } else {
            bean = new ProductCategoryBean(listModel, model, society);
        }
        return bean;
    }

    /**
     * @return the model
     */
    public ProductCategory getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(ProductCategory model) {
        this.model = model;
    }

    /**
     * @return the listModel
     */
    public ObservableList<ProductCategoryBean> getListModel() {
        return listModel;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<ProductCategoryBean> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the dao
     */
    public ProductCategoryDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(ProductCategoryDao dao) {
        this.dao = dao;
    }

    /**
     * @return the canBeNull
     */
    public boolean isCanBeNull() {
        return canBeNull;
    }

    /**
     * @param canBeNull the canBeNull to set
     */
    public void setCanBeNull(boolean canBeNull) {
        this.canBeNull = canBeNull;
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
