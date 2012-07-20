package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private boolean paid;
    private Date dateFirstRemember;
    private Date dateSecondRemember;
    private Date dateFormalNotice;
    private boolean closed;

    private PaymentModeBean paymentMode;
    private AddressBean billingAddress;

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
        this.paid = false;
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
     */
    public BillBean(Bill model, SocietyBean society, CustomerBean customer, PaymentModeBean mode) {
        super(model, society, customer);
        this.billingAddress = new AddressBean(model.getBillingAddress());
        List<BillDetailBean> beans = new ArrayList<BillDetailBean>();
        for (BillDetail detail : model.getDetails()) {
            beans.add(new BillDetailBean(detail, this));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.closed = model.isClosed();
        this.paid = model.isPaid();
        this.dateFirstRemember = model.getDateFirstRemember();
        this.dateSecondRemember = model.getDateSecondRemember();
        this.dateFormalNotice = model.getDateFormalNotice();
        this.dueDate = model.getDueDate();
        this.paymentMode = mode;
        setView(buildView());
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
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setClosed(other.isClosed());
        setPaid(other.isPaid());
        setDueDate(other.getDueDate());
        setDateFirstRemember(other.getDateFirstRemember());
        setDateSecondRemember(other.getDateSecondRemember());
        setDateFormalNotice(other.getDateFormalNotice());
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
        if (dateFirstRemember == null) {
            result += 0;
        } else {
            result += dateFirstRemember.hashCode();
        }
        result = prime * result;
        if (dateFormalNotice == null) {
            result += 0;
        } else {
            result += dateFormalNotice.hashCode();
        }
        result = prime * result;
        if (dateSecondRemember == null) {
            result += 0;
        } else {
            result += dateSecondRemember.hashCode();
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
        if (paid) {
            result += 1231;
        } else {
            result += 1237;
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
//        if (this == obj) {
//            return true;
//        }
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
        if (dateFirstRemember == null) {
            if (other.dateFirstRemember != null) {
                return false;
            }
        } else if (!dateFirstRemember.equals(other.dateFirstRemember)) {
            return false;
        }
        if (dateFormalNotice == null) {
            if (other.dateFormalNotice != null) {
                return false;
            }
        } else if (!dateFormalNotice.equals(other.dateFormalNotice)) {
            return false;
        }
        if (dateSecondRemember == null) {
            if (other.dateSecondRemember != null) {
                return false;
            }
        } else if (!dateSecondRemember.equals(other.dateSecondRemember)) {
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
        if (paid != other.paid) {
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

}
