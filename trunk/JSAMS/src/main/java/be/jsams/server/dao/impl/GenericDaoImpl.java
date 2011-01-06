package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.GenericDao;

/**
 * Generic DAO class implementation.
 * 
 * @param <T>
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class GenericDaoImpl<T> implements GenericDao<T> {

    /** The Logger */
    protected static final Log LOGGER = LogFactory.getLog(GenericDaoImpl.class);

    /** The {@link EntityManager} */
    @PersistenceContext
    private EntityManager entityManager;

    /** The class type */
    private Class<T> type;

    /**
     * Constructor
     * 
     * @param type
     *            the class type
     */
    public GenericDaoImpl(Class<T> type) {
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
     * @param entityManager
     *            the {@link EntityManager} to set
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * {@inheritDoc}
     */
    public void add(T newInstance) {
        try {
            entityManager.persist(newInstance);
        } catch (RuntimeException re) {
            LOGGER.error(re);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        List<T> ts = null;
        try {
            Query query = entityManager.createQuery("FROM " + type.getSimpleName());
            ts = query.getResultList();
        } catch (RuntimeException re) {
            LOGGER.error(re);
        }
        return ts;
    }

    /**
     * {@inheritDoc}
     */
    public T findById(Long id) {
        T t = null;
        try {
            t = entityManager.find(type, id);
        } catch (RuntimeException re) {
            LOGGER.error(re);
        }
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
    public void remove(T persistentObject) {
        try {
            // Merge necessary for the detached object
            // T object = entityManager.merge(persistentObject);
            entityManager.remove(persistentObject);
        } catch (RuntimeException re) {
            LOGGER.error(re);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void update(T transientObject) {
        try {
            entityManager.merge(transientObject);
        } catch (RuntimeException re) {
            LOGGER.error(re);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void remove(Long id) {
        this.remove(this.findById(id));
    }

}
