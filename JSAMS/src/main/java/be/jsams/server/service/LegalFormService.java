package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.LegalForm;

/**
 * Legal form interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface LegalFormService {

	public void create(LegalForm legalForm);

	public void delete(LegalForm legalForm);

	public void delete(Long id);

	public void update(LegalForm legalForm);

	public LegalForm findById(Long id);

	public List<LegalForm> findAll();

}
