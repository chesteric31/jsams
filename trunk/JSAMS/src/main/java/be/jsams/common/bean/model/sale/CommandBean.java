package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.builder.ProductBeanBuilder;
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
    private Double totalEt;
    private Double totalVat;
    private Double totalAti;

    private AgentBean agent;
    private AddressBean billingAddress;
    private AddressBean deliveryAddress;
    private List<CommandDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";
    public static final String TOTAL_ET_PROPERTY = "totalEt";
    public static final String TOTAL_ATI_PROPERTY = "totalAti";
    public static final String TOTAL_VAT_PROPERTY = "totalVat";

    private CommandMediator mediator;

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
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param model the {@link Command}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param agent the {@link AgentBean}
     * @param productBeanBuilder the {@link ProductBeanBuilder}
     */
    public CommandBean(Command model, SocietyBean society, CustomerBean customer, AgentBean agent,
            ProductBeanBuilder productBeanBuilder) {
        super(model, society, customer, productBeanBuilder);
        this.agent = agent;
        this.billingAddress = new AddressBean(model.getBillingAddress());
        this.deliveryAddress = new AddressBean(model.getDeliveryAddress());
        List<CommandDetailBean> beans = new ArrayList<CommandDetailBean>();
        for (CommandDetail detail : model.getDetails()) {
            beans.add(new CommandDetailBean(detail, this, getProductBeanBuilder()));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.transferred = model.isTransferred();
        setView(buildView());
    }

    /**
     * @return the discountRate
     */
    public Double getDiscountRate() {
        return discountRate;
    }

    /**
     * @return the mediator
     */
    public CommandMediator getMediator() {
        return mediator;
    }

    /**
     * @param mediator the mediator to set
     */
    public void setMediator(CommandMediator mediator) {
        this.mediator = mediator;
    }

    /**
     * @param discountRate the discountRate to set
     */
    public void setDiscountRate(Double discountRate) {
        Double oldValue = this.discountRate;
        this.discountRate = discountRate;
        firePropertyChange(DISCOUNT_RATE_PROPERTY, oldValue, this.discountRate);
        mediator.updateTotals();
        mediator.updateDiscountRates();
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
        mediator.updateTotals();
    }

    /**
     * @return the totalEt
     */
    public Double getTotalEt() {
        return totalEt;
    }

    /**
     * @param totalEt the totalEt to set
     */
    public void setTotalEt(Double totalEt) {
        Double oldValue = this.totalEt;
        this.totalEt = totalEt;
        firePropertyChange(TOTAL_ET_PROPERTY, oldValue, this.totalEt);
    }

    /**
     * @return the totalAti
     */
    public Double getTotalAti() {
        return totalAti;
    }

    /**
     * @param totalAti the totalAti to set
     */
    public void setTotalAti(Double totalAti) {
        Double oldValue = this.totalAti;
        this.totalAti = totalAti;
        firePropertyChange(TOTAL_ATI_PROPERTY, oldValue, this.totalAti);
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
        CommandMediator commandMediator = other.getMediator();
        List<CommandDetailBean> list = other.getDetails();
        if (list != null && !list.isEmpty()) {
            for (CommandDetailBean detailBean : list) {
                detailBean.setMediator(commandMediator);
            }
        }
        details.addAll(list);
        setMediator(commandMediator);
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

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (agent == null) {
            result += 0;
        } else {
            result += agent.hashCode();
        }
        result = prime * result;
        if (billingAddress == null) {
            result += 0;
        } else {
            result += billingAddress.hashCode();
        }
        result = prime * result;
        if (deliveryAddress == null) {
            result += 0;
        } else {
            result += deliveryAddress.hashCode();
        }
        result = prime * result;
        if (details == null) {
            result += 0;
        } else {
            result += details.hashCode();
        }
        result = prime * result;
        if (discountRate == null) {
            result += 0;
        } else {
            result += discountRate.hashCode();
        }
        result = prime * result;
        if (transferred) {
            result += 1231;
        } else {
            result += 1237;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        // if (this == obj) {
        // return true;
        // }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof CommandBean)) {
            return false;
        }
        CommandBean other = (CommandBean) obj;
        if (agent == null) {
            if (other.agent != null) {
                return false;
            }
        } else if (!agent.equals(other.agent)) {
            return false;
        }
        if (billingAddress == null) {
            if (other.billingAddress != null) {
                return false;
            }
        } else if (!billingAddress.equals(other.billingAddress)) {
            return false;
        }
        if (deliveryAddress == null) {
            if (other.deliveryAddress != null) {
                return false;
            }
        } else if (!deliveryAddress.equals(other.deliveryAddress)) {
            return false;
        }
        if (details == null) {
            if (other.details != null) {
                return false;
            }
        } else if (!details.equals(other.details)) {
            return false;
        }
        if (discountRate == null) {
            if (other.discountRate != null) {
                return false;
            }
        } else if (!discountRate.equals(other.discountRate)) {
            return false;
        }
        if (transferred != other.transferred) {
            return false;
        }
        return true;
    }

    /**
     * @return the totalVat
     */
    public Double getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat the total VAT
     */
    public void setTotalVat(Double totalVat) {
        Double oldValue = this.totalVat;
        this.totalVat = totalVat;
        firePropertyChange(TOTAL_VAT_PROPERTY, oldValue, this.totalVat);
    }

}
