package be.jsams.common.bean.view.sale;

import javax.swing.JPanel;

import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.view.AbstractView;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev: 689 $ $Date:: 2011-04-14 16:11#$ $Author: chesteric31 $
 */
public class DeliveryOrderBeanView extends AbstractView<DeliveryOrderBean, JPanel, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4995396899905110598L;

    /**
     * Constructor
     * 
     * @param bean the {@link DeliveryOrderBean}
     */
    public DeliveryOrderBeanView(DeliveryOrderBean bean) {
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
