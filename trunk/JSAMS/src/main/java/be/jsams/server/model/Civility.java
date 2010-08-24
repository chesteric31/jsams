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

	@Override
	public String toString() {
		return "Civility [label=" + label + "]";
	}
	
}
