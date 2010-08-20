package be.jsams.server.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Super class for Identity interface.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::    $
 */
@MappedSuperclass
public class AbstractIdentity implements Identity {

	private Long id;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
