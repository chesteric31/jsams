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
import be.jsams.common.bean.view.sale.CommandBeanView;
import be.jsams.server.model.Command;
import be.jsams.server.model.CommandDetail;

/**
 * {@link AbstractIdentityBean} for {@link Command} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandBean extends AbstractIdentityBean<Command, CommandBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2213182386643300641L;

    private Date creationDate;
    private PeriodBean period;
    private String remark;
    private Double discountRate;
    private boolean transferred;

    private AgentBean agent;
    private CustomerBean customer;
    private AddressBean billingAddress;
    private AddressBean deliveryAddress;
    private List<CommandDetailBean> details;
    private SocietyBean society;

    public static final String CREATION_DATE_PROPERTY = "creationDate";
    public static final String REMARK_PROPERTY = "remark";
    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    /**
     * Default constructor.
     */
    public CommandBean() {
        super();
        setBillingAddress(new AddressBean());
        setDeliveryAddress(new AddressBean());
        SocietyBean society = JsamsDesktop.getInstance().getCurrentSociety();
        setAgent(new AgentBean(society));
        setCreationDate(new Date());
        setCustomer(new CustomerBean(society));
        setPeriod(new PeriodBean());
        setTransferred(false);
        List<CommandDetailBean> details = new ArrayList<CommandDetailBean>();
        CommandDetailBean detail = new CommandDetailBean();
        detail.setCommand(this);
        details.add(detail);
        setDetails(details);
    }

    /**
     * Constructor.
     * 
     * @param model the {@link Command}
     */
    public CommandBean(Command model) {
        super(model);
        setAgent(new AgentBean(model.getAgent()));
        setBillingAddress(new AddressBean(model.getBillingAddress()));
        setDeliveryAddress(new AddressBean(model.getDeliveryAddress()));
        setCreationDate(model.getCreationDate());
        setCustomer(new CustomerBean(model.getCustomer()));
        List<CommandDetailBean> beans = new ArrayList<CommandDetailBean>();
        for (CommandDetail detail : model.getDetails()) {
            beans.add(new CommandDetailBean(detail, this));
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
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        Date oldValue = this.creationDate;
        this.creationDate = creationDate;
        firePropertyChange(CREATION_DATE_PROPERTY, oldValue, this.creationDate);
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
     * @return the deliveryAddress
     */
    public AddressBean getDeliveryAddress() {
        return deliveryAddress;
    }

    /**
     * @param deliveryAddress the deliveryAddress to set
     */
    public void setDeliveryAddress(AddressBean deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    /**
     * @return the transferred
     */
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred the transferred to set
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
     * @param period the period to set
     */
    public void setPeriod(PeriodBean period) {
        this.period = period;
    }

    /**
     * @return the details
     */
    public List<CommandDetailBean> getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(List<CommandDetailBean> details) {
        this.details = details;
    }

    /**
     * @return the {@link SocietyBean}
     */
    public SocietyBean getSociety() {
        return society;
    }

    /**
     * @param society the {@link SocietyBean} to set
     */
    public void setSociety(SocietyBean society) {
        this.society = society;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandBeanView getView() {
        return new CommandBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        agent.clear();
        billingAddress.clear();
        deliveryAddress.clear();
        customer.clear();
        setCreationDate(null);
        for (CommandDetailBean detail : details) {
            detail.clear();
        }
        setDiscountRate(null);
        setListModel(null);
        setRemark(null);
        setSelection(null);
        setTransferred(false);
        period.clear();
        society.clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        CommandBean other = (CommandBean) bean;
        agent.refresh(other.getAgent());
        billingAddress.refresh(other.getBillingAddress());
        deliveryAddress.refresh(other.getDeliveryAddress());
        setCreationDate(other.getCreationDate());
        customer.refresh(other.getCustomer());
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setListModel(other.getListModel());
        setRemark(other.getRemark());
        setSelection(other.getSelection());
        setTransferred(other.isTransferred());
        society.refresh(other.getSociety());
    }

}
