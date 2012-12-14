package be.jsams.client.swing.listener.wizard.selection.document.multiple;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.EstimateTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Estimate wizard multiple selection table.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateWizardMultipleSelectionTableML extends AbstractWizardMultipleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public EstimateWizardMultipleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
        if (selectedRows != null && selectedRows.length > 0) {
            List<EstimateBean> beans = new ArrayList<EstimateBean>();
            for (int selectedRow : selectedRows) {
                if (selectedRow > -1) {
                    int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                    EstimateTableModel model = (EstimateTableModel) table.getModel();
                    beans.add(model.getRow(selectedRowModel));
                }
            }
            getBean().setDocuments(beans);
        }
    }

}
