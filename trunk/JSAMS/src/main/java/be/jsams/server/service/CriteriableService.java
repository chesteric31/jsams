package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.SocietyBean;

/**
 * Generic service with criteria methods for B extension of {@link AbstractIdentityBean}.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface CriteriableService<B extends AbstractIdentityBean<?, ?>> extends Service<B> {

    /**
     * Find all beans following the criteria bean.
     * 
     * @param criteria the criteria bean
     * @return a list of found beans
     */
    List<B> findByCriteria(final B criteria);
    
    /**
     * Find all the B object of the database for the specific {@link SocietyBean}.
     * 
     * @param currentSociety the {@link SocietyBean} currently used
     * @return a list of B objects
     */
    List<B> findAll(SocietyBean currentSociety);
    
}
