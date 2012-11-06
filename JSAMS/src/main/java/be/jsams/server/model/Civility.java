package be.jsams.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import be.jsams.common.bean.model.CivilityBean;

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
     * Constructor.
     */
    public Civility() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link CivilityBean}
     */
    public Civility(CivilityBean bean) {
        super(bean);
    }

}
