package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.view.sale.detail.CreditNoteDetailBeanView;
import be.jsams.server.model.sale.detail.CreditNoteDetail;

/**
 * {@link AbstractIdentityBean} for {@link CreditNoteDetail} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetailBean extends
        AbstractDetailBean<CreditNoteDetail, CreditNoteDetailBeanView, CreditNoteBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2345486313955221349L;

    private CreditNoteBean creditNote;

    /**
     * Default constructor
     */
    public CreditNoteDetailBean() {
        super();
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param model the {@link CreditNoteDetail}
     * @param creditNote the {@link CreditNoteBean}
     */
    public CreditNoteDetailBean(CreditNoteDetail model, CreditNoteBean creditNote) {
        super(model, creditNote);
        this.creditNote = creditNote;
        setView(buildView());
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
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        setCreditNote(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        CreditNoteDetailBean other = (CreditNoteDetailBean) bean;
        creditNote.refresh(other.getCreditNote());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteDetailBeanView buildView() {
        // TODO Auto-generated method stub
        return null;
    }

}
