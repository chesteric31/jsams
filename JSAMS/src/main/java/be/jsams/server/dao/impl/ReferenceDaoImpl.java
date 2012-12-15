package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.Query;

import be.jsams.server.dao.ReferenceDao;

/**
 * Generic DAO class implementation for all references like payment mode, society, ... .
 * 
 * @param <T> the class type
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ReferenceDaoImpl<T> extends DaoImpl<T> implements ReferenceDao<T> {

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public ReferenceDaoImpl(final Class<T> type) {
        super(type);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> ts = null;
        Query query = getEntityManager().createQuery("FROM " + getType().getSimpleName());
        ts = query.getResultList();
        return ts;
    }

}
