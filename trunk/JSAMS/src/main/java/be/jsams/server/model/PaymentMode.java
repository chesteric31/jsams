package be.jsams.server.model;

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
public class PaymentMode extends AbstractTranslatableIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -9072503968708609612L;

	public PaymentMode() {
		super();
	}

	@Override
	public String toString() {
		return "PaymentMode [label=" + getLabel() + ", labelFr=" + getLabelFr()
				+ ", labelNl=" + getLabelNl() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((getLabel() == null) ? 0 : getLabel().hashCode());
		result = prime * result
				+ ((getLabelFr() == null) ? 0 : getLabelFr().hashCode());
		result = prime * result
				+ ((getLabelNl() == null) ? 0 : getLabelNl().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		// if (!super.equals(obj)) {
		// return false;
		// }
		if (!(obj instanceof PaymentMode)) {
			return false;
		}
		PaymentMode other = (PaymentMode) obj;
		if (getLabel() == null) {
			if (other.getLabel() != null) {
				return false;
			}
		} else if (!getLabel().equals(other.getLabel())) {
			return false;
		}
		if (getLabelFr() == null) {
			if (other.getLabelFr() != null) {
				return false;
			}
		} else if (!getLabelFr().equals(other.getLabelFr())) {
			return false;
		}
		if (getLabelNl() == null) {
			if (other.getLabelNl() != null) {
				return false;
			}
		} else if (!getLabelNl().equals(other.getLabelNl())) {
			return false;
		}
		return true;
	}

}
