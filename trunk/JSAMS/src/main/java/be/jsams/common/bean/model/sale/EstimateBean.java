package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.PeriodBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.sale.EstimateBeanView;
import be.jsams.server.model.sale.Estimate;
import be.jsams.server.model.sale.EstimateDetail;

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
        setBillingAddress(new AddressBean());
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        setAgent(new AgentBean(society));
        setCreationDate(new Date());
        setCustomer(new CustomerBean(society));
        setPeriod(new PeriodBean());
        setTransferred(false);
        List<EstimateDetailBean> details = new ArrayList<EstimateDetailBean>();
//        EstimateDetailBean detail = new EstimateDetailBean();
//        detail.setEstimate(this);
//        details.add(detail);
        setDetails(details);
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Estimate}
     */
    public EstimateBean(Estimate model) {
        super(model);
        setAgent(new AgentBean(model.getAgent()));
        setBillingAddress(new AddressBean(model.getBillingAddress()));
        setCreationDate(model.getCreationDate());
        setCustomer(new CustomerBean(model.getCustomer()));
        List<EstimateDetailBean> beans = new ArrayList<EstimateDetailBean>();
        for (EstimateDetail detail : model.getDetails()) {
            beans.add(new EstimateDetailBean(detail, this));
        }
        setDetails(beans);
        setDiscountRate(model.getDiscountRate());
        setRemark(model.getRemark());
        setTransferred(model.isTransferred());
    }

    /**
     * @return the discountRate
     */
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate
     *            the discountRate to set
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
     * @param agent
     *            the agent to set
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
     * @param billingAddress
     *            the billingAddress to set
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
     * @param details
     *            the details to set
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
     * @param transferred
     *            the boolean to indicate that the estimate is transferred or not
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
    public EstimateBeanView getView() {
        return new EstimateBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        agent.clear();
        billingAddress.clear();
        getCustomer().clear();
        setCreationDate(null);
        for (EstimateDetailBean detail : details) {
            detail.clear();
        }
        setDiscountRate(null);
        setListModel(null);
        setRemark(null);
        setSelection(null);
        setTransferred(false);
        getPeriod().clear();
        // only not null if searching of estimate...
        if (getSociety() != null) {
            getSociety().clear();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        EstimateBean other = (EstimateBean) bean;
        agent.refresh(other.getAgent());
        billingAddress.refresh(other.getBillingAddress());
        setCreationDate(other.getCreationDate());
        getCustomer().refresh(other.getCustomer());
        details.clear();
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
        getSociety().refresh(other.getSociety());
        getPeriod().refresh(other.getPeriod());
    }

}