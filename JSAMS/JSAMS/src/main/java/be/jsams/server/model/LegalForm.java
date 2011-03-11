package be.jsams.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import be.jsams.common.bean.model.LegalFormBean;

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

    /**
     * Constructor.
     */
    public LegalForm() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link LegalFormBean}
     */
    public LegalForm(LegalFormBean bean) {
        super(bean);
    }

}
