package be.jsams.server.dao.impl;

import be.jsams.server.dao.ContactInformationDao;
import be.jsams.server.model.ContactInformation;

/**
 * Contact information DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ContactInformationDaoImpl extends DaoImpl<ContactInformation> implements ContactInformationDao {

    /**
     * Constructor
     * 
     * @param type the class type
     */
    public ContactInformationDaoImpl(final Class<ContactInformation> type) {
        super(type);
    }

}
