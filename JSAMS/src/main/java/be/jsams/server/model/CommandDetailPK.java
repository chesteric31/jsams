package be.jsams.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the COMMAND_DETAIL database table.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Embeddable
public class CommandDetailPK implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 7023819367222530340L;
	private Long commandFK;
	private Long id;

	public CommandDetailPK() {
		super();
	}

	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FK_COMMAND")
	public Long getCommandFK() {
		return commandFK;
	}

	public void setCommandFK(Long commandFK) {
		this.commandFK = commandFK;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CommandDetailPK other = (CommandDetailPK) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (commandFK == null) {
			if (other.commandFK != null) {
				return false;
			}
		} else if (!commandFK.equals(other.commandFK)) {
			return false;
		}
		return true;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((commandFK == null) ? 0 : commandFK.hashCode());
		return result;
	}
	
}