package be.jsams.server.model.sale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.BillDetailBean;
import be.jsams.common.bean.model.sale.CommandDetailBean;
import be.jsams.common.bean.model.sale.DeliveryOrderDetailBean;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.model.management.Product;

/**
 * Delivery order detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "DELIVERY_ORDER_DETAIL")
public class DeliveryOrderDetail extends AbstractIdentity {

    private int quantity;
    private Double vatApplicable;
    private Double discountRate;
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
     * Constructor.
     * 
     * @param bean the {@link DeliveryOrderDetailBean}
     * @param deliveryOrder the {@link DeliveryOrder} model
     */
    public DeliveryOrderDetail(final DeliveryOrderDetailBean bean, final DeliveryOrder deliveryOrder) {
        super(bean);
        setDiscountRate(bean.getDiscountRate());
        setDeliveryOrder(deliveryOrder);
        setProduct(new Product(bean.getProduct()));
        setQuantity(bean.getQuantity());
        setTransferred(bean.isTransferred());
        setVatApplicable(bean.getVatApplicable());
        CommandDetailBean commandDetail = bean.getCommandDetail();
        if (commandDetail != null) {
            setCommandDetail(new CommandDetail(commandDetail, new Command(commandDetail.getCommand())));
        }
        BillDetailBean billDetail = bean.getBillDetail();
        if (billDetail != null) {
            setBillDetail(new BillDetail(billDetail, new Bill(billDetail.getBill())));
        }
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
     * @return a boolean to indicate is the {@link DeliveryOrderDetail} is transferred to bill detail
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred a boolean to indicate is the {@link DeliveryOrderDetail} is transferred to bill detail
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * 
     * @return the {@link DeliveryOrder}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_DELIVERY_ORDER")
    public DeliveryOrder getDeliveryOrder() {
        return deliveryOrder;
    }

    /**
     * 
     * @param deliveryOrder the {@link DeliveryOrder} to set
     */
    public void setDeliveryOrder(DeliveryOrder deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
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
     * @return the {@link CommandDetail}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_COMMAND_DETAIL")
    public CommandDetail getCommandDetail() {
        return commandDetail;
    }

    /**
     * 
     * @param commandDetail the {@link CommandDetail} to set
     */
    public void setCommandDetail(CommandDetail commandDetail) {
        this.commandDetail = commandDetail;
    }

    /**
     * 
     * @return the {@link BillDetail}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_BILL_DETAIL")
    public BillDetail getBillDetail() {
        return billDetail;
    }

    /**
     * 
     * @param billDetail the {@link BillDetail} to set
     */
    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("DeliveryOrderDetail [billDetail=");
        builder.append(billDetail);
        builder.append(", commandDetail=");
        builder.append(commandDetail);
        builder.append(", deliveryOrder=");
        builder.append(deliveryOrder);
        builder.append(", discountRate=");
        builder.append(discountRate);
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
