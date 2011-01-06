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

}
