package be.jsams.common.bean.model.management;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AbstractTranslatableIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.management.ProductCategoryBeanView;
import be.jsams.server.model.management.ProductCategory;

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
     * Default constructor.
     */
    public ProductCategoryBean() {
        super();
        setView(buildView());
    }
    
    /**
     * Constructor.
     * 
     * @param model the {@link ProductCategory}
     * @param society the current {@link SocietyBean} used
     */
    public ProductCategoryBean(ProductCategory model, SocietyBean society) {
        super(model);
        this.society = society;
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     * @param society the current {@link SocietyBean} used
     */
    public ProductCategoryBean(ObservableList<ProductCategoryBean> list, SocietyBean society) {
        super();
        setListModel(list);
        if (!list.isEmpty()) {
            setSelection(list.get(0));
        }
        this.society = society;
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link ProductCategory} object
     * @param society the current {@link SocietyBean} used
     */
    public ProductCategoryBean(ObservableList<ProductCategoryBean> list, ProductCategory model, SocietyBean society) {
        this(model, society);
        setListModel(list);
        setSelection(this);
        setView(buildView());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductCategoryBeanView buildView() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (society == null) {
            result += 0;
        } else {
            result += society.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof ProductCategoryBean)) {
            return false;
        }
        ProductCategoryBean other = (ProductCategoryBean) obj;
        if (society == null) {
            if (other.society != null) {
                return false;
            }
        } else if (!society.equals(other.society)) {
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        ProductCategoryBean other = (ProductCategoryBean) bean;
        setSociety(other.getSociety());
    }

}
