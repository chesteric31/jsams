package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;
import be.jsams.server.service.SocietyService;

/**
 * Society service implementation.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyServiceImpl implements SocietyService {

	private SocietyDao societyDao;
	private LegalFormDao legalFormDao;

	public SocietyDao getSocietyDao() {
		return societyDao;
	}

	public void setSocietyDao(SocietyDao societyDao) {
		this.societyDao = societyDao;
	}

	public LegalFormDao getLegalFormDao() {
		return legalFormDao;
	}

	public void setLegalFormDao(LegalFormDao legalFormDao) {
		this.legalFormDao = legalFormDao;
	}

	public List<Society> findAll() {
		return societyDao.findAll();
	}

	public Society findById(Long id) {
		return societyDao.findById(id);
	}

	public void update(Society society) {
		societyDao.update(society);
	}

	public void create(Society society) {
		societyDao.add(society);
	}

	public void delete(Society society) {
		societyDao.remove(society);
	}

	public void delete(Long id) {
		societyDao.remove(id);
	}

	public void createLegalForm(LegalForm legalForm) {
		legalFormDao.add(legalForm);
	}

}
