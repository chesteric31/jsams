package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.sale.CreditNoteDetailBeanView;
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

    private CreditNoteBean creditNote;
    private BillDetailBean billDetail;

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
        setBillDetail(new BillDetailBean(model.getBillDetail()));
        list.add(this);
        setListModel(list);
        setSelection(this);
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
    }

}
