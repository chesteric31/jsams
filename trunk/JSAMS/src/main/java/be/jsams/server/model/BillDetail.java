package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Bill detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "BILL_DETAIL")
public class BillDetail extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5662462711250331221L;
    private int quantity;
    private BigDecimal price;
    private String description;
    private BigDecimal vatApplicable;
    private BigDecimal discountRate;
    private boolean transferred;

    private Bill bill;
    private Product product;

    public BillDetail() {
        super();
    }

    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "VAT_APPLICABLE")
    public BigDecimal getVatApplicable() {
        return vatApplicable;
    }

    public void setVatApplicable(BigDecimal vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    @Column(name = "DISCOUNT_RATE")
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_BILL")
    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PRODUCT")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "BillDetail [bill=" + bill + ", description=" + description + ", discountRate=" + discountRate
                + ", price=" + price + ", product=" + product + ", quantity=" + quantity + ", transferred="
                + transferred + ", vatApplicable=" + vatApplicable + "]";
    }

}
