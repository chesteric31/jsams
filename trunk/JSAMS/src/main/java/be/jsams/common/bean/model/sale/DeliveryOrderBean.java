package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.model.sale.detail.DeliveryOrderDetail;

/**
 * {@link AbstractDocumentBean} for {@link DeliveryOrder} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderBean extends AbstractDocumentBean<DeliveryOrder, DeliveryOrderBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6774873021985339541L;

    private Double discountRate;
    private boolean transferred;

    private AddressBean deliveryAddress;
    private List<DeliveryOrderDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    /**
     * Default constructor.
     * 
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     */
    public DeliveryOrderBean(SocietyBean society, CustomerBean customer) {
        super(society, customer);
        this.deliveryAddress = new AddressBean();
        this.transferred = false;
        List<DeliveryOrderDetailBean> details = new ArrayList<DeliveryOrderDetailBean>();
        this.details = details;
        setView(new DeliveryOrderBeanView(this));
    }

    /**
     * Constructor
     * 
     * @param model the {@link DeliveryOrder}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     */
    public DeliveryOrderBean(DeliveryOrder model, SocietyBean society, CustomerBean customer) {
        super(model, society, customer);
        this.deliveryAddress = new AddressBean(model.getDeliveryAddress());
        List<DeliveryOrderDetailBean> beans = new ArrayList<DeliveryOrderDetailBean>();
        for (DeliveryOrderDetail detail : model.getDetails()) {
            beans.add(new DeliveryOrderDetailBean(detail, this));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.transferred = model.isTransferred();
        setView(new DeliveryOrderBeanView(this));
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
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        deliveryAddress.clear();
        setDiscountRate(null);
        setTransferred(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        DeliveryOrderBean other = (DeliveryOrderBean) bean;
        deliveryAddress.refresh(other.getDeliveryAddress());
        details.clear();
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setTransferred(other.isTransferred());
    }

}
