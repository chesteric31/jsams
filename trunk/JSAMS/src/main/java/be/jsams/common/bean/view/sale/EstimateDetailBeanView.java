package be.jsams.common.bean.view.sale;

import javax.swing.JPanel;

import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.EstimateDetailBean;
import be.jsams.common.bean.view.AbstractView;

/**
 * Implementation of all sorts of views for {@link EstimateDetailBean} object.
 * 
 * @author chesteric31
 * @version $Rev: 689 $ $Date:: 2011-04-14 16:11#$ $Author: chesteric31 $
 */
public class EstimateDetailBeanView extends AbstractView<EstimateDetailBean, JsamsTable, JPanel> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 1156444686459091375L;

    /**
     * Constructor
     * 
     * @param bean
     *            the {@link EstimateDetailBean}
     */
    public EstimateDetailBeanView(EstimateDetailBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    public JsamsTable createEditView() {
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