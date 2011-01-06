package be.jsams.server.service.impl;

import java.util.List;

import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.Society;
import be.jsams.server.service.SocietyService;

/**
 * Society service implementation.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyServiceImpl implements SocietyService {

    private SocietyDao societyDao;

    public SocietyDao getSocietyDao() {
        return societyDao;
    }

    public void setSocietyDao(SocietyDao societyDao) {
        this.societyDao = societyDao;
    }

    /**
     * {@inheritDoc}
     */
    public List<Society> findAll() {
        return societyDao.findAll();
    }

    /**
     * {@inheritDoc}
     */
    public Society findById(Long id) {
        return societyDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(Society society) {
        societyDao.update(society);
    }

    /**
     * {@inheritDoc}
     */
    public void create(Society society) {
        societyDao.add(society);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Society society) {
        societyDao.remove(society);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {
        societyDao.remove(id);
    }

}
