package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.dao.ProductCategoryDao;
import be.jsams.server.dao.impl.ProductCategoryDaoImpl;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link ProductCategoryBean} from DAO list model and entity model.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryBeanBuilder {

    private ProductCategory model;

    private ObservableList<ProductCategoryBean> listModel;

    private ProductCategoryDao dao;

    /**
     * Build the {@link ProductCategoryBean}.
     * 
     * @param categoryCanBeNull
     *            to specify if the category can be null in the combo box
     * @return the built {@link ProductCategoryBean}
     */
    public ProductCategoryBean build(boolean categoryCanBeNull) {
        ((ProductCategoryDaoImpl) dao).setCurrentSociety(JsamsDesktop.getInstance().getCurrentSociety());
        List<ProductCategory> categories = dao.findAll();
        listModel = new ArrayListModel<ProductCategoryBean>();
        for (ProductCategory category : categories) {
            listModel.add(new ProductCategoryBean(category));
        }
        if (categoryCanBeNull) {
            listModel.add(0, null);
        }
        if (model != null) {
            return new ProductCategoryBean(listModel, model);
        } else {
            return new ProductCategoryBean(listModel);
        }
    }

    /**
     * @return the model
     */
    public ProductCategory getModel() {
        return model;
    }

    /**
     * @param model
     *            the model to set
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
     * @param listModel
     *            the listModel to set
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
     * @param dao
     *            the dao to set
     */
    public void setDao(ProductCategoryDao dao) {
        this.dao = dao;
    }

}
