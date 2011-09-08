package be.jsams.client.swing.listener.search;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditProductCategoryDialog;
import be.jsams.client.model.table.ProductCategoryTableModel;
import be.jsams.client.swing.component.JsamsTable;

/**
 * Customized {@link MouseListener} for Product category table double click.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductCategoryTableMouseListener implements MouseListener {

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                ProductCategoryTableModel model = (ProductCategoryTableModel) table.getModel();
                new EditProductCategoryDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT_CATEGORY, model
                        .getRow(selectedRowModel));
                table.repaint();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
