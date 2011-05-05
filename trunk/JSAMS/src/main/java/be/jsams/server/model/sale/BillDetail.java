package be.jsams.server.model.sale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.BillDetailBean;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.model.management.Product;

/**
 * Bill detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "BILL_DETAIL")
public class BillDetail extends AbstractIdentity {

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;
    private boolean transferred;

    private Bill bill;
    private Product product;

    /**
     * Constructor.
     */
    public BillDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link BillDetailBean}
     * @param bill the {@link Bill} model
     */
    public BillDetail(BillDetailBean bean, final Bill bill) {
        super(bean);
        setDiscountRate(bean.getDiscountRate());
        setBill(bill);
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
     * @return true if the {@link BillDetail} was transferred to a credit note detail, false otherwise
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred the transferred boolean to set (true, this {@link BillDetail} is transferred to a credit note
     *            detail, false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link Bill}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_BILL")
    public Bill getBill() {
        return bill;
    }

    /**
     * 
     * @param bill the {@link Bill} to set
     */
    public void setBill(Bill bill) {
        this.bill = bill;
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
        builder.append("BillDetail [bill=");
        builder.append(bill);
        builder.append(", discountRate=");
        builder.append(discountRate);
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
