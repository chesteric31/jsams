package be.jsams.common.bean.view;

import javax.swing.JPanel;

import be.jsams.common.bean.model.CreditNoteDetailBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteDetailBeanView extends AbstractView<CreditNoteDetailBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1337431096085343826L;

    /**
     * Constructor
     * 
     * @param bean the {@link CreditNoteDetailBean}
     */
    public CreditNoteDetailBeanView(CreditNoteDetailBean bean) {
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