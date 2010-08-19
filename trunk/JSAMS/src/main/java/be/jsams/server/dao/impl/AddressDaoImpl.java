package be.jsams.server.dao.impl;

import be.jsams.server.dao.AddressDao;
import be.jsams.server.model.Address;

public class AddressDaoImpl extends GenericDaoImpl<Address> implements AddressDao {

	public AddressDaoImpl(Class<Address> type) {
		super(type);
	}
}
