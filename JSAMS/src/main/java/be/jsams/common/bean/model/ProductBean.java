package be.jsams.common.bean.model;

import be.jsams.common.bean.view.ProductBeanView;
import be.jsams.server.model.Product;

import com.jgoodies.common.collect.ArrayListModel;

/**
 * 
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
        setCategory(new ProductCategoryBean());
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Product}
     */
    public ProductBean(Product model) {
        super(model);
        setCategory(new ProductCategoryBean(model.getCategory()));
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
