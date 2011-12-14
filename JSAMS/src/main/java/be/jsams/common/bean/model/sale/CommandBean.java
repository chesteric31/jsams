package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.view.sale.CommandBeanView;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.detail.CommandDetail;

/**
 * {@link AbstractDocumentBean} for {@link Command} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandBean extends AbstractDocumentBean<Command, CommandBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2213182386643300641L;

    private Double discountRate;
    private boolean transferred;

    private AgentBean agent;
    private AddressBean billingAddress;
    private AddressBean deliveryAddress;
    private List<CommandDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";

    /**
     * Default constructor.
     * 
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param agent the {@link AgentBean}
     */
    public CommandBean(SocietyBean society, CustomerBean customer, AgentBean agent) {
        super(society, customer);
        this.billingAddress = new AddressBean();
        this.deliveryAddress = new AddressBean();
        this.agent = agent;
        this.transferred = false;
        List<CommandDetailBean> details = new ArrayList<CommandDetailBean>();
        this.details = details;
        setView(new CommandBeanView(this));
    }

    /**
     * Constructor.
     * 
     * @param model the {@link Command}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param agent the {@link AgentBean}
     */
    public CommandBean(Command model, SocietyBean society, CustomerBean customer, AgentBean agent) {
        super(model, society, customer);
        this.agent = agent;
        this.billingAddress = new AddressBean(model.getBillingAddress());
        this.deliveryAddress = new AddressBean(model.getDeliveryAddress());
        List<CommandDetailBean> beans = new ArrayList<CommandDetailBean>();
        for (CommandDetail detail : model.getDetails()) {
            beans.add(new CommandDetailBean(detail, this));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.transferred = model.isTransferred();
        setView(new CommandBeanView(this));
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
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        agent.clear();
        billingAddress.clear();
        deliveryAddress.clear();
        setDiscountRate(null);
        setTransferred(false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        CommandBean other = (CommandBean) bean;
        agent.refresh(other.getAgent());
        billingAddress.refresh(other.getBillingAddress());
        deliveryAddress.refresh(other.getDeliveryAddress());
        details.clear();
        details.addAll(other.getDetails());
        setDiscountRate(other.getDiscountRate());
        setTransferred(other.isTransferred());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandBeanView buildView() {
        return new CommandBeanView(this);
    }

}
