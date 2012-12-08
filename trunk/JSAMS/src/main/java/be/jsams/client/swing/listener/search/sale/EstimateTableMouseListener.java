package be.jsams.client.swing.listener.search.sale;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditEstimateDialog;
import be.jsams.client.model.table.sale.EstimateTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Estimate table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        EstimateTableModel model = (EstimateTableModel) table.getModel();
        new EditEstimateDialog(I18nResource.TITLE_EDIT_ESTIMATE, model.getRow(selectedRowModel));
    }

}
