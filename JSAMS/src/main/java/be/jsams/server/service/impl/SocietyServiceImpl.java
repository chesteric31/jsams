package be.jsams.server.service.impl;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.builder.SocietyBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
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
    
    private SocietyBeanBuilder builder;

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
    public List<SocietyBean> findAll() {
        List<Society> societies = societyDao.findAll();
        List<SocietyBean> beans = new ArrayList<SocietyBean>();
        for (Society society : societies) {
            builder.setModel(society);
            beans.add(builder.build(false));
        }
        return beans;
    }

    /**
     * {@inheritDoc}
     */
    public SocietyBean findById(final Long id) {
        Society society = societyDao.findById(id);
        builder.setModel(society);
        SocietyBean bean = builder.build(false);
        return bean;
    }

    /**
     * {@inheritDoc}
     */
    public void update(final SocietyBean bean) {
        Society society = new Society(bean);
        societyDao.update(society);
    }

    /**
     * {@inheritDoc}
     */
    public SocietyBean create(final SocietyBean bean) {
        Society society = new Society(bean);
        Society persistedSociety = societyDao.add(society);
        builder.setModel(persistedSociety);
        return builder.build(false);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final SocietyBean bean) {
        Society society = new Society(bean);
        societyDao.delete(society);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        societyDao.delete(id);
    }

    /**
     * @return the builder
     */
    public SocietyBeanBuilder getBuilder() {
        return builder;
    }

    /**
     * @param builder the builder to set
     */
    public void setBuilder(SocietyBeanBuilder builder) {
        this.builder = builder;
    }

}
