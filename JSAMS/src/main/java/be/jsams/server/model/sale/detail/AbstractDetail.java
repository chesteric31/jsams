package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import be.jsams.common.bean.model.sale.detail.AbstractDetailBean;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.model.management.Product;

/**
 * {@link MappedSuperclass} abstract class to abstract all the common fields between the document detail:
 * estimate detail, command detail, delivery order detail, bill detail and credit note detail.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@MappedSuperclass
public abstract class AbstractDetail extends AbstractIdentity {

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;

    private Product product;

    /**
     * Constructor.
     */
    public AbstractDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractDetailBean} to use
     */
    public AbstractDetail(final AbstractDetailBean<?, ?, ?> bean) {
        super(bean);
        setDiscountRate(bean.getDiscountRate());
        setPrice(bean.getPrice());
        setProduct(new Product(bean.getProduct()));
        setQuantity(bean.getQuantity());
        setVatApplicable(bean.getVatApplicable());
    }


    /**
     * 
     * @return the quantity
     */
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * @return the VAT to apply
     */
    @Column(name = "VAT_APPLICABLE")
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable the VAT applicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return a discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate a discount rate to set
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return the {@link Product}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PRODUCT")
    public Product getProduct() {
        return product;
    }

    /**
     * 
     * @param product the {@link Product} to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractDetail [discountRate=");
        builder.append(discountRate);
        builder.append(", price=");
        builder.append(price);
        builder.append(", product=");
        builder.append(product);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", vatApplicable=");
        builder.append(vatApplicable);
        builder.append("]");
        return builder.toString();
    }
    
}
