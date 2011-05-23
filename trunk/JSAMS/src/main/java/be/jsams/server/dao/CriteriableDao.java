package be.jsams.server.dao;

import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.server.model.AbstractIdentity;

/**
 * Generic DAO interface for object that can be searched by criteria
 * 
 * @param <T> the object type
 * @param <B> the bean object type
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CriteriableDao<T extends AbstractIdentity, B extends AbstractIdentityBean<T, ?>> extends Dao<T> {

    /**
     * Find a list of models following the criteria model
     * 
     * @param criteria the criteria bean
     * @return a list of models
     */
    List<T> findByCriteria(final B criteria);

    /**
     * @return the currentSociety
     */
    SocietyBean getCurrentSociety();

    /**
     * @param currentSociety the currentSociety to set
     */
    void setCurrentSociety(SocietyBean currentSociety);

    
}
