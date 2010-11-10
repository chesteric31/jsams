package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.service.LegalFormService;

/**
 * Legal form service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
public class LegalFormServiceImpl implements LegalFormService {

	private LegalFormDao legalFormDao;

	public LegalFormDao getLegalFormDao() {
		return legalFormDao;
	}

	public void setLegalFormDao(LegalFormDao legalFormDao) {
		this.legalFormDao = legalFormDao;
	}

	public void create(LegalForm legalForm) {
		legalFormDao.add(legalForm);
	}

	public void delete(LegalForm legalForm) {
		legalFormDao.remove(legalForm);
	}

	public void delete(Long id) {
		legalFormDao.remove(id);
	}

	public List<LegalForm> findAll() {
		return legalFormDao.findAll();
	}

	public LegalForm findById(Long id) {
		return legalFormDao.findById(id);
	}

	public void update(LegalForm legalForm) {
		legalFormDao.update(legalForm);
	}

}
