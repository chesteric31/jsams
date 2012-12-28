package be.jsams.server.dao.impl;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    /** The {@link EntityManager} */
    @PersistenceContext
    private EntityManager entityManager;

    /** The class type */
    private Class<T> type;
    
    protected static final String AND = " AND ";
    protected static final String WHERE = " WHERE ";

    /**
     * Constructor.
     * 
     * @param type the class type
     */
    public DaoImpl(final Class<T> type) {
        this.type = type;
    }

    /**
     * @return the {@link EntityManager}
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
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
    public T findById(final Long id) {
        return entityManager.find(getType(), id);
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

    /**
     * @return the type
     */
    public Class<T> getType() {
        return type;
    }

    /**
     * @return the date format
     */
    protected SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

}
