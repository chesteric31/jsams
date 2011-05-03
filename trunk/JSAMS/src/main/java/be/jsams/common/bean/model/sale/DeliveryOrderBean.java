package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.model.sale.DeliveryOrderDetail;

/**
 * {@link AbstractIdentityBean} for {@link DeliveryOrder} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderBean extends AbstractIdentityBean<DeliveryOrder, DeliveryOrderBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6774873021985339541L;

    private Date creationDate;
    private PeriodBean period;
    private String remark;
    private Double discountRate;
    private boolean transferred;

    private CustomerBean customer;
    private AddressBean deliveryAddress;
    private List<DeliveryOrderDetailBean> details;
    private SocietyBean society;

    public static final String CREATION_DATE_PROPERTY = "creationDate";
    public static final String REMARK_PROPERTY = "remark";
    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    /**
     * Default constructor.
     */
    public DeliveryOrderBean() {
        super();
        setDeliveryAddress(new AddressBean());
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        setCreationDate(new Date());
        setCustomer(new CustomerBean(society));
        setPeriod(new PeriodBean());
        setTransferred(false);
        List<DeliveryOrderDetailBean> details = new ArrayList<DeliveryOrderDetailBean>();
        setDetails(details);
    }
    
    /**
     * Constructor
     * 
     * @param model
     *            the {@link DeliveryOrder}
     */
    public DeliveryOrderBean(DeliveryOrder model) {
        super(model);
        setDeliveryAddress(new AddressBean(model.getDeliveryAddress()));
        setCreationDate(model.getCreationDate());
        setCustomer(new CustomerBean(model.getCustomer()));
        List<DeliveryOrderDetailBean> beans = new ArrayList<DeliveryOrderDetailBean>();
        for (DeliveryOrderDetail detail : model.getDetails()) {
            beans.add(new DeliveryOrderDetailBean(detail, this));
        }
        setDetails(beans);
        setDiscountRate(model.getDiscountRate());
        setRemark(model.getRemark());
        setTransferred(model.isTransferred());
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        Date oldValue = this.creationDate;
        this.creationDate = creationDate;
        firePropertyChange(CREATION_DATE_PROPERTY, oldValue, this.creationDate);
    }

    /**
     * @return the period
     */
    public PeriodBean getPeriod() {
        return period;
    }

    /**
     * @param period the period to set
     */
    public void setPeriod(PeriodBean period) {
        this.period = period;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        String oldValue = this.remark;
        this.remark = remark;
        firePropertyChange(REMARK_PROPERTY, oldValue, this.remark);
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
     * @return the transferred
     */
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred the transferred to set
     */
    public void setTransferred(boolean transferred) {
        boolean oldValue = this.transferred;
        this.transferred = transferred;
        firePropertyChange(TRANSFERRED_PROPERTY, oldValue, this.transferred);
    }

    /**
     * @return the customer
     */
    public CustomerBean getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    /**
     * @return the deliveryAddress
     */
    public AddressBean getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @param deliveryAddress the deliveryAddress to set
     */
    public void setDeliveryAddress(AddressBean deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * @return the details
     */
    public List<DeliveryOrderDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<DeliveryOrderDetailBean> details) {
        this.details = details;
    }

    /**
     * @return the society
     */
    public SocietyBean getSociety() {
        return society;
    }

    /**
     * @param society the society to set
     */
    public void setSociety(SocietyBean society) {
        this.society = society;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBeanView getView() {
        return new DeliveryOrderBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        deliveryAddress.clear();
        customer.clear();
        setCreationDate(null);
        for (DeliveryOrderDetailBean detail : details) {
            detail.clear();
        }
        setDiscountRate(null);
        setListModel(null);
        setRemark(null);
        setSelection(null);
        setTransferred(false);
        period.clear();
        if (society != null) {
            society.clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        DeliveryOrderBean other = (DeliveryOrderBean) bean;
        deliveryAddress.refresh(other.getDeliveryAddress());
        setCreationDate(other.getCreationDate());
        customer.refresh(other.getCustomer());
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
        society.refresh(other.getSociety());
    }

}
