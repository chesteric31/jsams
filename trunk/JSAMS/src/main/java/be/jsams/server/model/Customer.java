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
 * Customer entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 81052360779940712L;
	private String name;
	private String vatNumber;
	private BigDecimal defaultDiscountRate;
	private String bank1;
	private String bank2;
	private BigDecimal creditLimit;
	private BigDecimal vatApplicable;
	private String description;
	
	private Address deliveryAddress;
	private Address billingAddress;
	private PaymentMode paymentMode;
	private ContactInformation contactInformation;
	private Civility civility;
	
	private Agent agent;
	
	private LegalForm legalForm;
	
	public Customer() {
		super();
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "VAT_NUMBER")
	public String getVatNumber() {
		return vatNumber;
	}

	public void setVatNumber(String vatNumber) {
		this.vatNumber = vatNumber;
	}

	@Column(name = "DEFAULT_DISCOUNT_RATE")
	public BigDecimal getDefaultDiscountRate() {
		return defaultDiscountRate;
	}

	public void setDefaultDiscountRate(BigDecimal defaultDiscountRate) {
		this.defaultDiscountRate = defaultDiscountRate;
	}

	@Column(name = "BANK_1")
	public String getBank1() {
		return bank1;
	}

	public void setBank1(String bank1) {
		this.bank1 = bank1;
	}

	@Column(name = "BANK_2")
	public String getBank2() {
		return bank2;
	}

	public void setBank2(String bank2) {
		this.bank2 = bank2;
	}

	@Column(name = "CREDIT_LIMIT")
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	@Column(name = "VAT_APPLICABLE")
	public BigDecimal getVatApplicable() {
		return vatApplicable;
	}

	public void setVatApplicable(BigDecimal vatApplicable) {
		this.vatApplicable = vatApplicable;
	}

	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS_DELIVERY")
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS_BILLING")
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "FK_PAYMENT_MODE")
	public PaymentMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PaymentMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CONTACT_INFORMATION")
	public ContactInformation getContactInformation() {
		return contactInformation;
	}

	public void setContactInformation(ContactInformation contactInformation) {
		this.contactInformation = contactInformation;
	}

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "FK_CIVILITY")
	public Civility getCivility() {
		return civility;
	}

	public void setCivility(Civility civility) {
		this.civility = civility;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_AGENT")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "FK_LEGAL_FORM")
	public LegalForm getLegalForm() {
		return legalForm;
	}

	public void setLegalForm(LegalForm legalForm) {
		this.legalForm = legalForm;
	}

	@Override
	public String toString() {
		return "Customer [agent=" + agent + ", bank1=" + bank1 + ", bank2="
				+ bank2 + ", billingAddress=" + billingAddress + ", civility="
				+ civility + ", contactInformation=" + contactInformation
				+ ", creditLimit=" + creditLimit + ", defaultDiscountRate="
				+ defaultDiscountRate + ", deliveryAddress=" + deliveryAddress
				+ ", description=" + description + ", legalForm=" + legalForm
				+ ", name=" + name + ", paymentMode=" + paymentMode
				+ ", vatApplicable=" + vatApplicable + ", vatNumber="
				+ vatNumber + "]";
	}

}
