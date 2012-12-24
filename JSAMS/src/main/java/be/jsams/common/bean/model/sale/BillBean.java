package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PaymentModeBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.view.sale.BillBeanView;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.detail.BillDetail;

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
    private Date paymentDate;
    private Date firstRememberDate;
    private Date secondRememberDate;
    private Date formalNoticeDate;
    private boolean closed;
    private Double totalEt;
    private Double totalVat;
    private Double totalAti;

    private PaymentModeBean paymentMode;
    private AddressBean billingAddress;

    private List<BillDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String DUE_DATE_PROPERTY = "dueDate";
    public static final String PAYMENT_DATE_PROPERTY = "paymentDate";
    public static final String DATE_FIRST_REMEMBER_PROPERTY = "firstRememberDate";
    public static final String DATE_SECOND_REMEMBER_PROPERTY = "secondRememberDate";
    public static final String DATE_FORMAL_NOTICE_PROPERTY = "formalNoticeDate";
    public static final String CLOSED_PROPERTY = "closed";
    public static final String TOTAL_ET_PROPERTY = "totalEt";
    public static final String TOTAL_ATI_PROPERTY = "totalAti";
    public static final String TOTAL_VAT_PROPERTY = "totalVat";

    private BillMediator mediator;

    /**
     * Constructor.
     * 
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param mode the {@link PaymentModeBean}
     */
    public BillBean(SocietyBean society, CustomerBean customer, PaymentModeBean mode) {
        super(society, customer);
        this.paymentMode = mode;
        this.billingAddress = new AddressBean();
        this.closed = false;
        List<BillDetailBean> details = new ArrayList<BillDetailBean>();
        this.details = details;
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param model the {@link Bill}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param mode the {@link PaymentModeBean}
     * @param productBeanBuilder the {@link ProductBeanBuilder}
     */
    public BillBean(Bill model, SocietyBean society, CustomerBean customer, PaymentModeBean mode,
            ProductBeanBuilder productBeanBuilder) {
        super(model, society, customer, productBeanBuilder);
        this.billingAddress = new AddressBean(model.getBillingAddress());
        List<BillDetailBean> beans = new ArrayList<BillDetailBean>();
        for (BillDetail detail : model.getDetails()) {
            beans.add(new BillDetailBean(detail, this, productBeanBuilder));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.closed = model.isClosed();
        this.paymentDate = model.getPaymentDate();
        this.firstRememberDate = model.getFirstRememberDate();
        this.secondRememberDate = model.getSecondRememberDate();
        this.formalNoticeDate = model.getFormalNoticeDate();
        this.dueDate = model.getDueDate();
        this.paymentMode = mode;
        setView(buildView());
    }

    /**
     * @return the mediator
     */
    public BillMediator getMediator() {
        return mediator;
    }

    /**
     * @param mediator the mediator to set
     */
    public void setMediator(BillMediator mediator) {
        this.mediator = mediator;
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
        mediator.updateTotals();
        mediator.updateDiscountRates();
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
     * @return the payment date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }

    /**
     * @param paymentDate the payment date to set
     */
    public void setPaymentDate(Date paymentDate) {
        Date oldValue = this.paymentDate;
        this.paymentDate = paymentDate;
        firePropertyChange(PAYMENT_DATE_PROPERTY, oldValue, this.paymentDate);
    }

    /**
     * @return the firstRememberDate
     */
    public Date getFirstRememberDate() {
        return firstRememberDate;
    }

    /**
     * @param firstRememberDate the firstRememberDate to set
     */
    public void setFirstRememberDate(Date firstRememberDate) {
        Date oldValue = this.firstRememberDate;
        this.firstRememberDate = firstRememberDate;
        firePropertyChange(DATE_FIRST_REMEMBER_PROPERTY, oldValue, this.firstRememberDate);
    }

    /**
     * @return the secondRememberDate
     */
    public Date getSecondRememberDate() {
        return secondRememberDate;
    }

    /**
     * @param secondRememberDate the secondRememberDate to set
     */
    public void setSecondRememberDate(Date secondRememberDate) {
        Date oldValue = this.secondRememberDate;
        this.secondRememberDate = secondRememberDate;
        firePropertyChange(DATE_SECOND_REMEMBER_PROPERTY, oldValue, this.secondRememberDate);
    }

    /**
     * @return the formalNoticeDate
     */
    public Date getFormalNoticeDate() {
        return formalNoticeDate;
    }

    /**
     * @param dateFormalNotice the dateFormalNotice to set
     */
    public void setFormalNoticeDate(Date dateFormalNotice) {
        Date oldValue = this.formalNoticeDate;
        this.formalNoticeDate = dateFormalNotice;
        firePropertyChange(DATE_FORMAL_NOTICE_PROPERTY, oldValue, this.formalNoticeDate);
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
        mediator.updateTotals();
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
        setPaymentDate(null);
        setDueDate(null);
        setFirstRememberDate(null);
        setSecondRememberDate(null);
        setFormalNoticeDate(null);
        paymentMode.clear();
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
        BillMediator billMediator = other.getMediator();
        List<BillDetailBean> list = other.getDetails();
        if (list != null && !list.isEmpty()) {
            for (BillDetailBean detailBean : list) {
                detailBean.setMediator(billMediator);
            }
        }
        details.addAll(other.getDetails());
        setMediator(billMediator);
        setDiscountRate(other.getDiscountRate());
        setClosed(other.isClosed());
        setPaymentDate(other.getPaymentDate());
        setDueDate(other.getDueDate());
        setFirstRememberDate(other.getFirstRememberDate());
        setSecondRememberDate(other.getSecondRememberDate());
        setFormalNoticeDate(other.getFormalNoticeDate());
        paymentMode.refresh(other.getPaymentMode());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BillBeanView buildView() {
        return new BillBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (billingAddress == null) {
            result += 0;
        } else {
            result += billingAddress.hashCode();
        }
        result = prime * result;
        if (closed) {
            result += 1231;
        } else {
            result += 1237;
        }
        result = prime * result;
        if (firstRememberDate == null) {
            result += 0;
        } else {
            result += firstRememberDate.hashCode();
        }
        result = prime * result;
        if (formalNoticeDate == null) {
            result += 0;
        } else {
            result += formalNoticeDate.hashCode();
        }
        result = prime * result;
        if (secondRememberDate == null) {
            result += 0;
        } else {
            result += secondRememberDate.hashCode();
        }
        result = prime * result;
        if (details == null) {
            result += 0;
        } else {
            result += details.hashCode();
        }
        result = prime * result;
        if (discountRate == null) {
            result += 0;
        } else {
            result += discountRate.hashCode();
        }
        result = prime * result;
        if (dueDate == null) {
            result += 0;
        } else {
            result += dueDate.hashCode();
        }
        result = prime * result;
        if (paymentDate == null) {
            result += 0;
        } else {
            result += paymentDate.hashCode();
        }
        result = prime * result;
        if (paymentMode == null) {
            result += 0;
        } else {
            result += paymentMode.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        // if (this == obj) {
        // return true;
        // }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof BillBean)) {
            return false;
        }
        BillBean other = (BillBean) obj;
        if (billingAddress == null) {
            if (other.billingAddress != null) {
                return false;
            }
        } else if (!billingAddress.equals(other.billingAddress)) {
            return false;
        }
        if (closed != other.closed) {
            return false;
        }
        if (firstRememberDate == null) {
            if (other.firstRememberDate != null) {
                return false;
            }
        } else if (!firstRememberDate.equals(other.firstRememberDate)) {
            return false;
        }
        if (formalNoticeDate == null) {
            if (other.formalNoticeDate != null) {
                return false;
            }
        } else if (!formalNoticeDate.equals(other.formalNoticeDate)) {
            return false;
        }
        if (secondRememberDate == null) {
            if (other.secondRememberDate != null) {
                return false;
            }
        } else if (!secondRememberDate.equals(other.secondRememberDate)) {
            return false;
        }
        if (details == null) {
            if (other.details != null) {
                return false;
            }
        } else if (!details.equals(other.details)) {
            return false;
        }
        if (discountRate == null) {
            if (other.discountRate != null) {
                return false;
            }
        } else if (!discountRate.equals(other.discountRate)) {
            return false;
        }
        if (dueDate == null) {
            if (other.dueDate != null) {
                return false;
            }
        } else if (!dueDate.equals(other.dueDate)) {
            return false;
        }
        if (paymentDate == null) {
            if (other.paymentDate != null) {
                return false;
            }
        } else if (!paymentDate.equals(other.paymentDate)) {
            return false;
        }
        if (paymentMode == null) {
            if (other.paymentMode != null) {
                return false;
            }
        } else if (!paymentMode.equals(other.paymentMode)) {
            return false;
        }
        return true;
    }

    /**
     * @return the totalEt
     */
    public Double getTotalEt() {
        return totalEt;
    }

    /**
     * @param totalEt the totalEt to set
     */
    public void setTotalEt(Double totalEt) {
        Double oldValue = this.totalEt;
        this.totalEt = totalEt;
        firePropertyChange(TOTAL_ET_PROPERTY, oldValue, this.totalEt);
    }

    /**
     * @return the totalAti
     */
    public Double getTotalAti() {
        return totalAti;
    }

    /**
     * @param totalAti the totalAti to set
     */
    public void setTotalAti(Double totalAti) {
        Double oldValue = this.totalAti;
        this.totalAti = totalAti;
        firePropertyChange(TOTAL_ATI_PROPERTY, oldValue, this.totalAti);
    }

    /**
     * @return the totalVat
     */
    public Double getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat the total VAT
     */
    public void setTotalVat(Double totalVat) {
        Double oldValue = this.totalVat;
        this.totalVat = totalVat;
        firePropertyChange(TOTAL_VAT_PROPERTY, oldValue, this.totalVat);
    }

}
