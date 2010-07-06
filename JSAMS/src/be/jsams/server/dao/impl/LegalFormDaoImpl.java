package be.jsams.server.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;

public class LegalFormDaoImpl extends GenericDaoImpl<LegalForm> implements
		LegalFormDao {

	private final Log LOGGER = LogFactory.getLog(this.getClass());

	public LegalFormDaoImpl(Class<LegalForm> type) {
		super(type);
	}

}
