package be.jsams.server.model.sale;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;

import be.jsams.server.model.Address;

/**
 * Credit note entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNote extends AbstractDocument {

    private Address billingAddress;

    private List<CreditNoteDetail> details;

    /**
     * Constructor.
     */
    public CreditNote() {
        super();
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
     * @param billingAddress
     *            the billing {@link Address} to set
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
     * @param details
     *            a list of {@link CreditNoteDetail} to set
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
        builder.append("CreditNote [billingAddress=");
        builder.append(billingAddress);
        builder.append(", creationDate=");
        builder.append(getCreationDate());
        builder.append(", customer=");
        builder.append(getCustomer());
        builder.append(", details=");
        builder.append(details);
        builder.append(", remark=");
        builder.append(getRemark());
        builder.append("]");
        return builder.toString();
    }

}
