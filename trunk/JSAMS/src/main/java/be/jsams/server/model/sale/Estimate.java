package be.jsams.server.model.sale;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.server.model.Address;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.sale.detail.EstimateDetail;

/**
 * Estimate entity object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@Entity
@Table(name = "ESTIMATE")
public class Estimate extends AbstractDocument {

    private Double discountRate;
    private boolean transferred;

    private Agent agent;
    private Address billingAddress;

    private List<EstimateDetail> details;

    /**
     * Constructor.
     */
    public Estimate() {
        super();
    }

    /**
     * Constructor
     * 
     * @param bean the {@link EstimateBean}
     */
    public Estimate(EstimateBean bean) {
        super(bean);
        this.agent = new Agent(bean.getAgent());
        this.billingAddress = new Address(bean.getBillingAddress());
        this.discountRate = bean.getDiscountRate();
        this.transferred = bean.isTransferred();
        List<EstimateDetailBean> list = bean.getDetails();
        List<EstimateDetail> tmp = new ArrayList<EstimateDetail>();
        if (list != null) {
            for (EstimateDetailBean detail : list) {
                tmp.add(new EstimateDetail(detail, this));
            }
        }
        this.details = tmp;
    }

    /**
     * 
     * @return the discount rate
     */
    @Column(name = "DISCOUNT_RATE")
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * 
     * @param discountRate the discount rate to set
     */
    public void setDiscountRate(Double discountRate) {
        this.discountRate = discountRate;
    }

    /**
     * 
     * @return the {@link Agent}
     */
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "FK_AGENT")
    public Agent getAgent() {
        return agent;
    }

    /**
     * 
     * @param agent the {@link Agent} to set
     */
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    /**
     * 
     * @return the billing {@link Address}
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_BILLING")
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * 
     * @param billingAddress the billing {@link Address} to set
     */
    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * 
     * @return a list of {@link EstimateDetail}
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "estimate")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    public List<EstimateDetail> getDetails() {
        return details;
    }

    /**
     * 
     * @param details a list of {@link EstimateDetail} to set
     */
    public void setDetails(List<EstimateDetail> details) {
        this.details = details;
    }

    /**
     * @return the transferred
     */
    @Column(name = "TRANSFERRED")
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred the transferred to set
     */
    public void setTransferred(boolean transferred) {
        this.transferred = transferred;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(super.toString());
        builder.append("Estimate [agent=");
        builder.append(agent);
        builder.append(", billingAddress=");
        builder.append(billingAddress);
        builder.append(", discountRate=");
        builder.append(discountRate);
        builder.append(", transferred=");
        builder.append(transferred);
        builder.append("]");
        return builder.toString();
    }

}
