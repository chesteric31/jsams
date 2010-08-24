package be.jsams.server.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
	private boolean transferred;
	private String remark;
	private BigDecimal discountRate;

	private Contact contact;
	private Customer customer;
	private Address billingAddress;

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

	@Column(name = "TRANSFERRED")
	public boolean isTransferred() {
		return transferred;
	}

	public void setTransferred(boolean transferred) {
		this.transferred = transferred;
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

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CONTACT")
	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CUSTOMER")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS_BILLING")
	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	@Override
	public String toString() {
		return "Estimate [billingAddress=" + billingAddress + ", contact="
				+ contact + ", creationDate=" + creationDate + ", customer="
				+ customer + ", discountRate=" + discountRate + ", remark="
				+ remark + ", transferred=" + transferred + "]";
	}

}
