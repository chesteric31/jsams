package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Estimate detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ESTIMATE_DETAIL")
public class EstimateDetail extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -389048976022120236L;
    private int quantity;
    private BigDecimal price;
    private BigDecimal vatApplicable;
    private BigDecimal discountRate;
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
     * 
     * @return the quantity
     */
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return the price
     */
    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *            the price to set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * 
     * @return the VAT applicable
     */
    @Column(name = "VAT_APPLICABLE")
    public BigDecimal getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable
     *            the VAT applicable to set
     */
    public void setVatApplicable(BigDecimal vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return the discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate
     *            the discount rate to set
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return the {@link Estimate}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ESTIMATE")
    public Estimate getEstimate() {
        return estimate;
    }

    /**
     * 
     * @param estimate
     *            the {@link Estimate} to set
     */
    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    /**
     * 
     * @return the {@link Product}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PRODUCT")
    public Product getProduct() {
        return product;
    }

    /**
     * 
     * @param product
     *            the {@link Product} to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * 
     * @return a boolean to indicate if the {@link EstimateDetail} is transferred to an other document
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred
     *            true if the {@link EstimateDetail} is transferred to an other document, false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    @Override
    public String toString() {
        return "EstimateDetail [discountRate=" + discountRate + ", estimate=" + estimate + ", price=" + price
                + ", product=" + product + ", quantity=" + quantity + ", transferred=" + transferred
                + ", vatApplicable=" + vatApplicable + "]";
    }

}
