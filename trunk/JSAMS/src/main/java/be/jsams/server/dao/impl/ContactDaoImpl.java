package be.jsams.server.dao.impl;

import be.jsams.server.dao.ContactDao;
import be.jsams.server.model.Contact;

/**
 * Contact DAO implementation.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ContactDaoImpl extends GenericDaoImpl<Contact> implements
		ContactDao {

	public ContactDaoImpl(Class<Contact> type) {
		super(type);
	}

}
