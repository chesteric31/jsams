package be.jsams.common.bean.model.sale;

import java.util.ArrayList;
import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
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
    private Double totalEt;
    private Double totalVat;
    private Double totalAti;

    private AgentBean agent;
    private AddressBean billingAddress;
    private List<EstimateDetailBean> details;

    public static final String DISCOUNT_RATE_PROPERTY = "discountRate";
    public static final String TRANSFERRED_PROPERTY = "transferred";
    public static final String TOTAL_ET_PROPERTY = "totalEt";
    public static final String TOTAL_ATI_PROPERTY = "totalAti";
    public static final String TOTAL_VAT_PROPERTY = "totalVat";
    
    private EstimateMediator mediator;


    /**
     * Default constructor.
     * 
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param agent the {@link AgentBean}
     */
    public EstimateBean(SocietyBean society, CustomerBean customer, AgentBean agent) {
        super(society, customer);
        this.billingAddress = new AddressBean();
        this.agent = agent;
        this.transferred = false;
        List<EstimateDetailBean> details = new ArrayList<EstimateDetailBean>();
        this.details = details;
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param model the {@link Estimate}
     * @param society the {@link SocietyBean}
     * @param customer the {@link CustomerBean}
     * @param agent the {@link AgentBean}
     */
    public EstimateBean(Estimate model, SocietyBean society, CustomerBean customer, AgentBean agent) {
        super(model, society, customer);
        this.agent = agent;
        this.billingAddress = new AddressBean(model.getBillingAddress());
        List<EstimateDetailBean> beans = new ArrayList<EstimateDetailBean>();
        for (EstimateDetail detail : model.getDetails()) {
            beans.add(new EstimateDetailBean(detail, this));
        }
        this.details = beans;
        this.discountRate = model.getDiscountRate();
        this.transferred = model.isTransferred();
        setView(buildView());
    }
    
    /**
     * @return the mediator
     */
    public EstimateMediator getMediator() {
        return mediator;
    }

    /**
     * @param mediator the mediator to set
     */
    public void setMediator(EstimateMediator mediator) {
        this.mediator = mediator;
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
        mediator.updateTotals();
    }

    /**
     * @return the transferred
     */
    public boolean isTransferred() {
        return transferred;
    }

    /**
     * @param transferred the boolean to indicate that the estimate is
     *            transferred or not
     */
    public void setTransferred(boolean transferred) {
        boolean oldValue = this.transferred;
        this.transferred = transferred;
        firePropertyChange(TRANSFERRED_PROPERTY, oldValue, this.transferred);
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
        EstimateMediator estimateMediator = other.getMediator();
        List<EstimateDetailBean> list = other.getDetails();
        if (list != null && !list.isEmpty()) {
            for (EstimateDetailBean detailBean : list) {
                detailBean.setMediator(estimateMediator);
            }
        }
        details.addAll(list);
        setMediator(estimateMediator);
        setDiscountRate(other.getDiscountRate());
        setTransferred(other.isTransferred());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EstimateBeanView buildView() {
        return new EstimateBeanView(this);
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
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof EstimateBean)) {
            return false;
        }
        EstimateBean other = (EstimateBean) obj;
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
