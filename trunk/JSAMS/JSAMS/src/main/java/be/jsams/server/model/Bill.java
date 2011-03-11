package be.jsams.server.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Bill entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "BILL")
public class Bill extends AbstractIdentity {

    private Date creationDate;
    private String remark;
    private BigDecimal discountRate;
    private Date dueDate;
    private boolean paid;
    private Date dateFirstRemember;
    private Date dateSecondRemember;
    private Date dateFormalNotice;
    private boolean closed;

    private PaymentMode paymentMode;
    private Customer customer;
    private Address billingAddress;

    private List<BillDetail> details;

    /**
     * Constructor.
     */
    public Bill() {
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
     * @return the due date
     */
    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * 
     * @param dueDate
     *            the due date to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * 
     * @return true if the {@link Bill} is paid, false otherwise
     */
    @Column(name = "PAID")
    public boolean isPaid() {
        return paid;
    }

    /**
     * 
     * @param paid
     *            true if the {@link Bill} was paid, false otherwise
     */
    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    /**
     * 
     * @return the date of the first reminder
     */
    @Column(name = "DATE_FIRST_REMINDER")
    @Temporal(TemporalType.DATE)
    public Date getDateFirstRemember() {
        return dateFirstRemember;
    }

    /**
     * 
     * @param dateFirstRemember
     *            the date of the first reminder to set
     */
    public void setDateFirstRemember(Date dateFirstRemember) {
        this.dateFirstRemember = dateFirstRemember;
    }

    /**
     * 
     * @return the date of the second reminder
     */
    @Column(name = "DATE_SECOND_REMINDER")
    @Temporal(TemporalType.DATE)
    public Date getDateSecondRemember() {
        return dateSecondRemember;
    }

    /**
     * 
     * @param dateSecondRemember
     *            the date of the second reminder
     */
    public void setDateSecondRemember(Date dateSecondRemember) {
        this.dateSecondRemember = dateSecondRemember;
    }

    /**
     * 
     * @return the date of the formal notice
     */
    @Column(name = "DATE_FORMAL_NOTICE")
    @Temporal(TemporalType.DATE)
    public Date getDateFormalNotice() {
        return dateFormalNotice;
    }

    /**
     * 
     * @param dateFormalNotice
     *            the date of the formal notice to set
     */
    public void setDateFormalNotice(Date dateFormalNotice) {
        this.dateFormalNotice = dateFormalNotice;
    }

    /**
     * 
     * @return true if the {@link Bill} is closed, false otherwise
     */
    @Column(name = "CLOSED")
    public boolean isClosed() {
        return closed;
    }

    /**
     * 
     * @param closed
     *            true if the {@link Bill} was closed, false otherwise
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * 
     * @return the {@link PaymentMode}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PAYMENT_MODE")
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    /**
     * 
     * @param paymentMode
     *            the {@link PaymentMode} to set
     */
    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * 
     * @return the customer
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
     * @return a list of {@link BillDetail}
     */
    public List<BillDetail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details
     *            a list of {@link BillDetail} to set
     */
    public void setDetails(List<BillDetail> details) {
        this.details = details;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Bill [billingAddress=");
        builder.append(billingAddress);
        builder.append(", closed=");
        builder.append(closed);
        builder.append(", creationDate=");
        builder.append(creationDate);
        builder.append(", customer=");
        builder.append(customer);
        builder.append(", dateFirstRemember=");
        builder.append(dateFirstRemember);
        builder.append(", dateFormalNotice=");
        builder.append(dateFormalNotice);
        builder.append(", dateSecondRemember=");
        builder.append(dateSecondRemember);
        builder.append(", details=");
        builder.append(details);
        builder.append(", discountRate=");
        builder.append(discountRate);
        builder.append(", dueDate=");
        builder.append(dueDate);
        builder.append(", paid=");
        builder.append(paid);
        builder.append(", paymentMode=");
        builder.append(paymentMode);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }

}
