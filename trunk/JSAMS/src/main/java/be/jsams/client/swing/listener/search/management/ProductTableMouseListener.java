package be.jsams.client.swing.listener.search.management;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.management.EditProductDialog;
import be.jsams.client.model.table.management.ProductTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Product table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ProductTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        ProductTableModel model = (ProductTableModel) table.getModel();
        new EditProductDialog(I18nResource.TITLE_EDIT_PRODUCT, model.getRow(selectedRowModel));
    }

}
