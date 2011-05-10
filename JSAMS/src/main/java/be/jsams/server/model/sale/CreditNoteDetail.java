package be.jsams.server.model.sale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.BillDetailBean;
import be.jsams.common.bean.model.sale.CreditNoteDetailBean;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.model.management.Product;

/**
 * Credit note detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CREDIT_NOTE_DETAIL")
public class CreditNoteDetail extends AbstractIdentity {

    private int quantity;
    private Double price;
    private Double vatApplicable;

    private Product product;
    private CreditNote creditNote;
    private BillDetail billDetail;

    /**
     * Constructor.
     */
    public CreditNoteDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link CreditNoteDetailBean}
     * @param creditNote the {@link CreditNote} model
     */
    public CreditNoteDetail(CreditNoteDetailBean bean, final CreditNote creditNote) {
        super(bean);
        setCreditNote(creditNote);
        BillDetailBean billDetailBean = bean.getBillDetail();
        if (billDetailBean != null) {
            setBillDetail(new BillDetail(billDetailBean, new Bill(billDetailBean.getBill())));
        }
        setPrice(bean.getPrice());
        setProduct(new Product(bean.getProduct()));
        setQuantity(bean.getQuantity());
        setVatApplicable(bean.getVatApplicable());
    }

    /**
     * 
     * @return the {@link CreditNote}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_CREDIT_NOTE")
    public CreditNote getCreditNote() {
        return creditNote;
    }

    /**
     * 
     * @param creditNote the {@link CreditNote} to set
     */
    public void setCreditNote(CreditNote creditNote) {
        this.creditNote = creditNote;
    }

    /**
     * 
     * @return a {@link BillDetail}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_BILL_DETAIL")
    public BillDetail getBillDetail() {
        return billDetail;
    }

    /**
     * 
     * @param billDetail the {@link BillDetail} to set
     */
    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }

    /**
     * 
     * @return the quantity
     */
    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    /**
     * 
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * 
     * @return the price
     */
    @Column(name = "PRICE")
    public Double getPrice() {
        return price;
    }

    /**
     * 
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * @return the VAT to apply
     */
    @Column(name = "VAT_APPLICABLE")
    public Double getVatApplicable() {
        return vatApplicable;
    }

    /**
     * 
     * @param vatApplicable the VAT applicable to set
     */
    public void setVatApplicable(Double vatApplicable) {
        this.vatApplicable = vatApplicable;
    }

    /**
     * 
     * @return the {@link Product}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_PRODUCT")
    public Product getProduct() {
        return product;
    }

    /**
     * 
     * @param product the {@link Product} to set
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreditNoteDetail [billDetail=");
        builder.append(billDetail);
        builder.append(", creditNote=");
        builder.append(creditNote);
        builder.append("]");
        return builder.toString();
    }

}
