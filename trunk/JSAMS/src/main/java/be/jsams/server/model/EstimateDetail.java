package be.jsams.server.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Estimate detail (line) entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ESTIMATE_DETAIL")
public class EstimateDetail implements Serializable {
	
	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -389048976022120236L;
	private EstimateDetailPK id;
	private int quantity;
	private BigDecimal price;
	private BigDecimal vatApplicable;
	private BigDecimal discountRate;
	
	private Estimate estimate;
	private Product product;

	public EstimateDetail() {
		super();
	}

	@EmbeddedId
	public EstimateDetailPK getId() {
		return this.id;
	}

	public void setId(EstimateDetailPK id) {
		this.id = id;
	}

	@Column(name = "QUANTITY")
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "VAT_APPLICABLE")
	public BigDecimal getVatApplicable() {
		return vatApplicable;
	}

	public void setVatApplicable(BigDecimal vatApplicable) {
		this.vatApplicable = vatApplicable;
	}

	@Column(name = "DISCOUNT_RATE")
	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_ESTIMATE")
	@Id
	public Estimate getEstimate() {
		return estimate;
	}

	public void setEstimate(Estimate estimate) {
		this.estimate = estimate;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_PRODUCT")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "EstimateDetail [discountRate=" + discountRate + ", estimate="
				+ estimate + ", price=" + price + ", product=" + product
				+ ", quantity=" + quantity + ", vatApplicable=" + vatApplicable
				+ "]";
	}

}
