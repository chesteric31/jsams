package be.jsams.server.model;

import javax.persistence.Embeddable;

/**
 * Estimate detail primary key formed by Estimate Id and Estimate Detail Id.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Embeddable
public class EstimateDetailPK {

	private Long estimateId;
	private Long estimateDetailId;
	
	public EstimateDetailPK() {
		super();
	}
	
	public EstimateDetailPK(Long estimateId, Long estimateDetailId) {
		this.estimateId = estimateId;
		this.estimateDetailId = estimateDetailId;
	}

	public Long getEstimateId() {
		return estimateId;
	}

	public void setEstimateId(Long estimateId) {
		this.estimateId = estimateId;
	}

	public Long getEstimateDetailId() {
		return estimateDetailId;
	}

	public void setEstimateDetailId(Long estimateDetailId) {
		this.estimateDetailId = estimateDetailId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((estimateDetailId == null) ? 0 : estimateDetailId.hashCode());
		result = prime * result
				+ ((estimateId == null) ? 0 : estimateId.hashCode());
		return result;
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
		if (estimateDetailId == null) {
			if (other.estimateDetailId != null) {
				return false;
			}
		} else if (!estimateDetailId.equals(other.estimateDetailId)) {
			return false;
		}
		if (estimateId == null) {
			if (other.estimateId != null) {
				return false;
			}
		} else if (!estimateId.equals(other.estimateId)) {
			return false;
		}
		return true;
	}
	
}
