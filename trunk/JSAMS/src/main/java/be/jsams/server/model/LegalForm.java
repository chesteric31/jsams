package be.jsams.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Legal form entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "LEGAL_FORM")
public class LegalForm extends AbstractTranslatableIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7811347200401042583L;

	public LegalForm() {
		super();
	}

	@Override
	public String toString() {
		return "LegalForm [label=" + getLabel() + ", labelFr=" + getLabelFr()
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
		if (!(obj instanceof LegalForm)) {
			return false;
		}
		LegalForm other = (LegalForm) obj;
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
