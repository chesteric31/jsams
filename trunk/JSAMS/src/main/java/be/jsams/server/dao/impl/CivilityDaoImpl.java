package be.jsams.server.dao.impl;

import be.jsams.server.dao.CivilityDao;
import be.jsams.server.model.Civility;

/**
 * Civility DAO implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CivilityDaoImpl extends GenericDaoImpl<Civility> implements
		CivilityDao {

	/**
	 * Constructor
	 * 
	 * @param type
	 *            the class type
	 */
	public CivilityDaoImpl(Class<Civility> type) {
		super(type);
	}

}
