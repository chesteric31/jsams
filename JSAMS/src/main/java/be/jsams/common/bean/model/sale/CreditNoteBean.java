package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
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

    private Double totalEt;
    private Double totalVat;
    private Double totalAti;

    private AddressBean billingAddress;
    private List<CreditNoteDetailBean> details;
    public static final String TOTAL_ET_PROPERTY = "totalEt";
    public static final String TOTAL_ATI_PROPERTY = "totalAti";
    public static final String TOTAL_VAT_PROPERTY = "totalVat";

    private CreditNoteMediator mediator;

    /**
     * Default constructor
     * 
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     */
    public CreditNoteBean(SocietyBean society, CustomerBean customer) {
        super(society, customer);
        this.billingAddress = new AddressBean();
        List<CreditNoteDetailBean> details = new ArrayList<CreditNoteDetailBean>();
        this.details = details;
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param model the {@link CreditNote}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     */
    public CreditNoteBean(CreditNote model, SocietyBean society, CustomerBean customer) {
        super(model, society, customer);
        this.billingAddress = new AddressBean(model.getBillingAddress());
        List<CreditNoteDetailBean> beans = new ArrayList<CreditNoteDetailBean>();
        for (CreditNoteDetail detail : model.getDetails()) {
            beans.add(new CreditNoteDetailBean(detail, this));
        }
        this.details = beans;
        setView(buildView());
    }

    /**
     * @return the mediator
     */
    public CreditNoteMediator getMediator() {
        return mediator;
    }

    /**
     * @param mediator the mediator to set
     */
    public void setMediator(CreditNoteMediator mediator) {
        this.mediator = mediator;
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
        mediator.updateTotals();
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
        CreditNoteMediator creditNoteMediator = other.getMediator();
        setMediator(creditNoteMediator);
        List<CreditNoteDetailBean> list = other.getDetails();
        if (list != null && !list.isEmpty()) {
            for (CreditNoteDetailBean detailBean : list) {
                detailBean.setMediator(creditNoteMediator);
            }
        }
        details.addAll(other.getDetails());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteBeanView buildView() {
        return new CreditNoteBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (billingAddress == null) {
            result += 0;
        } else {
            result += billingAddress.hashCode();
        }
        result = prime * result;
        if (details == null) {
            result += 0;
        } else {
            result += details.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        // if (this == obj) {
        // return true;
        // }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof CreditNoteBean)) {
            return false;
        }
        CreditNoteBean other = (CreditNoteBean) obj;
        if (billingAddress == null) {
            if (other.billingAddress != null) {
                return false;
            }
        } else if (!billingAddress.equals(other.billingAddress)) {
            return false;
        }
        if (details == null) {
            if (other.details != null) {
                return false;
            }
        } else if (!details.equals(other.details)) {
            return false;
        }
        return true;
    }

    /**
     * @return the totalEt
     */
    public Double getTotalEt() {
        return totalEt;
    }

    /**
     * @param totalEt the totalEt to set
     */
    public void setTotalEt(Double totalEt) {
        Double oldValue = this.totalEt;
        this.totalEt = totalEt;
        firePropertyChange(TOTAL_ET_PROPERTY, oldValue, this.totalEt);
    }

    /**
     * @return the totalAti
     */
    public Double getTotalAti() {
        return totalAti;
    }

    /**
     * @param totalAti the totalAti to set
     */
    public void setTotalAti(Double totalAti) {
        Double oldValue = this.totalAti;
        this.totalAti = totalAti;
        firePropertyChange(TOTAL_ATI_PROPERTY, oldValue, this.totalAti);
    }

    /**
     * @return the totalVat
     */
    public Double getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat the total VAT
     */
    public void setTotalVat(Double totalVat) {
        Double oldValue = this.totalVat;
        this.totalVat = totalVat;
        firePropertyChange(TOTAL_VAT_PROPERTY, oldValue, this.totalVat);
    }

}
