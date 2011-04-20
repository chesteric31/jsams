package be.jsams.client.swing.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditCommandDialog;
import be.jsams.client.model.table.CommandTableModel;
import be.jsams.client.swing.component.JsamsTable;

/**
 * Customized {@link MouseListener} for Command table double click.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandTableMouseListener implements MouseListener {

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
                CommandTableModel model = (CommandTableModel) table.getModel();
                new EditCommandDialog(JsamsI18nResource.TITLE_EDIT_COMMAND, model.getRow(selectedRowModel));
                table.updateUI();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
