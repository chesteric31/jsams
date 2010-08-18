package be.jsams.server.dao;

import java.util.List;

/**
 * Generic DAO interface.
 * @param <T> the model
 *
 * @author chesteric31
 * @version $Revision:$ $Date:$ $Author:$
 */
public interface GenericDao<T> {
	
	public void add(T newInstance);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public void flush();
	
	public void update(T transientObject);
	
	public void remove(T persistentObject);
	
	public void remove(Long id);

}
