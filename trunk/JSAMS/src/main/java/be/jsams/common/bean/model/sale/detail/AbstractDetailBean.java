package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.view.sale.detail.AbstractDetailBeanView;
import be.jsams.server.model.sale.detail.AbstractDetail;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Abstract class bean for document detail.
 * 
 * @param <M> an extension of {@link AbstractDetail}
 * @param <V> an extension of {@link AbstractDetailBeanView}
 * @param <D> an extension of {@link AbstractDocumentBean}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractDetailBean
        <M extends AbstractDetail, V extends AbstractDetailBeanView<?, ?, ?>, D extends AbstractDocumentBean<?, ?>>
        extends AbstractIdentityBean<M, V> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6916525274272221031L;

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;

    private ProductBean product;

    public static final String QUANTITY_PROPERTY = "quantity";
    public static final String PRICE_PROPERTY = "price";
    public static final String VAT_APPLICABLE_PROPERTY = "vatApplicable";
    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";

    private ObservableList<AbstractDetailBean<M, V, D>> list = new ArrayListModel<AbstractDetailBean<M, V, D>>();

    /**
     * Default constructor
     */
    public AbstractDetailBean() {
        super();
        setListModel(list);
        setSelection(this);
    }
    
    /**
     * Constructor.
     * 
     * @param model the {@link AbstractDetail}
     * @param document the {@link AbstractDocumentBean}
     */
    public AbstractDetailBean(M model, D document) {
        super(model);
        setDiscountRate(model.getDiscountRate());
        setPrice(model.getPrice());
        ProductBeanBuilder builder = new ProductBeanBuilder();
        builder.setSociety(document.getSociety());
        builder.setModel(model.getProduct());
        setProduct(builder.build(false, false));
        setQuantity(model.getQuantity());
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

//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public AbstractDetailBeanView getView() {
//        return new DocumentDetailBeanView(this);
//    }

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
        setProduct(null);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        AbstractDetailBean<M, V, D> other = (AbstractDetailBean<M, V, D>) bean;
        setDiscountRate(other.getDiscountRate());
        setListModel(other.getListModel());
        setPrice(other.getPrice());
        product.refresh(other.getProduct());
        setQuantity(other.getQuantity());
        setSelection(other.getSelection());
        setVatApplicable(other.getVatApplicable());
    }
    
}
