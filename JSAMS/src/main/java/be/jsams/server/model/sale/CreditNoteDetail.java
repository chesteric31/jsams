package be.jsams.server.model.sale;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import be.jsams.server.model.AbstractIdentity;

/**
 * Credit note detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetail extends AbstractIdentity {

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
