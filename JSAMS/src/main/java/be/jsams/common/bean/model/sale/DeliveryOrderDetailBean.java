package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.view.sale.DeliveryOrderDetailBeanView;
import be.jsams.server.model.sale.DeliveryOrderDetail;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * {@link AbstractIdentityBean} for {@link DeliveryOrderDetail} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailBean extends AbstractIdentityBean<DeliveryOrderDetail, DeliveryOrderDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1224722469323652563L;

    private int quantity;
    private Double vatApplicable;
    private Double discountRate;
    private boolean transferred;

    private DeliveryOrderBean deliveryOrder;
    private ProductBean product;
    private CommandDetailBean commandDetailBean;
    private BillDetailBean billDetailBean;

    public static final String QUANTITY_PROPERTY = "quantity";
    public static final String VAT_APPLICABLE_PROPERTY = "vatApplicable";
    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    private ObservableList<DeliveryOrderDetailBean> list = new ArrayListModel<DeliveryOrderDetailBean>();

    /**
     * Default constructor
     */
    public DeliveryOrderDetailBean() {
        super();
        setListModel(list);
        setSelection(this);
    }
    
    /**
     * Constructor
     * 
     * @param model the {@link DeliveryOrderDetail}
     * @param deliveryOrder the {@link DeliveryOrderBean}
     */
    public DeliveryOrderDetailBean(DeliveryOrderDetail model, DeliveryOrderBean deliveryOrder) {
        super(model);
        setDiscountRate(model.getDiscountRate());
        setDeliveryOrder(deliveryOrder);
        ProductBeanBuilder builder = new ProductBeanBuilder();
        builder.setModel(model.getProduct());
        setProduct(builder.build(false, false));
        setQuantity(model.getQuantity());
        setTransferred(model.isTransferred());
        setVatApplicable(model.getVatApplicable());
        list.add(this);
        setListModel(list);
        setSelection(this);
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        int oldValue = this.quantity;
        this.quantity = quantity;
        firePropertyChange(QUANTITY_PROPERTY, oldValue, this.quantity);
    }

    /**
     * @return the vatApplicable
     */
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * @param vatApplicable the vatApplicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        Double oldValue = this.vatApplicable;
        this.vatApplicable = vatApplicable;
        firePropertyChange(VAT_APPLICABLE_PROPERTY, oldValue, this.vatApplicable);
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
     * @return the product
     */
    public ProductBean getProduct() {
        return product;
    }

    /**
     * @param product the {@link ProductBean} to set
     */
    public void setProduct(ProductBean product) {
        this.product = product;
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
    public DeliveryOrderDetailBeanView getView() {
        return new DeliveryOrderDetailBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        setProduct(null);
        setQuantity(0);
        setVatApplicable(null);
        setDiscountRate(null);
        setListModel(null);
        setSelection(null);
        setTransferred(false);
        deliveryOrder.clear();
        product.clear();
        commandDetailBean.clear();
        billDetailBean.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        DeliveryOrderDetailBean other = (DeliveryOrderDetailBean) bean;
        setDiscountRate(other.getDiscountRate());
        deliveryOrder.refresh(other.getDeliveryOrder());
        setListModel(other.getListModel());
        product.refresh(other.getProduct());
        setQuantity(other.getQuantity());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
        setVatApplicable(other.getVatApplicable());
        commandDetailBean.refresh(other.getCommandDetail());
        billDetailBean.refresh(other.getBillDetail());
    }

}
