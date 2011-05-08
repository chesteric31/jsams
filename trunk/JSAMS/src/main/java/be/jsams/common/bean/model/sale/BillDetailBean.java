package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.view.sale.BillDetailBeanView;
import be.jsams.server.model.sale.BillDetail;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * {@link AbstractIdentityBean} for {@link BillDetail} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillDetailBean extends AbstractIdentityBean<BillDetail, BillDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4398604502381563804L;

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;
    private boolean transferred;

    private BillBean bill;
    private ProductBean product;

    public static final String QUANTITY_PROPERTY = "quantity";
    public static final String PRICE_PROPERTY = "price";
    public static final String VAT_APPLICABLE_PROPERTY = "vatApplicable";
    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    private ObservableList<BillDetailBean> list = new ArrayListModel<BillDetailBean>();

    /**
     * Default constructor
     */
    public BillDetailBean() {
        super();
        setListModel(list);
        setSelection(this);
    }
    
    /**
     * Constructor.
     * 
     * @param model the {@link BillDetail}
     * @param bill the {@link BillBean}  
     */
    public BillDetailBean(BillDetail model, BillBean bill) {
        super(model);
        setDiscountRate(model.getDiscountRate());
        setBill(bill);
        setPrice(model.getPrice());
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
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        Double oldValue = this.price;
        this.price = price;
        firePropertyChange(PRICE_PROPERTY, oldValue, this.price);
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
     * @return the bill
     */
    public BillBean getBill() {
        return bill;
    }

    /**
     * @param bill the bill to set
     */
    public void setBill(BillBean bill) {
        this.bill = bill;
    }

    /**
     * @return the product
     */
    public ProductBean getProduct() {
        return product;
    }

    /**
     * @param product the product to set
     */
    public void setProduct(ProductBean product) {
        this.product = product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BillDetailBeanView getView() {
        return new BillDetailBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        setPrice(null);
        setProduct(null);
        setQuantity(0);
        setVatApplicable(null);
        setDiscountRate(null);
        setListModel(null);
        setSelection(null);
        setTransferred(false);
        setBill(null);
        setProduct(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        BillDetailBean other = (BillDetailBean) bean;
        setDiscountRate(other.getDiscountRate());
        bill.refresh(other.getBill());
        setListModel(other.getListModel());
        setPrice(other.getPrice());
        product.refresh(other.getProduct());
        setQuantity(other.getQuantity());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
        setVatApplicable(other.getVatApplicable());
    }

}
