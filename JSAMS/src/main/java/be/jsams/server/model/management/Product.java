package be.jsams.server.model.management;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.model.AbstractNamedIdentity;

/**
 * Product entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractNamedIdentity {

    private Double price;
    private int quantityStock;
    private int reorderLevel;
    private Double vatApplicable;

    private ProductCategory category;

    /**
     * Constructor.
     */
    public Product() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link ProductBean}
     */
    public Product(ProductBean bean) {
        super(bean);
        if (bean.getCategory().getId() != null) {
            setCategory(new ProductCategory(bean.getCategory()));
        }
        setPrice(bean.getPrice());
        setQuantityStock(bean.getQuantityStock());
        setReorderLevel(bean.getReorderLevel());
        setVatApplicable(bean.getVatApplicable());
    }

    /**
     * 
     * @return the price
     */
    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *            the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * @return the stock quantity
     */
    @Column(name = "QUANTITY_STOCK")
    public int getQuantityStock() {
        return quantityStock;
    }

    /**
     * 
     * @param quantityStock
     *            the stock quantity to set
     */
    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    /**
     * 
     * @return the reorder level
     */
    @Column(name = "REORDER_LEVEL")
    public int getReorderLevel() {
        return reorderLevel;
    }

    /**
     * 
     * @param reorderLevel
     *            the reorder level to set
     */
    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    /**
     * 
     * @return the VAT applicable
     */
    @Column(name = "VAT_APPLICABLE")
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable
     *            the VAT applicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return the {@link ProductCategory}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_CATEGORY_PRODUCT")
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * 
     * @param category
     *            the {@link ProductCategory} to set
     */
    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product [category=");
        builder.append(category);
        builder.append(", price=");
        builder.append(price);
        builder.append(", quantityStock=");
        builder.append(quantityStock);
        builder.append(", reorderLevel=");
        builder.append(reorderLevel);
        builder.append(", vatApplicable=");
        builder.append(vatApplicable);
        builder.append("]");
        return builder.toString();
    }

}
