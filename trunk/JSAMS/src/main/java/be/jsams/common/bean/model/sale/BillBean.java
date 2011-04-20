package be.jsams.common.bean.model.sale;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.sale.BillBeanView;
import be.jsams.server.model.Bill;

/**
 * 
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class BillBean extends AbstractIdentityBean<Bill, BillBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3505169090606007960L;

    private Date creationDate;
    private String remark;
    private BigDecimal discountRate;
    private Date dueDate;
    private boolean paid;
    private Date dateFirstRemember;
    private Date dateSecondRemember;
    private Date dateFormalNotice;
    private boolean closed;

    private PaymentModeBean paymentMode;
    private CustomerBean customer;
    private AddressBean billingAddress;

    private List<BillDetailBean> details;

    public static final String CREATIONDATE_PROPERTY = "creationdate";
    public static final String REMARK_PROPERTY = "remark";
    public static final String DISCOUNTRATE_PROPERTY = "discountrate";
    public static final String DUEDATE_PROPERTY = "duedate";
    public static final String PAID_PROPERTY = "paid";
    public static final String DATEFIRSTREMEMBER_PROPERTY = "datefirstremember";
    public static final String DATESECONDREMEMBER_PROPERTY = "datesecondremember";
    public static final String DATEFORMALNOTICE_PROPERTY = "dateformalnotice";
    public static final String CLOSED_PROPERTY = "closed";

    /**
     * Constructor.
     * 
     * @param model the {@link Bill}
     */
    public BillBean(Bill model) {
        super(model);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        Date oldValue = this.creationDate;
        this.creationDate = creationDate;
        firePropertyChange(CREATIONDATE_PROPERTY, oldValue, this.creationDate);
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        String oldValue = this.remark;
        this.remark = remark;
        firePropertyChange(REMARK_PROPERTY, oldValue, this.remark);
    }

    /**
     * @return the discountRate
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate
     *            the discountRate to set
     */
    public void setDiscountRate(BigDecimal discountRate) {
        BigDecimal oldValue = this.discountRate;
        this.discountRate = discountRate;
        firePropertyChange(DISCOUNTRATE_PROPERTY, oldValue, this.discountRate);

    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate
     *            the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        Date oldValue = this.dueDate;
        this.dueDate = dueDate;
        firePropertyChange(DUEDATE_PROPERTY, oldValue, this.dueDate);
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @param paid
     *            the paid to set
     */
    public void setPaid(boolean paid) {
        boolean oldValue = this.paid;
        this.paid = paid;
        firePropertyChange(PAID_PROPERTY, oldValue, this.paid);
    }

    /**
     * @return the dateFirstRemember
     */
    public Date getDateFirstRemember() {
        return dateFirstRemember;
    }

    /**
     * @param dateFirstRemember
     *            the dateFirstRemember to set
     */
    public void setDateFirstRemember(Date dateFirstRemember) {
        Date oldValue = this.dateFirstRemember;
        this.dateFirstRemember = dateFirstRemember;
        firePropertyChange(DATEFIRSTREMEMBER_PROPERTY, oldValue, this.dateFirstRemember);
    }

    /**
     * @return the dateSecondRemember
     */
    public Date getDateSecondRemember() {
        return dateSecondRemember;
    }

    /**
     * @param dateSecondRemember
     *            the dateSecondRemember to set
     */
    public void setDateSecondRemember(Date dateSecondRemember) {
        Date oldValue = this.dateSecondRemember;
        this.dateSecondRemember = dateSecondRemember;
        firePropertyChange(DATESECONDREMEMBER_PROPERTY, oldValue, this.dateSecondRemember);
    }

    /**
     * @return the dateFormalNotice
     */
    public Date getDateFormalNotice() {
        return dateFormalNotice;
    }

    /**
     * @param dateFormalNotice
     *            the dateFormalNotice to set
     */
    public void setDateFormalNotice(Date dateFormalNotice) {
        Date oldValue = this.dateFormalNotice;
        this.dateFormalNotice = dateFormalNotice;
        firePropertyChange(DATEFORMALNOTICE_PROPERTY, oldValue, this.dateFormalNotice);
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @param closed
     *            the closed to set
     */
    public void setClosed(boolean closed) {
        boolean oldValue = this.closed;
        this.closed = closed;
        firePropertyChange(CLOSED_PROPERTY, oldValue, this.closed);
    }

    /**
     * @return the paymentMode
     */
    public PaymentModeBean getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode
     *            the paymentMode to set
     */
    public void setPaymentMode(PaymentModeBean paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the customer
     */
    public CustomerBean getCustomer() {
        return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    /**
     * @return the billingAddress
     */
    public AddressBean getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress
     *            the billingAddress to set
     */
    public void setBillingAddress(AddressBean billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return the details
     */
    public List<BillDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details
     *            the details to set
     */
    public void setDetails(List<BillDetailBean> details) {
        this.details = details;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BillBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        // TODO Auto-generated method stub
        
    }

}
