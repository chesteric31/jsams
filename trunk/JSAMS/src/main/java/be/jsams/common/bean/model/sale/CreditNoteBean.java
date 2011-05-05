package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.view.sale.CreditNoteBeanView;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.model.sale.CreditNoteDetail;

/**
 * {@link AbstractDocumentBean} for {@link CreditNote} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteBean extends AbstractDocumentBean<CreditNote, CreditNoteBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1611159056426861810L;

    private AddressBean billingAddress;
    private List<CreditNoteDetailBean> details;

    /**
     * Default constructor
     */
    public CreditNoteBean() {
        super();
        setBillingAddress(new AddressBean());
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        setDetails(details);
    }

    /**
     * Constructor
     * 
     * @param model the {@link CreditNote}
     */
    public CreditNoteBean(CreditNote model) {
        super(model);
        setBillingAddress(new AddressBean(model.getBillingAddress()));
        List<CreditNoteDetailBean> beans = new ArrayList<CreditNoteDetailBean>();
        for (CreditNoteDetail detail : model.getDetails()) {
            beans.add(new CreditNoteDetailBean(detail, this));
        }
        setDetails(beans);
    }

    /**
     * @return the billingAddress
     */
    public AddressBean getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress the billingAddress to set
     */
    public void setBillingAddress(AddressBean billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return the details
     */
    public List<CreditNoteDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<CreditNoteDetailBean> details) {
        this.details = details;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteBeanView getView() {
        return new CreditNoteBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        billingAddress.clear();
        getCustomer().clear();
        setCreationDate(null);
        for (CreditNoteDetailBean detail : details) {
            detail.clear();
        }
        setListModel(null);
        setRemark(null);
        setSelection(null);
        getPeriod().clear();
        // only not null if searching...
        if (getSociety() != null) {
            getSociety().clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        CreditNoteBean other = (CreditNoteBean) bean;
        billingAddress.refresh(other.getBillingAddress());
        setCreationDate(other.getCreationDate());
        getCustomer().refresh(other.getCustomer());
        details.clear();
        details.addAll(other.getDetails());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        getSociety().refresh(other.getSociety());
        getPeriod().refresh(other.getPeriod());
    }

}
