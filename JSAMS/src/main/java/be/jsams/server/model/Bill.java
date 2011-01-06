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

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -613138056155460436L;
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

    public Bill() {
        super();
    }

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
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

    @Column(name = "DUE_DATE")
    @Temporal(TemporalType.DATE)
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    @Column(name = "PAID")
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Column(name = "DATE_FIRST_REMINDER")
    @Temporal(TemporalType.DATE)
    public Date getDateFirstRemember() {
        return dateFirstRemember;
    }

    public void setDateFirstRemember(Date dateFirstRemember) {
        this.dateFirstRemember = dateFirstRemember;
    }

    @Column(name = "DATE_SECOND_REMINDER")
    @Temporal(TemporalType.DATE)
    public Date getDateSecondRemember() {
        return dateSecondRemember;
    }

    public void setDateSecondRemember(Date dateSecondRemember) {
        this.dateSecondRemember = dateSecondRemember;
    }

    @Column(name = "DATE_FORMAL_NOTICE")
    @Temporal(TemporalType.DATE)
    public Date getDateFormalNotice() {
        return dateFormalNotice;
    }

    public void setDateFormalNotice(Date dateFormalNotice) {
        this.dateFormalNotice = dateFormalNotice;
    }

    @Column(name = "CLOSED")
    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_PAYMENT_MODE")
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
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
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<BillDetail> getDetails() {
        return details;
    }

    public void setDetails(List<BillDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Bill [billingAddress=" + billingAddress + ", closed=" + closed + ", creationDate=" + creationDate
                + ", customer=" + customer + ", dateFirstRemember=" + dateFirstRemember + ", dateFormalNotice="
                + dateFormalNotice + ", dateSecondRemember=" + dateSecondRemember + ", discountRate=" + discountRate
                + ", dueDate=" + dueDate + ", paid=" + paid + ", paymentMode=" + paymentMode + ", remark=" + remark
                + "]";
    }

}
