package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.EstimateBean;
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

    // private ObservableList<EstimateDetailBean> list = new
    // ArrayListModel<EstimateDetailBean>();

    /**
     * Default constructor
     */
    public EstimateDetailBean() {
        super();
    }

    /**
     * Constructor
     * 
     * @param model the {@link EstimateDetail}
     * @param estimate the {@link EstimateBean}
     */
    public EstimateDetailBean(EstimateDetail model, EstimateBean estimate) {
        super(model, estimate);
        this.estimate = estimate;
        this.transferred = model.isTransferred();
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
        // TODO Auto-generated method stub
        return null;
    }

}
