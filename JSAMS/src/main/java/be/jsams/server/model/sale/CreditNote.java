package be.jsams.server.model.sale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import be.jsams.common.bean.model.sale.CreditNoteBean;
import be.jsams.common.bean.model.sale.CreditNoteDetailBean;
import be.jsams.server.model.Address;

/**
 * Credit note entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CREDIT_NOTE")
public class CreditNote extends AbstractDocument {

    private Address billingAddress;

    private List<CreditNoteDetail> details;

    /**
     * Default constructor
     */
    public CreditNote() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean the {@link CreditNoteBean}
     */
    public CreditNote(CreditNoteBean bean) {
        super(bean);
        setBillingAddress(new Address(bean.getBillingAddress()));
        List<CreditNoteDetailBean> list = bean.getDetails();
        List<CreditNoteDetail> tmp = new ArrayList<CreditNoteDetail>();
        if (list != null) {
            for (CreditNoteDetailBean detail : list) {
                tmp.add(new CreditNoteDetail(detail, this));
            }
        }
        setDetails(tmp);
    }

    /**
     * 
     * @return the billing {@link Address}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * 
     * @param billingAddress the billing {@link Address} to set
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * 
     * @return a list of {@link CreditNoteDetail}
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "creditNote")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<CreditNoteDetail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details a list of {@link CreditNoteDetail} to set
     */
    public void setDetails(List<CreditNoteDetail> details) {
        this.details = details;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("CreditNote [billingAddress=");
        builder.append(billingAddress);
        builder.append("]");
        return builder.toString();
    }

}
