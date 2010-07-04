package be.jsams.server.service;

import java.util.List;

public interface GenericService<M> {

	public void add(M object);

	public void remove(M object);

	public void remove(Long id);

	public void update(M object);

	public M findById(Long id);

	public List<M> findAll();

}
