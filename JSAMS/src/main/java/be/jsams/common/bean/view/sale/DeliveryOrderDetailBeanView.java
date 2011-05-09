package be.jsams.common.bean.view.sale;

import javax.swing.JPanel;

import be.jsams.common.bean.model.sale.DeliveryOrderDetailBean;
import be.jsams.common.bean.view.AbstractBeanView;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderDetailBeanView extends AbstractBeanView<DeliveryOrderDetailBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 3509559416961795771L;

    /**
     * Constructor
     * 
     * @param bean the {@link DeliveryOrderDetailBean}
     */
    public DeliveryOrderDetailBeanView(DeliveryOrderDetailBean bean) {
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
