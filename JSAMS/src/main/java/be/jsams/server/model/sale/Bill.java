package be.jsams.server.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;

import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.server.model.Address;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.sale.detail.BillDetail;

/**
 * Bill entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "BILL")
public class Bill extends AbstractDocument {

    private Double discountRate;
    private Date dueDate;
    private Date paymentDate;;
    private Date firstRememberDate;
    private Date secondRememberDate;
    private Date formalNoticeDate;
    private boolean closed;

    private PaymentMode paymentMode;
    private Address billingAddress;

    private List<BillDetail> details;

    /**
     * Constructor.
     */
    public Bill() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link BillBean}
     */
    public Bill(BillBean bean) {
        super(bean);
        this.billingAddress = new Address(bean.getBillingAddress());
        this.discountRate = bean.getDiscountRate();
        this.paymentDate = bean.getPaymentDate();
        this.closed = bean.isClosed();
        this.firstRememberDate = bean.getFirstRememberDate();
        this.secondRememberDate = bean.getSecondRememberDate();
        this.formalNoticeDate = bean.getFormalNoticeDate();
        this.dueDate = bean.getDueDate();
        this.paymentMode = new PaymentMode(bean.getPaymentMode());
        List<BillDetailBean> list = bean.getDetails();
        List<BillDetail> tmp = new ArrayList<BillDetail>();
        if (list != null) {
            for (BillDetailBean detail : list) {
                tmp.add(new BillDetail(detail, this));
            }
        }
        this.details = tmp;
    }

    /**
     * @return a discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate a discount rate to set
     */
    public void setDiscountRate(Double discountRate) {
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
     * @param dueDate the due date to set
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * @return the payment date
     */
    @Column(name = "PAYMENT_DATE")
    @Temporal(TemporalType.DATE)
    public Date getPaymentDate() {
        return this.paymentDate;
    }

    /**
     * @param paymentDate the payment date
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    /**
     * @return the date of the first reminder
     */
    @Column(name = "DATE_FIRST_REMINDER")
    @Temporal(TemporalType.DATE)
    public Date getFirstRememberDate() {
        return firstRememberDate;
    }

    /**
     * @param firstRememberDate the date of the first reminder to set
     */
    public void setFirstRememberDate(Date firstRememberDate) {
        this.firstRememberDate = firstRememberDate;
    }

    /**
     * @return the date of the second reminder
     */
    @Column(name = "DATE_SECOND_REMINDER")
    @Temporal(TemporalType.DATE)
    public Date getSecondRememberDate() {
        return secondRememberDate;
    }

    /**
     * @param secondRememberDate the date of the second reminder
     */
    public void setSecondRememberDate(Date secondRememberDate) {
        this.secondRememberDate = secondRememberDate;
    }

    /**
     * @return the date of the formal notice
     */
    @Column(name = "DATE_FORMAL_NOTICE")
    @Temporal(TemporalType.DATE)
    public Date getFormalNoticeDate() {
        return formalNoticeDate;
    }

    /**
     * @param formalNoticeDate the date of the formal notice to set
     */
    public void setFormalNoticeDate(Date formalNoticeDate) {
        this.formalNoticeDate = formalNoticeDate;
    }

    /**
     * @return true if the {@link Bill} is closed, false otherwise
     */
    @Column(name = "CLOSED")
    public boolean isClosed() {
        return closed;
    }

    /**
     * @param closed true if the {@link Bill} was closed, false otherwise
     */
    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    /**
     * @return the {@link PaymentMode}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PAYMENT_MODE")
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the {@link PaymentMode} to set
     */
    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the billing {@link Address}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress the billing {@link Address} to set
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return a list of {@link BillDetail}
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bill")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<BillDetail> getDetails() {
        return details;
    }

    /**
     * @param details a list of {@link BillDetail} to set
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
        builder.append(super.toString());
        builder.append("Bill [billingAddress=");
        builder.append(billingAddress);
        builder.append(", closed=");
        builder.append(closed);
        builder.append(", firstRememberDate=");
        builder.append(firstRememberDate);
        builder.append(", formalNoticeDate=");
        builder.append(formalNoticeDate);
        builder.append(", secondRememberDate=");
        builder.append(secondRememberDate);
        builder.append(", discountRate=");
        builder.append(discountRate);
        builder.append(", dueDate=");
        builder.append(dueDate);
        builder.append(", paymentDate=");
        builder.append(paymentDate);
        builder.append(", paymentMode=");
        builder.append(paymentMode);
        builder.append("]");
        return builder.toString();
    }

}
