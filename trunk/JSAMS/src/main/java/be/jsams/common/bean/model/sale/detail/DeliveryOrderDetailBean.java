package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.view.sale.detail.DeliveryOrderDetailBeanView;
import be.jsams.server.model.sale.detail.DeliveryOrderDetail;

/**
 * {@link AbstractIdentityBean} for {@link DeliveryOrderDetail} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailBean extends
        AbstractDetailBean<DeliveryOrderDetail, DeliveryOrderDetailBeanView, DeliveryOrderBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1224722469323652563L;

    private boolean transferred;

    private DeliveryOrderBean deliveryOrder;
    private CommandDetailBean commandDetailBean;
    private BillDetailBean billDetailBean;

    public static final String TRANSFERRED_PROPERTY = "transferred";

    /**
     * Default constructor
     */
    public DeliveryOrderDetailBean() {
        super();
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param model the {@link DeliveryOrderDetail}
     * @param deliveryOrder the {@link DeliveryOrderBean}
     */
    public DeliveryOrderDetailBean(DeliveryOrderDetail model, DeliveryOrderBean deliveryOrder) {
        super(model, deliveryOrder);
        this.deliveryOrder = deliveryOrder;
        this.transferred = model.isTransferred();
        setView(buildView());
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
     * @return the deliveryOrder
     */
    public DeliveryOrderBean getDeliveryOrder() {
        return deliveryOrder;
    }

    /**
     * @param deliveryOrder the {@link DeliveryOrderBean} to set
     */
    public void setDeliveryOrder(DeliveryOrderBean deliveryOrder) {
        this.deliveryOrder = deliveryOrder;
    }

    /**
     * @return the commandDetailBean
     */
    public CommandDetailBean getCommandDetail() {
        return commandDetailBean;
    }

    /**
     * @param commandDetailBean the commandDetailBean to set
     */
    public void setCommandDetail(CommandDetailBean commandDetailBean) {
        this.commandDetailBean = commandDetailBean;
    }

    /**
     * @return the billDetailBean
     */
    public BillDetailBean getBillDetail() {
        return billDetailBean;
    }

    /**
     * @param billDetailBean the billDetailBean to set
     */
    public void setBillDetail(BillDetailBean billDetailBean) {
        this.billDetailBean = billDetailBean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        setTransferred(false);
        setCommandDetail(null);
        setBillDetail(null);
        setDeliveryOrder(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        DeliveryOrderDetailBean other = (DeliveryOrderDetailBean) bean;
        deliveryOrder.refresh(other.getDeliveryOrder());
        setTransferred(other.isTransferred());
        if (commandDetailBean == null) {
            commandDetailBean = new CommandDetailBean();
        }
        commandDetailBean.refresh(other.getCommandDetail());
        if (billDetailBean == null) {
            billDetailBean = new BillDetailBean();
        }
        billDetailBean.refresh(other.getBillDetail());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DeliveryOrderDetailBeanView buildView() {
        return null;
    }

}
