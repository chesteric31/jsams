package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.view.sale.BillBeanView;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.BillDetail;

/**
 * {@link AbstractDocumentBean} for {@link Bill} object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class BillBean extends AbstractDocumentBean<Bill, BillBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3505169090606007960L;

    private Double discountRate;
    private Date dueDate;
    private boolean paid;
    private Date dateFirstRemember;
    private Date dateSecondRemember;
    private Date dateFormalNotice;
    private boolean closed;

    private PaymentModeBean paymentMode;
    private AddressBean billingAddress;

    private PaymentModeBeanBuilder paymentModeBuilder;

    private List<BillDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String DUE_DATE_PROPERTY = "dueDate";
    public static final String PAID_PROPERTY = "paid";
    public static final String DATE_FIRST_REMEMBER_PROPERTY = "dateFirstRemember";
    public static final String DATE_SECOND_REMEMBER_PROPERTY = "dateSecondRemember";
    public static final String DATE_FORMAL_NOTICE_PROPERTY = "dateFormalNotice";
    public static final String CLOSED_PROPERTY = "closed";

    /**
     * Constructor.
     */
    public BillBean() {
        super();
        paymentModeBuilder = new PaymentModeBeanBuilder();
        paymentModeBuilder.setDao(JsamsApplicationContext.getPaymentModeDao());
        setPaymentMode(paymentModeBuilder.build());
        setBillingAddress(new AddressBean());
        setClosed(false);
        setPaid(false);
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        setDetails(details);
        setView(new BillBeanView(this));
    }

    /**
     * Constructor.
     * 
     * @param model the {@link Bill}
     */
    public BillBean(Bill model) {
        super(model);
        setBillingAddress(new AddressBean(model.getBillingAddress()));
        List<BillDetailBean> beans = new ArrayList<BillDetailBean>();
        for (BillDetail detail : model.getDetails()) {
            beans.add(new BillDetailBean(detail, this));
        }
        setDetails(beans);
        setDiscountRate(model.getDiscountRate());
        setClosed(model.isClosed());
        setPaid(model.isPaid());
        setDateFirstRemember(model.getDateFirstRemember());
        setDateSecondRemember(model.getDateSecondRemember());
        setDateFormalNotice(model.getDateFormalNotice());
        setDueDate(model.getDueDate());
        paymentModeBuilder = new PaymentModeBeanBuilder();
        paymentModeBuilder.setDao(JsamsApplicationContext.getPaymentModeDao());
        PaymentMode mode = model.getPaymentMode();
        if (mode != null) {
            paymentModeBuilder.setModel(mode);
        }
        setPaymentMode(paymentModeBuilder.build());
        setView(new BillBeanView(this));
    }

    /**
     * @return the discountRate
     */
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate the discountRate to set
     */
    public void setDiscountRate(Double discountRate) {
        Double oldValue = this.discountRate;
        this.discountRate = discountRate;
        firePropertyChange(DISCOUNT_RATE_PROPERTY, oldValue, this.discountRate);
    }

    /**
     * @return the dueDate
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * @param dueDate the dueDate to set
     */
    public void setDueDate(Date dueDate) {
        Date oldValue = this.dueDate;
        this.dueDate = dueDate;
        firePropertyChange(DUE_DATE_PROPERTY, oldValue, this.dueDate);
    }

    /**
     * @return the paid
     */
    public boolean isPaid() {
        return paid;
    }

    /**
     * @param paid the paid to set
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
     * @param dateFirstRemember the dateFirstRemember to set
     */
    public void setDateFirstRemember(Date dateFirstRemember) {
        Date oldValue = this.dateFirstRemember;
        this.dateFirstRemember = dateFirstRemember;
        firePropertyChange(DATE_FIRST_REMEMBER_PROPERTY, oldValue, this.dateFirstRemember);
    }

    /**
     * @return the dateSecondRemember
     */
    public Date getDateSecondRemember() {
        return dateSecondRemember;
    }

    /**
     * @param dateSecondRemember the dateSecondRemember to set
     */
    public void setDateSecondRemember(Date dateSecondRemember) {
        Date oldValue = this.dateSecondRemember;
        this.dateSecondRemember = dateSecondRemember;
        firePropertyChange(DATE_SECOND_REMEMBER_PROPERTY, oldValue, this.dateSecondRemember);
    }

    /**
     * @return the dateFormalNotice
     */
    public Date getDateFormalNotice() {
        return dateFormalNotice;
    }

    /**
     * @param dateFormalNotice the dateFormalNotice to set
     */
    public void setDateFormalNotice(Date dateFormalNotice) {
        Date oldValue = this.dateFormalNotice;
        this.dateFormalNotice = dateFormalNotice;
        firePropertyChange(DATE_FORMAL_NOTICE_PROPERTY, oldValue, this.dateFormalNotice);
    }

    /**
     * @return the closed
     */
    public boolean isClosed() {
        return closed;
    }

    /**
     * @param closed the closed to set
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
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(PaymentModeBean paymentMode) {
        this.paymentMode = paymentMode;
    }

    /**
     * @return the billingAddress
     */
    public AddressBean getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress the billingAddress to set
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
     * @param details the details to set
     */
    public void setDetails(List<BillDetailBean> details) {
        this.details = details;
    }

    /**
     * @return the paymentModeBuilder
     */
    public PaymentModeBeanBuilder getPaymentModeBuilder() {
        return paymentModeBuilder;
    }

    /**
     * @param paymentModeBuilder the paymentModeBuilder to set
     */
    public void setPaymentModeBuilder(PaymentModeBeanBuilder paymentModeBuilder) {
        this.paymentModeBuilder = paymentModeBuilder;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public BillBeanView getView() {
        return new BillBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        billingAddress.clear();
        setDiscountRate(null);
        setClosed(false);
        setPaid(false);
        setDueDate(null);
        setDateFirstRemember(null);
        setDateSecondRemember(null);
        setDateFormalNotice(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        BillBean other = (BillBean) bean;
        billingAddress.refresh(other.getBillingAddress());
        details.clear();
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setClosed(other.isClosed());
        setPaid(other.isPaid());
        setDueDate(other.getDueDate());
        setDateFirstRemember(other.getDateFirstRemember());
        setDateSecondRemember(other.getDateSecondRemember());
        setDateFormalNotice(other.getDateFormalNotice());
        setPaymentMode(other.getPaymentMode());
    }

}
