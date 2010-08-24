package be.jsams.server.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * The primary key class for the ESTIMATE_DETAIL database table.
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Embeddable
public class EstimateDetailPK implements Serializable {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -8029041188018490218L;
	private Long estimateFK;
	private Long id;

	public EstimateDetailPK() {
		super();
	}

	@Column(name = "ID")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "FK_ESTIMATE")
	public Long getEstimateFK() {
		return estimateFK;
	}

	public void setEstimateFK(Long estimateFK) {
		this.estimateFK = estimateFK;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstimateDetailPK other = (EstimateDetailPK) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (estimateFK == null) {
			if (other.estimateFK != null) {
				return false;
			}
		} else if (!estimateFK.equals(other.estimateFK)) {
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
				+ ((estimateFK == null) ? 0 : estimateFK.hashCode());
		return result;
	}
	
}