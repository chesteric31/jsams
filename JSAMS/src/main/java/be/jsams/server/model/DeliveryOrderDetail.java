package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Delivery order detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetail extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2260567879212953331L;
    private int quantity;
    private String description;
    private BigDecimal vatApplicable;
    private BigDecimal discountRate;
    private boolean transferred;

    private DeliveryOrder deliveryOrder;
    private Product product;
    private CommandDetail commandDetail;
    private BillDetail billDetail;

    /**
     * Constructor.
     */
    public DeliveryOrderDetail() {
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
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *            the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return a boolean to indicate is the {@link DeliveryOrderDetail} is transferred to bill detail
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred
     *            a boolean to indicate is the {@link DeliveryOrderDetail} is transferred to bill detail
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link DeliveryOrder}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_DELIVERY_ORDER")
    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    /**
     * 
     * @param deliveryOrder
     *            the {@link DeliveryOrder} to set
     */
    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
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
     * @return the {@link CommandDetail}
     */
    public CommandDetail getCommandDetail() {
        return commandDetail;
    }

    /**
     * 
     * @param commandDetail
     *            the {@link CommandDetail} to set
     */
    public void setCommandDetail(CommandDetail commandDetail) {
        this.commandDetail = commandDetail;
    }

    /**
     * 
     * @return the {@link BillDetail}
     */
    public BillDetail getBillDetail() {
        return billDetail;
    }

    /**
     * 
     * @param billDetail
     *            the {@link BillDetail} to set
     */
    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }

    @Override
    public String toString() {
        return "DeliveryOrderDetail [commandDetail=" + commandDetail + ", deliveryOrder=" + deliveryOrder
                + ", description=" + description + ", discountRate=" + discountRate + ", product=" + product
                + ", quantity=" + quantity + ", transferred=" + transferred + ", vatApplicable=" + vatApplicable + "]";
    }

}
