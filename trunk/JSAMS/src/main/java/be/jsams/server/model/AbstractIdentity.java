package be.jsams.server.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Super class for Identity interface.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@MappedSuperclass
public class AbstractIdentity implements Identity, Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2585047845813378914L;
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
