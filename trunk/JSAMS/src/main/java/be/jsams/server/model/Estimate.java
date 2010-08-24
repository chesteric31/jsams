package be.jsams.server.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Estimate entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ESTIMATE")
public class Estimate extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 4590224667073848578L;
	private Date creationDate;
	private String remark;
	private BigDecimal discountRate;

	private Contact contact;
	private Customer customer;
	private Address billingAddress;

	private List<EstimateDetail> details;

	public Estimate() {
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

	@Column(name = "DISCOUNT_RATE")
	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CONTACT")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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

	@OneToMany(mappedBy = "estimate")
	public List<EstimateDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EstimateDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Estimate [billingAddress=" + billingAddress + ", contact="
				+ contact + ", creationDate=" + creationDate + ", customer="
				+ customer + ", discountRate=" + discountRate + ", remark="
				+ remark + "]";
	}

}
