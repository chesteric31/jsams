package be.jsams.server.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Credit note entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNote extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5751333650368429179L;

    private Date creationDate;
    private String remark;

    private Customer customer;
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
     * @return the creation date
     */
    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *            the creation date to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return a remark
     */
    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    /**
     * 
     * @param remark
     *            a remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * @return the {@link Customer}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CUSTOMER")
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 
     * @param customer
     *            the {@link Customer} to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    @Override
    public String toString() {
        return "CreditNote [billingAddress=" + billingAddress + ", creationDate=" + creationDate + ", customer="
                + customer + ", remark=" + remark + "]";
    }

}
