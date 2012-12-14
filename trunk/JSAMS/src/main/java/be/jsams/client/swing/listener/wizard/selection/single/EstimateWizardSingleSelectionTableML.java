package be.jsams.client.swing.listener.wizard.selection.single;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.EstimateTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Estimate wizard single selection table.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EstimateWizardSingleSelectionTableML extends AbstractWizardSingleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public EstimateWizardSingleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSingleClick(JsamsTable table, int selectedRowModel) {
        EstimateTableModel model = (EstimateTableModel) table.getModel();
        List<EstimateBean> beans = new ArrayList<EstimateBean>();
        beans.add(model.getRow(selectedRowModel));
        getBean().setDocuments(beans);
    }

}
