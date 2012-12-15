package be.jsams.server.dao;

import java.util.List;

/**
 * Generic DAO interface for all references like payment mode, society, ... .
 * 
 * @param <T> the object type
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface ReferenceDao<T> extends Dao<T> {

    /**
     * Finds all the object of this type.
     * 
     * @return a list of object of this type
     */
    List<T> findAll();

}
