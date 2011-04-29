package be.jsams.server.model.sale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.jsams.server.model.AbstractIdentity;

/**
 * Credit note detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetail extends AbstractIdentity {

    private int quantity;
    private Double price;
    private String description;

    private CreditNote creditNote;
    private BillDetail billDetail;

    /**
     * Constructor.
     */
    public CreditNoteDetail() {
        super();
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
     * @return the description
     */
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return the {@link CreditNote}
     */
    @ManyToOne(cascade = CascadeType.ALL)
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
    @ManyToOne(cascade = CascadeType.ALL)
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
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("CreditNoteDetail [billDetail=");
        builder.append(billDetail);
        builder.append(", creditNote=");
        builder.append(creditNote);
        builder.append(", description=");
        builder.append(description);
        builder.append(", price=");
        builder.append(price);
        builder.append(", quantity=");
        builder.append(quantity);
        builder.append("]");
        return builder.toString();
    }

}
