package be.jsams.server.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Society;

/**
 * Society DAO implementation.
 * 
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public class SocietyDaoImpl extends GenericDaoImpl<Society> implements
		SocietyDao {

	private final Log LOGGER = LogFactory.getLog(this.getClass());

	public SocietyDaoImpl(Class<Society> type) {
		super(type);
	}
}
