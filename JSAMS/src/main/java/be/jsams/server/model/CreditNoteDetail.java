package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Credit note detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetail extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2841513484839283690L;
    private int quantity;
    private BigDecimal price;
    private String description;

    private CreditNote creditNote;
    private BillDetail billDetail;

    public CreditNoteDetail() {
        super();
    }

    @Column(name = "QUANTITY")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "PRICE")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CREDIT_NOTE")
    public CreditNote getCreditNote() {
        return creditNote;
    }

    public void setCreditNote(CreditNote creditNote) {
        this.creditNote = creditNote;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_BILL_DETAIL")
    public BillDetail getBillDetail() {
        return billDetail;
    }

    public void setBillDetail(BillDetail billDetail) {
        this.billDetail = billDetail;
    }

    @Override
    public String toString() {
        return "CreditNoteDetail [billDetail=" + billDetail + ", creditNote=" + creditNote + ", description="
                + description + ", price=" + price + ", quantity=" + quantity + "]";
    }

}
