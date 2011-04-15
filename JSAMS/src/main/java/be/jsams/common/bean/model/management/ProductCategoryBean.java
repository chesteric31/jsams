package be.jsams.common.bean.model.management;

import be.jsams.common.bean.model.AbstractTranslatableIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.ProductCategoryBeanView;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.common.collect.ObservableList;

/**
 * Bean model for {@link ProductCategory} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductCategoryBean extends AbstractTranslatableIdentityBean<ProductCategory, ProductCategoryBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6394980207406343489L;
    
    private SocietyBean society;

    /**
     * Constructor.
     * 
     * @param model the {@link ProductCategory}
     */
    public ProductCategoryBean(ProductCategory model) {
        super(model);
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     */
    public ProductCategoryBean(ObservableList<ProductCategoryBean> list) {
        super();
        setListModel(list);
        if (!list.isEmpty()) {
            setSelection(list.get(0));
        }
    }

    /**
     * Constructor
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link ProductCategory} object
     */
    public ProductCategoryBean(ObservableList<ProductCategoryBean> list, ProductCategory model) {
        this(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryBeanView getView() {
        return new ProductCategoryBeanView(this);
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
