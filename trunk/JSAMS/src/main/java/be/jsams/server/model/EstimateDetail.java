package be.jsams.server.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.EstimateDetailBean;

/**
 * Estimate detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ESTIMATE_DETAIL")
public class EstimateDetail extends AbstractIdentity {

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;
    private boolean transferred;

    private Estimate estimate;
    private Product product;

    /**
     * Constructor.
     */
    public EstimateDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link EstimateDetailBean}
     * @param estimate the {@link Estimate} model
     */
    public EstimateDetail(final EstimateDetailBean bean, final Estimate estimate) {
        super(bean);
        setDiscountRate(bean.getDiscountRate());
        setEstimate(estimate);
        setPrice(bean.getPrice());
        setProduct(new Product(bean.getProduct()));
        setQuantity(bean.getQuantity());
        setTransferred(bean.isTransferred());
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
     * @return the VAT applicable
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
     * @return the discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate the discount rate to set
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return the {@link Estimate}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_ESTIMATE")
    public Estimate getEstimate() {
        return estimate;
    }

    /**
     * 
     * @param estimate the {@link Estimate} to set
     */
    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
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
     * 
     * @return a boolean to indicate if the {@link EstimateDetail} is
     *         transferred to an other document
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred true if the {@link EstimateDetail} is transferred to
     *            an other document, false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("EstimateDetail [discountRate=");
        builder.append(discountRate);
        builder.append(", estimate=");
        builder.append(estimate);
        builder.append(", price=");
        builder.append(price);
        builder.append(", product=");
        builder.append(product);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append(", transferred=");
        builder.append(transferred);
        builder.append(", vatApplicable=");
        builder.append(vatApplicable);
        builder.append("]");
        return builder.toString();
    }

}
