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
    private Double totalEt;
    private Double totalVat;
    private Double totalAti;

    private AddressBean deliveryAddress;
    private List<DeliveryOrderDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";
    public static final String TOTAL_ET_PROPERTY = "totalEt";
    public static final String TOTAL_ATI_PROPERTY = "totalAti";
    public static final String TOTAL_VAT_PROPERTY = "totalVat";

    private DeliveryOrderMediator mediator;

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
        setView(buildView());
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
        setView(buildView());
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
     * @return the totalVat
     */
    public Double getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat the totalVat to set
     */
    public void setTotalVat(Double totalVat) {
        Double oldValue = this.totalVat;
        this.totalVat = totalVat;
        firePropertyChange(TOTAL_VAT_PROPERTY, oldValue, this.totalVat);
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
     * @return the mediator
     */
    public DeliveryOrderMediator getMediator() {
        return mediator;
    }

    /**
     * @param mediator the mediator to set
     */
    public void setMediator(DeliveryOrderMediator mediator) {
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
        mediator.updateTotals();
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
        DeliveryOrderMediator deliveryOrderMediator = other.getMediator();
        List<DeliveryOrderDetailBean> list = other.getDetails();
        if (list != null && !list.isEmpty()) {
            for (DeliveryOrderDetailBean detailBean : list) {
                detailBean.setMediator(deliveryOrderMediator);
            }
        }
        details.addAll(list);
        setMediator(deliveryOrderMediator);
        setDiscountRate(other.getDiscountRate());
        setTransferred(other.isTransferred());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderBeanView buildView() {
        return new DeliveryOrderBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (deliveryAddress == null) {
            result += 0;
        } else {
            result += deliveryAddress.hashCode();
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
        if (transferred) {
            result += 1231;
        } else {
            result += 1237;
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
        if (!(obj instanceof DeliveryOrderBean)) {
            return false;
        }
        DeliveryOrderBean other = (DeliveryOrderBean) obj;
        if (deliveryAddress == null) {
            if (other.deliveryAddress != null) {
                return false;
            }
        } else if (!deliveryAddress.equals(other.deliveryAddress)) {
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
        if (transferred != other.transferred) {
            return false;
        }
        return true;
    }

}
