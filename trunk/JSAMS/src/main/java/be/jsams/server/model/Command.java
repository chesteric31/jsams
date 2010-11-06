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
 * Command entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "COMMAND")
public class Command extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 8102219472482522357L;
	private Date creationDate;
	private String remark;
	private BigDecimal discountRate;
	
	private Agent agent;
	private Customer customer;
	private Address billingAddress;
	private Address deliveryAddress;

	private List<CommandDetail> details;
	
	public Command() {
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
	@JoinColumn(name = "FK_AGENT")
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
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

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS_DELIVERY")
	public Address getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Address deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@OneToMany(mappedBy = "command")
	public List<CommandDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CommandDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Command [agent=" + agent + ", billingAddress=" + billingAddress
				+ ", creationDate=" + creationDate + ", customer=" + customer
				+ ", deliveryAddress=" + deliveryAddress + ", details="
				+ details + ", discountRate=" + discountRate + ", remark="
				+ remark + "]";
	}

}
