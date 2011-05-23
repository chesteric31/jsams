package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.Dao;

/**
 * Generic DAO class implementation.
 * 
 * @param <T> the class type
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DaoImpl<T> implements Dao<T> {

    /** The Logger */
    protected static final Log LOGGER = LogFactory.getLog(DaoImpl.class);

    /** The {@link EntityManager} */
    @PersistenceContext
    private EntityManager entityManager;

    /** The class type */
    private Class<T> type;

    /**
     * Constructor
     * 
     * @param type the class type
     */
    public DaoImpl(final Class<T> type) {
        this.type = type;
    }

    /**
     * 
     * @return the {@link EntityManager}
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 
     * @param entityManager the {@link EntityManager} to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    public T add(final T newInstance) {
        entityManager.persist(newInstance);
        return newInstance;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> ts = null;
        Query query = entityManager.createQuery("FROM " + type.getSimpleName());
        ts = query.getResultList();
        return ts;
    }

    /**
     * {@inheritDoc}
     */
    public T findById(final Long id) {
        T t = entityManager.find(type, id);
        return t;
    }

    /**
     * {@inheritDoc}
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final T persistentObject) {
        // Merge necessary for the detached object
        T object = entityManager.merge(persistentObject);
        entityManager.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    public void update(final T transientObject) {
        entityManager.merge(transientObject);
    }

    /**
     * {@inheritDoc}
     */
    public void delete(final Long id) {
        this.delete(this.findById(id));
    }

}
