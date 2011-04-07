package be.jsams.server.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Delivery order entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "DELIVERY_ORDER")
public class DeliveryOrder extends AbstractIdentity {

    private Date creationDate;
    private String remark;
    private BigDecimal discountRate;
    private boolean transferred;

    private Customer customer;
    private Address deliveryAddress;

    private List<DeliveryOrderDetail> details;

    /**
     * Constructor.
     */
    public DeliveryOrder() {
        super();
    }

    /**
     * 
     * @return the creation date
     */
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *            the creation date to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return a remark
     */
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark
     *            a remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
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
     * @return the {@link Customer}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CUSTOMER")
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 
     * @param customer
     *            the {@link Customer} to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * 
     * @return the delivery {@link Address}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_DELIVERY")
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * 
     * @param deliveryAddress
     *            the delivery {@link Address} to set
     */
    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * 
     * @return a list of {@link DeliveryOrderDetail}
     */
    @OneToMany(mappedBy = "deliveryOrder")
    public List<DeliveryOrderDetail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *            a list of {@link DeliveryOrderDetail} to set
     */
    public void setDetails(List<DeliveryOrderDetail> details) {
        this.details = details;
    }

    /**
     * @return the transferred
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred
     *            the transferred to set
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
        builder.append("DeliveryOrder [creationDate=");
        builder.append(creationDate);
        builder.append(", customer=");
        builder.append(customer);
        builder.append(", deliveryAddress=");
        builder.append(deliveryAddress);
        builder.append(", details=");
        builder.append(details);
        builder.append(", discountRate=");
        builder.append(discountRate);
        builder.append(", remark=");
        builder.append(remark);
        builder.append(", transferred=");
        builder.append(transferred);
        builder.append("]");
        return builder.toString();
    }

}
