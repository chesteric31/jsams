package be.jsams.server.dao;

import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.server.model.AbstractIdentity;

/**
 * Generic DAO interface for object that can be searched by criteria.
 * 
 * @param <T> the object type
 * @param <B> the bean object type
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CriteriableDao<T extends AbstractIdentity, B extends AbstractIdentityBean<T, ?>> extends Dao<T> {

    /**
     * Finds all models with a link to the current society id.
     * 
     * @param currentSocietyId the current society id to use
     * @return all the models with a link to the current society id
     */
    List<T> findAll(Long currentSocietyId);

    /**
     * Finds a list of models following the current society id and the criteria
     * model.
     * 
     * @param currentSocietyId the current society id to use
     * @param criteria the criteria bean
     * @return a list of models with a link to the current society id and
     *         corresponded the criteria
     */
    List<T> findByCriteria(Long currentSocietyId, final B criteria);

}
