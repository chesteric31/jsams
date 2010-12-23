package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Civility entity object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "CIVILITY")
public class Civility extends AbstractIdentity {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -7268110575101379157L;
	private String label;
	private String labelFr;
	private String labelNl;

	public Civility() {
		super();
	}

	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Column(name = "LABEL_FR")
	public String getLabelFr() {
		return labelFr;
	}

	public void setLabelFr(String labelFr) {
		this.labelFr = labelFr;
	}

	@Column(name = "LABEL_NL")
	public String getLabelNl() {
		return labelNl;
	}

	public void setLabelNl(String labelNl) {
		this.labelNl = labelNl;
	}

	@Override
	public String toString() {
		return "Civility [label=" + label + ", labelFr=" + labelFr
				+ ", labelNl=" + labelNl + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((labelFr == null) ? 0 : labelFr.hashCode());
		result = prime * result + ((labelNl == null) ? 0 : labelNl.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		Civility other = (Civility) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (labelFr == null) {
			if (other.labelFr != null)
				return false;
		} else if (!labelFr.equals(other.labelFr))
			return false;
		if (labelNl == null) {
			if (other.labelNl != null)
				return false;
		} else if (!labelNl.equals(other.labelNl))
			return false;
		return true;
	}

}
