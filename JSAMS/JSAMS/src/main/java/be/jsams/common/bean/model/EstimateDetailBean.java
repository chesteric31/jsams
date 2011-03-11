package be.jsams.common.bean.model;

import java.math.BigDecimal;

import be.jsams.common.bean.view.EstimateDetailBeanView;
import be.jsams.server.model.EstimateDetail;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDetailBean extends AbstractIdentityBean<EstimateDetail, EstimateDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4401359166302949409L;

    private int quantity;
    private BigDecimal price;
    private BigDecimal vatApplicable;
    private BigDecimal discountRate;
    private boolean transferred;

    private EstimateBean estimate;
    private ProductBean product;

    public static final String QUANTITY_PROPERTY = "quantity";
    public static final String PRICE_PROPERTY = "price";
    public static final String VATAPPLICABLE_PROPERTY = "vatapplicable";
    public static final String DISCOUNTRATE_PROPERTY = "discountrate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    /**
     * Constructor
     * 
     * @param model
     *            the {@link EstimateDetail}
     */
    public EstimateDetailBean(EstimateDetail model) {
        super(model);
        // TODO Auto-generated constructor stub
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
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(BigDecimal price) {
        BigDecimal oldValue = this.price;
        this.price = price;
        firePropertyChange(PRICE_PROPERTY, oldValue, this.price);
    }

    /**
     * @return the vatApplicable
     */
    public BigDecimal getVatApplicable() {
        return vatApplicable;
    }

    /**
     * @param vatApplicable the vatApplicable to set
     */
    public void setVatApplicable(BigDecimal vatApplicable) {
        BigDecimal oldValue = this.vatApplicable;
        this.vatApplicable = vatApplicable;
        firePropertyChange(VATAPPLICABLE_PROPERTY, oldValue, this.vatApplicable);
    }

    /**
     * @return the discountRate
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate the discountRate to set
     */
    public void setDiscountRate(BigDecimal discountRate) {
        BigDecimal oldValue = this.discountRate;
        this.discountRate = discountRate;
        firePropertyChange(DISCOUNTRATE_PROPERTY, oldValue, this.discountRate);
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
     * @return the estimate
     */
    public EstimateBean getEstimate() {
        return estimate;
    }

    /**
     * @param estimate the estimate to set
     */
    public void setEstimate(EstimateBean estimate) {
        this.estimate = estimate;
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
    public EstimateDetailBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

}
