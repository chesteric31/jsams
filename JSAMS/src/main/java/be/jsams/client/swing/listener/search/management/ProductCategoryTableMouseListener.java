package be.jsams.client.swing.listener.search.management;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.management.EditProductCategoryDialog;
import be.jsams.client.model.table.management.ProductCategoryTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Product category table double click.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductCategoryTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        ProductCategoryTableModel model = (ProductCategoryTableModel) table.getModel();
        new EditProductCategoryDialog(I18nResource.TITLE_EDIT_PRODUCT_CATEGORY, model
                .getRow(selectedRowModel));
    }

}
