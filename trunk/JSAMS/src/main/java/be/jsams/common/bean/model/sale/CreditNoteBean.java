package be.jsams.common.bean.model.sale;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.sale.CreditNoteBeanView;
import be.jsams.server.model.sale.CreditNote;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteBean extends AbstractIdentityBean<CreditNote, CreditNoteBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1611159056426861810L;

    /**
     * Constructor
     * 
     * @param model
     *            the {@link CreditNote}
     */
    public CreditNoteBean(CreditNote model) {
        super(model);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CreditNoteBeanView getView() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        // TODO Auto-generated method stub
        
    }


}