package be.jsams.server.dao.impl;

import be.jsams.server.dao.PaymentModeDao;
import be.jsams.server.model.PaymentMode;

/**
 * Payment mode DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class PaymentModeDaoImpl extends GenericDaoImpl<PaymentMode> implements
		PaymentModeDao {

	/**
	 * Constructor
	 * 
	 * @param type
	 *            the class type
	 */
	public PaymentModeDaoImpl(Class<PaymentMode> type) {
		super(type);
	}

}
