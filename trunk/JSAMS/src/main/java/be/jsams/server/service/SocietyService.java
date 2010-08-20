package be.jsams.server.service;

import java.util.List;

import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

/**
 * Society Service interface.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface SocietyService {

	public void create(Society society);

	public void delete(Society society);

	public void delete(Long id);

	public void update(Society society);

	public Society findById(Long id);

	public List<Society> findAll();
	
	public void createLegalForm(LegalForm legalForm);

}
