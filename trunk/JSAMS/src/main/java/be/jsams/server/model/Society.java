package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Society entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "SOCIETY")
public class Society extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8541820243148400802L;
	private String name;
	private BigDecimal capital;
	private String activity;
	private String responsible;
	private String vatNumber;

	private Address address;
	private LegalForm legalForm;
	private ContactInformation contactInformation;

	public Society() {
		super();
	}

	@Column(name = "LABEL")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "CAPITAL")
	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	@Column(name = "ACTIVITY")
	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	@Column(name = "RESPONSIBLE")
	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	@Column(name = "VAT_NUMBER")
	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS")
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "FK_LEGAL_FORM")
	public LegalForm getLegalForm() {
		return legalForm;
	}

	public void setLegalForm(LegalForm legalForm) {
		this.legalForm = legalForm;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CONTACT_INFORMATION")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@Override
	public String toString() {
		return "Society [activity=" + activity + ", address=" + address
				+ ", capital=" + capital + ", contactInformation="
				+ contactInformation + ", name=" + name + ", legalForm="
				+ legalForm + ", responsible=" + responsible + ", vatNumber="
				+ vatNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((activity == null) ? 0 : activity.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((capital == null) ? 0 : capital.hashCode());
		result = prime
				* result
				+ ((contactInformation == null) ? 0 : contactInformation
						.hashCode());
		result = prime * result
				+ ((legalForm == null) ? 0 : legalForm.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((responsible == null) ? 0 : responsible.hashCode());
		result = prime * result
				+ ((vatNumber == null) ? 0 : vatNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
//		if (!super.equals(obj))
//			return false;
		if (getClass() != obj.getClass())
			return false;
		Society other = (Society) obj;
		if (activity == null) {
			if (other.activity != null)
				return false;
		} else if (!activity.equals(other.activity))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (capital == null) {
			if (other.capital != null)
				return false;
		} else if (!capital.equals(other.capital))
			return false;
		if (contactInformation == null) {
			if (other.contactInformation != null)
				return false;
		} else if (!contactInformation.equals(other.contactInformation))
			return false;
		if (legalForm == null) {
			if (other.legalForm != null)
				return false;
		} else if (!legalForm.equals(other.legalForm))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (responsible == null) {
			if (other.responsible != null)
				return false;
		} else if (!responsible.equals(other.responsible))
			return false;
		if (vatNumber == null) {
			if (other.vatNumber != null)
				return false;
		} else if (!vatNumber.equals(other.vatNumber))
			return false;
		return true;
	}

}
