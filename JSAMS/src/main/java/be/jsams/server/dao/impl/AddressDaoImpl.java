package be.jsams.server.dao.impl;

import be.jsams.server.dao.AddressDao;
import be.jsams.server.model.Address;

/**
 * Address DAO implementation.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {

	public AddressDaoImpl(Class<Address> type) {
		super(type);
	}
	
}
