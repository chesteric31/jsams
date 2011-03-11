package be.jsams.common.bean.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import be.jsams.common.bean.view.EstimateBeanView;
import be.jsams.server.model.Estimate;

/**
 * 
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
    private String remark;
    private BigDecimal discountRate;

    private AgentBean agent;
    private CustomerBean customer;
    private AddressBean billingAddress;
    private List<EstimateDetailBean> details;

    public static final String CREATIONDATE_PROPERTY = "creationdate";
    public static final String REMARK_PROPERTY = "remark";
    public static final String DISCOUNTRATE_PROPERTY = "discountrate";

    /**
     * Default constructor
     */
    public EstimateBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Estimate}
     */
    public EstimateBean(Estimate model) {
        // TODO Auto-generated constructor stub
        super(model);
    }

    /**
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        Date oldValue = this.creationDate;
        this.creationDate = creationDate;
        firePropertyChange(CREATIONDATE_PROPERTY, oldValue, this.creationDate);
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        String oldValue = this.remark;
        this.remark = remark;
        firePropertyChange(REMARK_PROPERTY, oldValue, this.remark);
    }

    /**
     * @return the discountRate
     */
    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    /**
     * @param discountRate the discountRate to set
     */
    public void setDiscountRate(BigDecimal discountRate) {
        BigDecimal oldValue = this.discountRate;
        this.discountRate = discountRate;
        firePropertyChange(DISCOUNTRATE_PROPERTY, oldValue, this.discountRate);
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
     * @return the customer
     */
    public CustomerBean getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
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
     * {@inheritDoc}
     */
    @Override
    public EstimateBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

}
