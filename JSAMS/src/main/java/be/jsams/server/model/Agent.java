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

	public Agent() {
		super();
	}

	@Column(name = "FUNCTION")
	public String getFunction() {
		return function;
	}

	public void setFunction(String function) {
		this.function = function;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CIVILITY")
	public Civility getCivility() {
		return civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CONTACT_INFORMATION")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@ManyToMany(mappedBy = "agent")
	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Agent [address=" + address + ", civility=" + civility
				+ ", contactInformation=" + contactInformation + ", customers="
				+ customers + ", function=" + function + ", name=" + getName() + "]";
	}

}
