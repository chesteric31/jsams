package be.jsams.server.dao;

import java.util.List;

public interface GenericDao<T> {
	
	public void add(T newInstance);
	
	public T findById(Long id);
	
	public List<T> findAll();
	
	public void update(T transientObject);
	
	public void remove(T persistentObject);
	
	public void remove(Long id);

}
