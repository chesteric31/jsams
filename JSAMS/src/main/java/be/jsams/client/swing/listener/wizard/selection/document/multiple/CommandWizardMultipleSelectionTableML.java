package be.jsams.client.swing.listener.wizard.selection.document.multiple;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.CommandTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Command wizard multiple selection table.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandWizardMultipleSelectionTableML extends AbstractWizardMultipleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public CommandWizardMultipleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
        if (selectedRows != null && selectedRows.length > 0) {
            List<CommandBean> beans = new ArrayList<CommandBean>();
            for (int selectedRow : selectedRows) {
                if (selectedRow > -1) {
                    int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                    CommandTableModel model = (CommandTableModel) table.getModel();
                    beans.add(model.getRow(selectedRowModel));
                }
            }
            getBean().setDocuments(beans);
        }
    }

}
