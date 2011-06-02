package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
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

}
