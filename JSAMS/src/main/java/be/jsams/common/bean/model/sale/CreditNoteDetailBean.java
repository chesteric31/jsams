package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.view.sale.CreditNoteDetailBeanView;
import be.jsams.server.model.sale.BillDetail;
import be.jsams.server.model.sale.CreditNoteDetail;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * {@link AbstractIdentityBean} for {@link CreditNoteDetail} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetailBean extends AbstractIdentityBean<CreditNoteDetail, CreditNoteDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2345486313955221349L;

    private int quantity;
    private Double price;
    private Double vatApplicable;
    private Double discountRate;

    private ProductBean product;
    private CreditNoteBean creditNote;
    private BillDetailBean billDetail;

    public static final String QUANTITY_PROPERTY = "quantity";
    public static final String PRICE_PROPERTY = "price";
    public static final String VAT_APPLICABLE_PROPERTY = "vatApplicable";
    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";

    private ObservableList<CreditNoteDetailBean> list = new ArrayListModel<CreditNoteDetailBean>();

    /**
     * Default constructor
     */
    public CreditNoteDetailBean() {
        super();
        setListModel(list);
        setSelection(this);
    }

    /**
     * Constructor
     * 
     * @param model the {@link CreditNoteDetail}
     * @param creditNote the {@link CreditNoteBean}
     */
    public CreditNoteDetailBean(CreditNoteDetail model, CreditNoteBean creditNote) {
        super(model);
        setCreditNote(creditNote);
        BillDetail detail = model.getBillDetail();
        if (detail != null) {
            setBillDetail(new BillDetailBean(detail, new BillBean(detail.getBill())));
        }
        list.add(this);
        setListModel(list);
        setSelection(this);
        ProductBeanBuilder builder = new ProductBeanBuilder();
        builder.setModel(model.getProduct());
        setProduct(builder.build(false, false));
        setQuantity(model.getQuantity());
        setVatApplicable(model.getVatApplicable());
        setPrice(model.getPrice());
    }

    /**
     * @return the estimate
     */
    public CreditNoteBean getCreditNote() {
        return creditNote;
    }

    /**
     * @param creditNote the {@link CreditNoteBean} to set
     */
    public void setCreditNote(CreditNoteBean creditNote) {
        this.creditNote = creditNote;
    }

    /**
     * @return the {@link BillDetailBean}
     */
    public BillDetailBean getBillDetail() {
        return billDetail;
    }

    /**
     * @param billDetail the {@link BillDetailBean} to set
     */
    public void setBillDetail(BillDetailBean billDetail) {
        this.billDetail = billDetail;
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
    public CreditNoteDetailBeanView getView() {
        return new CreditNoteDetailBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        setListModel(null);
        setSelection(null);
        setCreditNote(null);
        setProduct(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        CreditNoteDetailBean other = (CreditNoteDetailBean) bean;
        creditNote.refresh(other.getCreditNote());
        setListModel(other.getListModel());
        billDetail.refresh(other.getBillDetail());
        setSelection(other.getSelection());
        product.refresh(other.getProduct());
    }

}
