package be.jsams.client.swing.listener.search.sale;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditCreditNoteDialog;
import be.jsams.client.model.table.sale.CreditNoteTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Credit note table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        CreditNoteTableModel model = (CreditNoteTableModel) table.getModel();
        new EditCreditNoteDialog(I18nResource.TITLE_EDIT_CREDIT_NOTE, model.getRow(selectedRowModel));
    }

}
