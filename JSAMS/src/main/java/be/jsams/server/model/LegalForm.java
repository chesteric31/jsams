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
}
