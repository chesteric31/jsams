package be.jsams.common.bean.model.management;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.builder.ProductCategoryBeanBuilder;
import be.jsams.common.bean.model.AbstractNamedIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.management.ProductBeanView;
import be.jsams.server.dao.management.ProductCategoryDao;
import be.jsams.server.model.management.Product;
import be.jsams.server.model.management.ProductCategory;

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

    private ProductCategoryBeanBuilder productCategoryBuilder;

    public static final String PRICE_PROPERTY = "price";
    public static final String QUANTITY_STOCK_PROPERTY = "quantityStock";
    public static final String REORDER_LEVEL_PROPERTY = "reorderLevel";
    public static final String VAT_APPLICABLE_PROPERTY = "vatApplicable";

    /**
     * Default constructor
     * 
     * @param categoryCanBeNull to specify if the category can be null in the
     *            combo box
     * @param society the current {@link SocietyBean}
     */
    public ProductBean(boolean categoryCanBeNull, SocietyBean society) {
        super();
        productCategoryBuilder = new ProductCategoryBeanBuilder(categoryCanBeNull, society);
        productCategoryBuilder.setDao(JsamsApplicationContext.getProductCategoryDao());
        ProductCategoryBean categoryBean = productCategoryBuilder.build();
        this.category = categoryBean;
    }

    /**
     * Constructor
     * 
     * @param model the {@link Product}
     * @param society the current {@link SocietyBean}
     */
    public ProductBean(Product model, SocietyBean society) {
        super(model);
        ProductCategory productCategory = model.getCategory();
        productCategoryBuilder = new ProductCategoryBeanBuilder(false, society);
        productCategoryBuilder.setModel(productCategory);
        productCategoryBuilder.setDao(getProductCategoryDao());
        productCategoryBuilder.setSociety(society);
        ProductCategoryBean categoryBean = productCategoryBuilder.build();
        this.category = categoryBean;

        this.price = model.getPrice();
        this.quantityStock = model.getQuantityStock();
        this.reorderLevel = model.getReorderLevel();
        this.vatApplicable = model.getVatApplicable();
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
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
     * @param quantityStock the quantityStock to set
     */
    public void setQuantityStock(int quantityStock) {
        int oldValue = this.quantityStock;
        this.quantityStock = quantityStock;
        firePropertyChange(QUANTITY_STOCK_PROPERTY, oldValue, this.quantityStock);
    }

    /**
     * @return the reorderLevel
     */
    public int getReorderLevel() {
        return reorderLevel;
    }

    /**
     * @param reorderLevel the reorderLevel to set
     */
    public void setReorderLevel(int reorderLevel) {
        int oldValue = this.reorderLevel;
        this.reorderLevel = reorderLevel;
        firePropertyChange(REORDER_LEVEL_PROPERTY, oldValue, this.reorderLevel);
    }

    /**
     * @return the vatApplicable
     */
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * @param vatApplicable the vatApplicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        Double oldValue = this.vatApplicable;
        this.vatApplicable = vatApplicable;
        firePropertyChange(VAT_APPLICABLE_PROPERTY, oldValue, this.vatApplicable);
    }

    /**
     * @return the category
     */
    public ProductCategoryBean getCategory() {
        return category;
    }

    /**
     * @param category the category to set
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

    /**
     * 
     * @return the {@link ProductCategoryDao}
     */
    public ProductCategoryDao getProductCategoryDao() {
        return JsamsApplicationContext.getProductCategoryDao();
    }

}
