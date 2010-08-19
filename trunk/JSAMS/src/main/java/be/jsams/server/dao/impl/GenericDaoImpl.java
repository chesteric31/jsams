package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.GenericDao;
import be.jsams.server.model.AbstractIdentity;

/**
 * Generic DAO class implementation.
 * 
 * @param <T>
 *            the model
 * 
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public class GenericDaoImpl<T extends AbstractIdentity> implements
		GenericDao<T> {

	protected final Log LOGGER = LogFactory.getLog(this.getClass());

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> type;

	public GenericDaoImpl(Class<T> type) {
		this.type = type;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void add(T newInstance) {
		try {
			entityManager.persist(newInstance);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	public List<T> findAll() {
		List<T> ts = null;
		try {
			Query query = entityManager.createQuery("FROM "
					+ type.getSimpleName());
			ts = query.getResultList();
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return ts;
	}

	public T findById(Long id) {
		T t = null;
		try {
			t = entityManager.find(type, id);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return t;
	}

	public void flush() {
		entityManager.flush();
	}

	public void remove(T persistentObject) {
		try {
			// Merge necessary for the detached object
			T object = entityManager.merge(persistentObject);
			entityManager.remove(object);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	public void update(T transientObject) {
		try {
			entityManager.merge(transientObject);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	public void remove(Long id) {
		this.remove(this.findById(id));
	}

}
