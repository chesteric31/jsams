package be.jsams.server.model.sale.detail;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.sale.Estimate;

/**
 * Estimate detail (line) entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ESTIMATE_DETAIL")
public class EstimateDetail extends AbstractDetail {

    private boolean transferred;

    private Estimate estimate;

    /**
     * Constructor.
     */
    public EstimateDetail() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link EstimateDetailBean}
     * @param estimate the {@link Estimate} model
     */
    public EstimateDetail(final EstimateDetailBean bean, final Estimate estimate) {
        super(bean);
        this.estimate = estimate;
        this.transferred = bean.isTransferred();
    }

    /**
     * 
     * @return the {@link Estimate}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_ESTIMATE")
    public Estimate getEstimate() {
        return estimate;
    }

    /**
     * 
     * @param estimate the {@link Estimate} to set
     */
    public void setEstimate(Estimate estimate) {
        this.estimate = estimate;
    }

    /**
     * 
     * @return a boolean to indicate if the {@link EstimateDetail} is
     *         transferred to an other document
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred true if the {@link EstimateDetail} is transferred to
     *            an other document, false otherwise
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

}
