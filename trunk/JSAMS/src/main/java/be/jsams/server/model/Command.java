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
 * Command entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "COMMAND")
public class Command extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8102219472482522357L;
    private Date creationDate;
    private String remark;
    private BigDecimal discountRate;

    private Agent agent;
    private Customer customer;
    private Address billingAddress;
    private Address deliveryAddress;

    private List<CommandDetail> details;

    /**
     * Constructor.
     */
    public Command() {
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
     * @return a discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate
     *            a discount rate to set
     */
    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return the {@link Agent}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_AGENT")
    public Agent getAgent() {
        return agent;
    }

    /**
     * 
     * @param agent
     *            the {@link Agent} to set
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * 
     * @return the {@link Customer}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
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
     * @return the billing {@link Address}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * 
     * @param billingAddress
     *            the billing {@link Address} to set
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
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
     * @return a list of {@link CommandDetail}
     */
    @OneToMany(mappedBy = "command")
    public List<CommandDetail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *            a list of {@link CommandDetail} to set
     */
    public void setDetails(List<CommandDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Command [agent=" + agent + ", billingAddress=" + billingAddress + ", creationDate=" + creationDate
                + ", customer=" + customer + ", deliveryAddress=" + deliveryAddress + ", details=" + details
                + ", discountRate=" + discountRate + ", remark=" + remark + "]";
    }

}
