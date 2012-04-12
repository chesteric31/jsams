package be.jsams.client.swing.listener.search;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditCustomerDialog;
import be.jsams.client.model.table.CustomerTableModel;
import be.jsams.client.swing.component.JsamsTable;

/**
 * Customized {@link MouseListener} for Customer table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerTableMouseListener implements MouseListener {

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                CustomerTableModel model = (CustomerTableModel) table.getModel();
                new EditCustomerDialog(JsamsI18nResource.TITLE_EDIT_CUSTOMER, model.getRow(selectedRowModel));
                table.repaint();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mousePressed(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(MouseEvent e) {
    }

}
