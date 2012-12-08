package be.jsams.client.swing.listener.search.sale;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditBillDialog;
import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Bill table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class BillTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        BillTableModel model = (BillTableModel) table.getModel();
        new EditBillDialog(I18nResource.TITLE_EDIT_BILL, model.getRow(selectedRowModel));
    }
    
}
