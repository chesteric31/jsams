package be.jsams.server.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Agent entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "AGENT")
public class Agent extends AbstractNamedIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8508634686187725716L;
    private String function;

    private List<Customer> customers;
    private Civility civility;
    private Address address;
    private ContactInformation contactInformation;

    /**
     * Constructor.
     */
    public Agent() {
        super();
    }

    /**
     * 
     * @return the function String
     */
    @Column(name = "FUNCTION")
    public String getFunction() {
        return function;
    }

    /**
     * 
     * @param function
     *            the function to set
     */
    public void setFunction(String function) {
        this.function = function;
    }

    /**
     * 
     * @return the {@link Civility}
     */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_CIVILITY")
    public Civility getCivility() {
        return civility;
    }

    /**
     * 
     * @param civility
     *            the {@link Civility} to set
     */
    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    /**
     * 
     * @return the {@link Address}
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS")
    public Address getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *            the {@link Address} to set
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * 
     * @return the {@link ContactInformation}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_CONTACT_INFORMATION")
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    /**
     * 
     * @param contactInformation
     *            the {@link ContactInformation} to set
     */
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * 
     * @return a list of {@link Customer}
     */
    @ManyToMany(mappedBy = "agent")
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * 
     * @param customers
     *            the list of {@link Customer}s to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public String toString() {
        return "Agent [address=" + address + ", civility=" + civility + ", contactInformation=" + contactInformation
                + ", customers=" + customers + ", function=" + function + ", name=" + getName() + "]";
    }

}
