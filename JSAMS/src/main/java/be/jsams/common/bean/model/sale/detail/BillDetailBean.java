package be.jsams.common.bean.model.sale.detail;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.view.sale.detail.BillDetailBeanView;
import be.jsams.server.model.sale.detail.BillDetail;

/**
 * {@link AbstractDetailBean} for {@link BillDetail} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillDetailBean extends AbstractDetailBean<BillDetail, BillDetailBeanView, BillBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4398604502381563804L;

    private boolean transferred;

    private BillBean bill;

    public static final String TRANSFERRED_PROPERTY = "transferred";

//    private ObservableList<BillDetailBean> list = new ArrayListModel<BillDetailBean>();

    /**
     * Default constructor
     */
    public BillDetailBean() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param model the {@link BillDetail}
     * @param bill the {@link BillBean}  
     */
    public BillDetailBean(BillDetail model, BillBean bill) {
        super(model, bill);
        setBill(bill);
        setTransferred(model.isTransferred());
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
     * @return the bill
     */
    public BillBean getBill() {
        return bill;
    }

    /**
     * @param bill the bill to set
     */
    public void setBill(BillBean bill) {
        this.bill = bill;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        super.clear();
        setTransferred(false);
        setBill(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        super.refresh(bean);
        BillDetailBean other = (BillDetailBean) bean;
        bill.refresh(other.getBill());
        setTransferred(other.isTransferred());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BillDetailBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

}
