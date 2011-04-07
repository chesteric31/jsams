package be.jsams.common.bean.view;

import java.util.List;

import javax.swing.JPanel;

import be.jsams.client.model.table.EstimateDetailTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.EstimateDetailBean;

/**
 * Implementation of all sorts of views for {@link EstimateDetailBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
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
    @SuppressWarnings("unchecked")
    public JsamsTable createEditView() {
        ViewFactory<EstimateDetailBean> helper = new ViewFactory<EstimateDetailBean>();
        EstimateDetailBean bean = getBean();
        EstimateDetailTableModel tableModel = new EstimateDetailTableModel(
                (List<EstimateDetailBean>) bean.getListModel());
        JsamsTable table = helper.createBindingTableComponent(tableModel, false, false);
        return table;
    }

    /**
     * {@inheritDoc}
     */
    public JPanel createSearchView() {
        // TODO Auto-generated method stub
        return null;
    }

}
