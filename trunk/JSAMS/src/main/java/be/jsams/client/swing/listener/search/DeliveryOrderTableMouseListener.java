package be.jsams.client.swing.listener.search;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditDeliveryOrderDialog;
import be.jsams.client.model.table.DeliveryOrderTableModel;
import be.jsams.client.swing.component.JsamsTable;

/**
 * Customized {@link MouseListener} for Delivery Order table double click.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderTableMouseListener implements MouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                DeliveryOrderTableModel model = (DeliveryOrderTableModel) table.getModel();
                new EditDeliveryOrderDialog(JsamsI18nResource.TITLE_EDIT_DELIVERY_ORDER,
                        model.getRow(selectedRowModel));
                table.repaint();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

}
