package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Society entity.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
@Entity
@Table(name = "SOCIETY")
public class Society extends AbstractIdentity {

	private String label;
	private BigDecimal capital;
	private String activity;
	private String responsible;
	private Address address;
	private LegalForm legalForm;
	private ContactInformation contactInformation;

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

	@Embedded
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

	@Embedded
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	public String toString() {
		return "Id: " + getId() + " Activity: " + activity + " Label: " + label
				+ " Responsible: " + responsible + " Address: " + address
				+ " Capital: " + capital + " Contact: " + contactInformation
				+ " Legal Form: " + legalForm;
	}

}
