package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.view.EstimateBeanView;
import be.jsams.server.model.Estimate;
import be.jsams.server.model.EstimateDetail;

/**
 * The {@link AbstractIdentityBean} for {@link Estimate} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateBean extends AbstractIdentityBean<Estimate, EstimateBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4335706521574884643L;

    private Date creationDate;
    private PeriodBean period;
    private String remark;
    private Double discountRate;

    private AgentBean agent;
    private CustomerBean customer;
    private AddressBean billingAddress;
    private List<EstimateDetailBean> details;
    private boolean transferred;

    public static final String CREATION_DATE_PROPERTY = "creationDate";
    public static final String REMARK_PROPERTY = "remark";
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
        EstimateDetailBean detail = new EstimateDetailBean();
        detail.setEstimate(this);
        details.add(detail);
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
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
//        DateBean oldValue = this.creationDate;
//        this.creationDate = creationDate;
//        firePropertyChange(CREATION_DATE_PROPERTY, oldValue, this.creationDate);
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        String oldValue = this.remark;
        this.remark = remark;
        firePropertyChange(REMARK_PROPERTY, oldValue, this.remark);
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
     * @return the customer
     */
    public CustomerBean getCustomer() {
        return customer;
    }

    /**
     * @param customer
     *            the customer to set
     */
    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
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
     * @return the period
     */
    public PeriodBean getPeriod() {
        return period;
    }

    /**
     * @param period
     *            the period to set
     */
    public void setPeriod(PeriodBean period) {
        this.period = period;
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
        // TODO Auto-generated method stub

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
        customer.refresh(other.getCustomer());
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
    }

}
