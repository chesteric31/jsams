package be.jsams.client.swing.listener.search.sale;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditDeliveryOrderDialog;
import be.jsams.client.model.table.DeliveryOrderTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Delivery Order table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        DeliveryOrderTableModel model = (DeliveryOrderTableModel) table.getModel();
        new EditDeliveryOrderDialog(JsamsI18nResource.TITLE_EDIT_DELIVERY_ORDER, model.getRow(selectedRowModel));
    }

}
