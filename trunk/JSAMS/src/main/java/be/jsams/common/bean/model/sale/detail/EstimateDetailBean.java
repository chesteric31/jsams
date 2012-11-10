package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.EstimateMediator;
import be.jsams.common.bean.view.sale.detail.EstimateDetailBeanView;
import be.jsams.server.model.sale.detail.EstimateDetail;

/**
 * {@link AbstractIdentityBean} for {@link EstimateDetail} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateDetailBean extends AbstractDetailBean<EstimateDetail, EstimateDetailBeanView, EstimateBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4401359166302949409L;

    private boolean transferred;

    private EstimateBean estimate;

    public static final String TRANSFERRED_PROPERTY = "transferred";
    private EstimateMediator mediator;

    /**
     * Default constructor.
     */
    public EstimateDetailBean() {
        super();
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param model the {@link EstimateDetail}
     * @param estimate the {@link EstimateBean}
     */
    public EstimateDetailBean(EstimateDetail model, EstimateBean estimate) {
        super(model, estimate);
        this.estimate = estimate;
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
     * @return the estimate
     */
    public EstimateBean getEstimate() {
        return estimate;
    }

    /**
     * @param estimate the estimate to set
     */
    public void setEstimate(EstimateBean estimate) {
        this.estimate = estimate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        setTransferred(false);
        setEstimate(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        EstimateDetailBean other = (EstimateDetailBean) bean;
        estimate.refresh(other.getEstimate());
        setTransferred(other.isTransferred());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EstimateDetailBeanView buildView() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
        mediator.updateTotals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPrice(Double price) {
        super.setPrice(price);
        mediator.updateTotals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVatApplicable(Double vatApplicable) {
        super.setVatApplicable(vatApplicable);
        mediator.updateTotals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDiscountRate(Double discountRate) {
        super.setDiscountRate(discountRate);
        mediator.updateTotals();
    }

}
