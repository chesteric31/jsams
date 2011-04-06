package be.jsams.server.model;

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
public class Civility extends AbstractTranslatableIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -7268110575101379157L;

    /**
     * Constructor.
     */
    public Civility() {
        super();
    }

}
