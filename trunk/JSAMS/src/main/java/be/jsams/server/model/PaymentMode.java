package be.jsams.server.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import be.jsams.common.bean.model.PaymentModeBean;

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
     * Constructor.
     */
    public PaymentMode() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link PaymentModeBean}
     */
    public PaymentMode(PaymentModeBean bean) {
        super(bean);
    }

}
