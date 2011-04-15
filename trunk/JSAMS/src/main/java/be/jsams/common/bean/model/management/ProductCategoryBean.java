package be.jsams.common.bean.model.management;

import java.util.List;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.model.AbstractTranslatableIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.ProductCategoryBeanView;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.common.collect.ArrayListModel;

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

    // Remark, that must be non static list, to avoid to share this list for
    // everyone
    private ArrayListModel<ProductCategoryBean> list = new ArrayListModel<ProductCategoryBean>();

    private SocietyBean society;

    /**
     * Default constructor
     */
    public ProductCategoryBean() {
        super();
        initList();
    }

    /**
     * Initializes the ObservableList and the eventually the selection
     * element.
     */
    private void initList() {
        List<ProductCategoryBean> beans = JsamsApplicationContext.getProductCategoryService().findAll();
        list.clear();
        list.addAll(beans);
        setListModel(list);
        if (!list.isEmpty()) {
            setSelection(list.get(0));
        }
    }

    /**
     * Constructor.
     * 
     * @param model the {@link ProductCategory}
     */
    public ProductCategoryBean(ProductCategory model) {
        super(model);
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
