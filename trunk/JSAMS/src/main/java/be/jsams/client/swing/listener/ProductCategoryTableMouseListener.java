package be.jsams.client.swing.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditProductCategoryDialog;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.server.model.ProductCategory;

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
                Long id = (Long) table.getValueAt(selectedRow, 0);
                ProductCategory selectedProductCategory = JsamsApplicationContext.getProductCategoryService().findById(
                        id);
                new EditProductCategoryDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT_CATEGORY, selectedProductCategory);
              //TODO perform ok onto table for refresh
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
