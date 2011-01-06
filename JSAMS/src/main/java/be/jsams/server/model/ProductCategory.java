package be.jsams.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Product category entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory extends AbstractTranslatableIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5602936385893795004L;

	/**
	 * Constructor
	 */
	public ProductCategory() {
		super();
	}

}
