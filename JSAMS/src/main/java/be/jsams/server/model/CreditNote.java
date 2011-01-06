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

    public CreditNote() {
        super();
    }

    @Column(name = "CREATION_DATE")
    @Temporal(TemporalType.DATE)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Column(name = "REMARK")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CUSTOMER")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public List<CreditNoteDetail> getDetails() {
        return details;
    }

    public void setDetails(List<CreditNoteDetail> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "CreditNote [billingAddress=" + billingAddress + ", creationDate=" + creationDate + ", customer="
                + customer + ", remark=" + remark + "]";
    }

}
