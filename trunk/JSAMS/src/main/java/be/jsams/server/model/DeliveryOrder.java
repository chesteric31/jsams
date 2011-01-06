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

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6319674447875624133L;
    private Date creationDate;
    private String remark;
    private BigDecimal discountRate;

    private Customer customer;
    private Address deliveryAddress;

    private List<DeliveryOrderDetail> details;

    public DeliveryOrder() {
        super();
    }

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Column(name = "DISCOUNT_RATE")
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CUSTOMER")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_DELIVERY")
    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    @OneToMany(mappedBy = "deliveryOrder")
    public List<DeliveryOrderDetail> getDetails() {
        return details;
    }

    public void setDetails(List<DeliveryOrderDetail> details) {
        this.details = details;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "DeliveryOrder [creationDate=" + creationDate + ", customer=" + customer + ", deliveryAddress="
                + deliveryAddress + ", discountRate=" + discountRate + ", remark=" + remark + "]";
    }

}
