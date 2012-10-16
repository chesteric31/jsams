package be.jsams.server.model.management;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.model.AbstractNamedIdentity;
import be.jsams.server.model.Address;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Society;

/**
 * Agent entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "AGENT")
public class Agent extends AbstractNamedIdentity {

    private String function;

    private List<Customer> customers;
    private Civility civility;
    private Address address;
    private ContactInformation contactInformation;
    private Society society;

    /**
     * Constructor.
     */
    public Agent() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link AgentBean}
     */
    public Agent(final AgentBean bean) {
        super(bean);
        this.function = bean.getFunction();
        this.address = new Address(bean.getAddress());
        CivilityBean civilityBean = bean.getCivility();
        if (civilityBean != null) {
            this.civility = new Civility(civilityBean);
        }
        this.contactInformation = new ContactInformation(bean.getContactInformation());
        this.society = new Society(bean.getSociety());
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
     * @param function the function to set
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
     * @param civility the {@link Civility} to set
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
     * @param address the {@link Address} to set
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
     * @param contactInformation the {@link ContactInformation} to set
     */
    public void setContactInformation(ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * 
     * @return a list of {@link Customer}
     */
    @ManyToMany(mappedBy = "agent", fetch = FetchType.LAZY)
    public List<Customer> getCustomers() {
        return customers;
    }

    /**
     * 
     * @param customers the list of {@link Customer}s to set
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    /**
     * @return the society
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_SOCIETY")
    public Society getSociety() {
        return society;
    }

    /**
     * @param society the society to set
     */
    public void setSociety(Society society) {
        this.society = society;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Agent [address=");
        builder.append(address);
        builder.append(", civility=");
        builder.append(civility);
        builder.append(", contactInformation=");
        builder.append(contactInformation);
        builder.append(", customers=");
        builder.append(customers);
        builder.append(", function=");
        builder.append(function);
        builder.append(", society=");
        builder.append(society);
        builder.append("]");
        return builder.toString();
    }

}
