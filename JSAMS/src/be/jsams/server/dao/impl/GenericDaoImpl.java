package be.jsams.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.server.dao.GenericDao;

public class GenericDaoImpl<T> implements GenericDao<T> {

	private final Log LOGGER = LogFactory.getLog(this.getClass());

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

	@Override
	public void add(T newInstance) {
		try {
			entityManager.persist(newInstance);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	@Override
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

	@Override
	public T findById(Long id) {
		T t = null;
		try {
			t = entityManager.find(type, id);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
		return t;
	}

	@Override
	public void remove(T persistentObject) {
		try {
			// Merge necessary for the detached object
			T object = entityManager.merge(persistentObject);
			entityManager.remove(object);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	@Override
	public void update(T transientObject) {
		try {
			entityManager.merge(transientObject);
		} catch (RuntimeException re) {
			LOGGER.error(re);
		}
	}

	@Override
	public void remove(Long id) {
		this.remove(this.findById(id));
	}

}
