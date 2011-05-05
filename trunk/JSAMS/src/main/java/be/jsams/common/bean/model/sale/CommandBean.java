package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.view.sale.CommandBeanView;
import be.jsams.server.model.sale.Command;
import be.jsams.server.model.sale.CommandDetail;

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
     */
    public CommandBean() {
        super();
        setBillingAddress(new AddressBean());
        setDeliveryAddress(new AddressBean());
        setAgent(new AgentBean(getSociety()));
        setTransferred(false);
        List<CommandDetailBean> details = new ArrayList<CommandDetailBean>();
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
        List<CommandDetailBean> beans = new ArrayList<CommandDetailBean>();
        for (CommandDetail detail : model.getDetails()) {
            beans.add(new CommandDetailBean(detail, this));
        }
        setDetails(beans);
        setDiscountRate(model.getDiscountRate());
        setTransferred(model.isTransferred());
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
        getCustomer().clear();
        setCreationDate(null);
        for (CommandDetailBean detail : details) {
            detail.clear();
        }
        setDiscountRate(null);
        setListModel(null);
        setRemark(null);
        setSelection(null);
        setTransferred(false);
        getPeriod().clear();
        if (getSociety() != null) {
            getSociety().clear();
        }
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
        getCustomer().refresh(other.getCustomer());
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
