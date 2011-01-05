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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof AbstractIdentity)) {
			return false;
		}
		AbstractIdentity other = (AbstractIdentity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

}
