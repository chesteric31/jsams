package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.common.bean.model.CreditNoteBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteBeanView extends AbstractView<CreditNoteBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1286822483256587871L;

    /**
     * Constructor
     * 
     * @param bean the {@link CreditNoteBean}
     */
    public CreditNoteBeanView(CreditNoteBean bean) {
        super(bean);
        // TODO Auto-generated constructor stub
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createEditView() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
