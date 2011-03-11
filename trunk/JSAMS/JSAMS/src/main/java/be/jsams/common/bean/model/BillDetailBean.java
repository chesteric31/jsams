package be.jsams.common.bean.model;

import be.jsams.common.bean.view.BillDetailBeanView;
import be.jsams.server.model.BillDetail;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillDetailBean extends AbstractIdentityBean<BillDetail, BillDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4398604502381563804L;

    /**
     * Constructor.
     * 
     * @param model the {@link BillDetail}
     */
    public BillDetailBean(BillDetail model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BillDetailBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

}
