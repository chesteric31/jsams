package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Payment mode entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "PAYMENT_MODE")
public class PaymentMode extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -9072503968708609612L;
	private String label;

	public PaymentMode() {
		super();
	}

	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return "PaymentMode [label=" + label + "]";
	}

}
