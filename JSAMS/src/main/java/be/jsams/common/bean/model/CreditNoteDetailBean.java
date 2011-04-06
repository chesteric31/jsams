package be.jsams.common.bean.model;

import be.jsams.common.bean.view.CreditNoteDetailBeanView;
import be.jsams.server.model.CreditNoteDetail;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetailBean extends AbstractIdentityBean<CreditNoteDetail, CreditNoteDetailBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2345486313955221349L;

    /**
     * Constructor
     * 
     * @param model
     *            the {@link CreditNoteDetail}
     */
    public CreditNoteDetailBean(CreditNoteDetail model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteDetailBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

}
