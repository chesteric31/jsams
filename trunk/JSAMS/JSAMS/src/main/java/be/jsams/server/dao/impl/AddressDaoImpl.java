package be.jsams.server.dao.impl;

import be.jsams.server.dao.AddressDao;
import be.jsams.server.model.Address;

/**
 * Address DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AddressDaoImpl extends DaoImpl<Address> implements AddressDao {

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public AddressDaoImpl(final Class<Address> type) {
        super(type);
    }

}
