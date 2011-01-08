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

    /**
     * 
     * @return the {@link SocietyDao}
     */
    public SocietyDao getSocietyDao() {
        return societyDao;
    }

    /**
     * 
     * @param societyDao the {@link SocietyDao} to set
     */
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
    public Society findById(final Long id) {
        return societyDao.findById(id);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final Society society) {
        societyDao.update(society);
    }

    /**
     * {@inheritDoc}
     */
    public void create(final Society society) {
        societyDao.add(society);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Society society) {
        societyDao.remove(society);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        societyDao.remove(id);
    }

}
