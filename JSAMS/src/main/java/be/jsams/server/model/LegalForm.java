package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Legal form entity.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
@Entity
@Table(name = "LEGAL_FORM")
public class LegalForm extends AbstractIdentity {

	private String label;

	public LegalForm() {
		super();
	}

	@Column(name = "LABEL")
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String toString() {
		return getId() + " " + label;
	}
}
