package be.jsams.server.service;

import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;

/**
 * Service for all reference objects like society, payment mode, ...
 * 
 * @param <B> a reference bean
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface ReferenceService<B extends AbstractIdentityBean<?, ?>> extends Service<B> {

    /**
     * Finds all the B objects of the database.
     * 
     * @return a list of B objects
     */
    List<B> findAll();
    
}
