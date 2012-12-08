package be.jsams.client.swing.listener.search.management;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.management.EditCustomerDialog;
import be.jsams.client.model.table.management.CustomerTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Customer table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        CustomerTableModel model = (CustomerTableModel) table.getModel();
        new EditCustomerDialog(I18nResource.TITLE_EDIT_CUSTOMER, model.getRow(selectedRowModel));
    }

}
