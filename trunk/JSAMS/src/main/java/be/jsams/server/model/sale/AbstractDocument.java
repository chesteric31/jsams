package be.jsams.server.model.sale;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.model.management.Customer;

/**
 * {@link MappedSuperclass} abstract class to abstract all the common fields between the documents:
 * estimate, command, delivery order, bill and credit note.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@MappedSuperclass
public abstract class AbstractDocument extends AbstractIdentity {

    private Date creationDate;
    private String remark;

    private Customer customer;

    /**
     * Constructor.
     */
    public AbstractDocument() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractDocumentBean} to use
     */
    public AbstractDocument(final AbstractDocumentBean<?, ?> bean) {
        super(bean);
        setCreationDate(bean.getCreationDate());
        setCustomer(new Customer(bean.getCustomer()));
        setRemark(bean.getRemark());
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
     * @param creationDate the creation date to set
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
     * @param remark a remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 
     * @return the {@link Customer}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_CUSTOMER")
    public Customer getCustomer() {
        return customer;
    }

    /**
     * 
     * @param customer the {@link Customer} to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractDocument [creationDate=");
        builder.append(creationDate);
        builder.append(", customer=");
        builder.append(customer);
        builder.append(", remark=");
        builder.append(remark);
        builder.append("]");
        return builder.toString();
    }
    
}
