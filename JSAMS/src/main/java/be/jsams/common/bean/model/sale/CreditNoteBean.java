package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.sale.detail.CreditNoteDetailBean;
import be.jsams.common.bean.view.sale.CreditNoteBeanView;
import be.jsams.server.model.sale.CreditNote;
import be.jsams.server.model.sale.detail.CreditNoteDetail;

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
        this.billingAddress = new AddressBean();
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        this.details = details;
        setView(new CreditNoteBeanView(this));
    }

    /**
     * Constructor
     * 
     * @param model the {@link CreditNote}
     */
    public CreditNoteBean(CreditNote model) {
        super(model);
        this.billingAddress = new AddressBean(model.getBillingAddress());
        List<CreditNoteDetailBean> beans = new ArrayList<CreditNoteDetailBean>();
        for (CreditNoteDetail detail : model.getDetails()) {
            beans.add(new CreditNoteDetailBean(detail, this));
        }
        this.details = beans;
        setView(new CreditNoteBeanView(this));
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
    public void clear() {
        super.clear();
        billingAddress.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        CreditNoteBean other = (CreditNoteBean) bean;
        billingAddress.refresh(other.getBillingAddress());
        details.clear();
        details.addAll(other.getDetails());
    }

}
