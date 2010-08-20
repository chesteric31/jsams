package be.jsams.server.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Product entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractIdentity {

	private String label;
	private BigDecimal price;
	private int quantityStock;
	private int reorderLevel;
	private BigDecimal vatApplicable;
	
	private ProductCategory category;

	public Product() {
		super();
	}

	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "PRICE")
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Column(name = "QUANTITY_STOCK")
	public int getQuantityStock() {
		return quantityStock;
	}

	public void setQuantityStock(int quantityStock) {
		this.quantityStock = quantityStock;
	}

	@Column(name = "REORDER_LEVEL")
	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	@Column(name = "VAT_APPLICABLE")
	public BigDecimal getVatApplicable() {
		return vatApplicable;
	}

	public void setVatApplicable(BigDecimal vatApplicable) {
		this.vatApplicable = vatApplicable;
	}

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_CATEGORY_PRODUCT")
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Product [category=" + category + ", label=" + label
				+ ", price=" + price + ", quantityStock=" + quantityStock
				+ ", reorderLevel=" + reorderLevel + ", vatApplicable="
				+ vatApplicable + "]";
	}

}
