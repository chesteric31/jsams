package be.jsams.common.bean.model.management;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.AbstractNamedIdentityBean;
import be.jsams.common.bean.view.ProductBeanView;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.common.collect.ArrayListModel;

/**
 * Bean model for {@link Product} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductBean extends AbstractNamedIdentityBean<Product, ProductBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6336930492600484120L;

    private Double price;
    private int quantityStock;
    private int reorderLevel;
    private Double vatApplicable;

    private ProductCategoryBean category;

    public static final String PRICE_PROPERTY = "price";
    public static final String QUANTITYSTOCK_PROPERTY = "quantityStock";
    public static final String REORDERLEVEL_PROPERTY = "reorderLevel";
    public static final String VATAPPLICABLE_PROPERTY = "vatApplicable";

    private static ArrayListModel<ProductBean> list = new ArrayListModel<ProductBean>();

    /**
     * Default constructor
     */
    public ProductBean() {
        super();
        ProductCategoryBean categoryBean = new ProductCategoryBean();
        categoryBean.setSociety(JsamsDesktop.getInstance().getCurrentSociety());
        setCategory(categoryBean);
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Product}
     */
    public ProductBean(Product model) {
        super(model);
        ProductCategory productCategory = model.getCategory();
        ProductCategoryBean categoryBean = new ProductCategoryBean(productCategory);
        categoryBean.setSociety(new SocietyBean(productCategory.getSociety()));
        setCategory(categoryBean);
        setPrice(model.getPrice());
        setQuantityStock(model.getQuantityStock());
        setReorderLevel(model.getReorderLevel());
        setVatApplicable(model.getVatApplicable());
        setListModel(list);
        setSelection(this);
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(Double price) {
        Double oldValue = this.price;
        this.price = price;
        firePropertyChange(PRICE_PROPERTY, oldValue, this.price);
    }

    /**
     * @return the quantityStock
     */
    public int getQuantityStock() {
        return quantityStock;
    }

    /**
     * @param quantityStock
     *            the quantityStock to set
     */
    public void setQuantityStock(int quantityStock) {
        int oldValue = this.quantityStock;
        this.quantityStock = quantityStock;
        firePropertyChange(QUANTITYSTOCK_PROPERTY, oldValue, this.quantityStock);
    }

    /**
     * @return the reorderLevel
     */
    public int getReorderLevel() {
        return reorderLevel;
    }

    /**
     * @param reorderLevel
     *            the reorderLevel to set
     */
    public void setReorderLevel(int reorderLevel) {
        int oldValue = this.reorderLevel;
        this.reorderLevel = reorderLevel;
        firePropertyChange(REORDERLEVEL_PROPERTY, oldValue, this.reorderLevel);
    }

    /**
     * @return the vatApplicable
     */
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * @param vatApplicable
     *            the vatApplicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        Double oldValue = this.vatApplicable;
        this.vatApplicable = vatApplicable;
        firePropertyChange(VATAPPLICABLE_PROPERTY, oldValue, this.vatApplicable);
    }

    /**
     * @return the category
     */
    public ProductCategoryBean getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(ProductCategoryBean category) {
        this.category = category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProductBeanView getView() {
        return new ProductBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "ProductBean [category=" + category + ", price=" + price + ", quantityStock=" + quantityStock
                + ", reorderLevel=" + reorderLevel + ", vatApplicable=" + vatApplicable + "]";
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        super.clear();
        category.clear();
        setPrice(null);
        setQuantityStock(0);
        setReorderLevel(0);
        setVatApplicable(null);
    }

}
