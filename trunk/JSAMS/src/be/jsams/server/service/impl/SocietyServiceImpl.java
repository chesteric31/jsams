package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Society;
import be.jsams.server.service.SocietyService;

public class SocietyServiceImpl implements SocietyService {

	private SocietyDao societyDao;

	public SocietyDao getSocietyDao() {
		return societyDao;
	}

	public void setSocietyDao(SocietyDao societyDao) {
		this.societyDao = societyDao;
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

	public void add(Society society) {
		societyDao.add(society);
	}

	public void remove(Society society) {
		societyDao.remove(society);
	}

	public void remove(Long id) {
		societyDao.remove(id);
	}

}
