package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.server.model.sale.Bill;
import be.jsams.server.model.sale.CreditNote;

/**
 * Credit note detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CREDIT_NOTE_DETAIL")
public class CreditNoteDetail extends AbstractDetail {

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
        this.creditNote = creditNote;
        BillDetailBean billDetailBean = bean.getBillDetail();
        if (billDetailBean != null) {
            this.billDetail = new BillDetail(billDetailBean, new Bill(billDetailBean.getBill()));
        }
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

}
