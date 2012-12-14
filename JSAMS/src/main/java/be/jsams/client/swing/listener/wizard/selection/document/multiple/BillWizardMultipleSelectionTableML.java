package be.jsams.client.swing.listener.wizard.selection.document.multiple;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Bill wizard multiple selection table.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillWizardMultipleSelectionTableML extends AbstractWizardMultipleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public BillWizardMultipleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
        if (selectedRows != null && selectedRows.length > 0) {
            List<BillBean> beans = new ArrayList<BillBean>();
            for (int selectedRow : selectedRows) {
                if (selectedRow > -1) {
                    int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                    BillTableModel model = (BillTableModel) table.getModel();
                    beans.add(model.getRow(selectedRowModel));
                }
            }
            getBean().setDocuments(beans);
        }
    }

}
