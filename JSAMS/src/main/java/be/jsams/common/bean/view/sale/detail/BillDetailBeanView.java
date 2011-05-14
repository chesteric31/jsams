package be.jsams.common.bean.view.sale.detail;

import javax.swing.JPanel;

import be.jsams.common.bean.model.sale.detail.BillDetailBean;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillDetailBeanView extends AbstractDetailBeanView<BillDetailBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6480141753894999568L;

    /**
     * Constructor
     * 
     * @param bean the {@link BillDetailBean}
     */
    public BillDetailBeanView(BillDetailBean bean) {
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
