package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.view.sale.EstimateBeanView;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.model.sale.detail.EstimateDetail;

/**
 * {@link AbstractDocumentBean} for {@link Estimate} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateBean extends AbstractDocumentBean<Estimate, EstimateBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4335706521574884643L;

    private Double discountRate;
    private boolean transferred;

    private AgentBean agent;
    private AddressBean billingAddress;
    private List<EstimateDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";
    
    /**
     * Default constructor
     */
    public EstimateBean() {
        super();
        this.billingAddress = new AddressBean();
        this.agent = new AgentBean(getSociety());
        this.transferred = false;
        List<EstimateDetailBean> details = new ArrayList<EstimateDetailBean>();
        this.details = details;
        setView(new EstimateBeanView(this));
    }

    /**
     * Constructor
     * 
     * @param model the {@link Estimate}
     */
    public EstimateBean(Estimate model) {
        super(model);
        this.agent = new AgentBean(model.getAgent(), getSociety());
        this.billingAddress = new AddressBean(model.getBillingAddress());
        List<EstimateDetailBean> beans = new ArrayList<EstimateDetailBean>();
        for (EstimateDetail detail : model.getDetails()) {
            beans.add(new EstimateDetailBean(detail, this));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.transferred = model.isTransferred();
        setView(new EstimateBeanView(this));
    }

    /**
     * @return the discountRate
     */
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate the discountRate to set
     */
    public void setDiscountRate(Double discountRate) {
        Double oldValue = this.discountRate;
        this.discountRate = discountRate;
        firePropertyChange(DISCOUNT_RATE_PROPERTY, oldValue, this.discountRate);
    }

    /**
     * @return the agent
     */
    public AgentBean getAgent() {
        return agent;
    }

    /**
     * @param agent the agent to set
     */
    public void setAgent(AgentBean agent) {
        this.agent = agent;
    }

    /**
     * @return the billingAddress
     */
    public AddressBean getBillingAddress() {
        return billingAddress;
    }

    /**
     * @param billingAddress the billingAddress to set
     */
    public void setBillingAddress(AddressBean billingAddress) {
        this.billingAddress = billingAddress;
    }

    /**
     * @return the details
     */
    public List<EstimateDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<EstimateDetailBean> details) {
        this.details = details;
    }

    /**
     * @return the transferred
     */
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * 
     * @param transferred the boolean to indicate that the estimate is
     *            transferred or not
     */
    public void setTransferred(boolean transferred) {
        boolean oldValue = this.transferred;
        this.transferred = transferred;
        firePropertyChange(TRANSFERRED_PROPERTY, oldValue, this.transferred);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        agent.clear();
        billingAddress.clear();
        setDiscountRate(null);
        setTransferred(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        EstimateBean other = (EstimateBean) bean;
        agent.refresh(other.getAgent());
        billingAddress.refresh(other.getBillingAddress());
        details.clear();
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setTransferred(other.isTransferred());
    }

}
