package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.view.sale.DeliveryOrderBeanView;
import be.jsams.server.model.sale.DeliveryOrder;
import be.jsams.server.model.sale.DeliveryOrderDetail;

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
     */
    public DeliveryOrderBean() {
        super();
        setDeliveryAddress(new AddressBean());
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
        List<DeliveryOrderDetailBean> beans = new ArrayList<DeliveryOrderDetailBean>();
        for (DeliveryOrderDetail detail : model.getDetails()) {
            beans.add(new DeliveryOrderDetailBean(detail, this));
        }
        setDetails(beans);
        setDiscountRate(model.getDiscountRate());
        setTransferred(model.isTransferred());
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
    public DeliveryOrderBeanView getView() {
        return new DeliveryOrderBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        deliveryAddress.clear();
        getCustomer().clear();
        setCreationDate(null);
        for (DeliveryOrderDetailBean detail : details) {
            detail.clear();
        }
        setDiscountRate(null);
        setListModel(null);
        setRemark(null);
        setSelection(null);
        setTransferred(false);
        getPeriod().clear();
        if (getSociety() != null) {
            getSociety().clear();
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
        getCustomer().refresh(other.getCustomer());
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
        getSociety().refresh(other.getSociety());
        getPeriod().refresh(other.getPeriod());
    }

}
