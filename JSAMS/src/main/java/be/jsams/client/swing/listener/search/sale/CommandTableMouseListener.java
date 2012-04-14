package be.jsams.client.swing.listener.search.sale;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditCommandDialog;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Command table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        CommandTableModel model = (CommandTableModel) table.getModel();
        new EditCommandDialog(JsamsI18nResource.TITLE_EDIT_COMMAND, model.getRow(selectedRowModel));
    }

}
