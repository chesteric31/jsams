package be.jsams.common.bean.model.sale.detail;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.view.sale.detail.CreditNoteDetailBeanView;
import be.jsams.server.model.sale.detail.BillDetail;
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
    private BillDetailBean billDetail;

    // private ObservableList<CreditNoteDetailBean> list = new
    // ArrayListModel<CreditNoteDetailBean>();

    /**
     * Default constructor
     */
    public CreditNoteDetailBean() {
        super();
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
        BillDetail detail = model.getBillDetail();
        if (detail != null) {
            SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
            this.billDetail = new BillDetailBean(detail, new BillBean(detail.getBill(), currentSociety));
        }
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
        billDetail.refresh(other.getBillDetail());
    }

    @Override
    public CreditNoteDetailBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

}
