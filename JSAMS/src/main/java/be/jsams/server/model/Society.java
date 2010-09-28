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
	private String label;
	private BigDecimal capital;
	private String activity;
	private String responsible;
	private String vatNumber;
	
	private Address address;
	private LegalForm legalForm;
	private ContactInformation contactInformation;
	private Civility civility;

	public Society() {
		super();
	}

	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	@OneToOne(cascade = CascadeType.ALL)
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CIVILITY")
	public Civility getCivility() {
		return civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}

	@Override
	public String toString() {
		return "Society [activity=" + activity + ", address=" + address
				+ ", capital=" + capital + ", civility=" + civility
				+ ", contactInformation=" + contactInformation + ", label="
				+ label + ", legalForm=" + legalForm + ", responsible="
				+ responsible + ", vatNumber=" + vatNumber + "]";
	}

}
