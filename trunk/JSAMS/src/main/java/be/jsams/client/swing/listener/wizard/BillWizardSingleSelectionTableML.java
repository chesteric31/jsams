package be.jsams.client.swing.listener.wizard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.BillTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillWizardSingleSelectionTableML implements MouseListener {

    private TransferBean bean;

    /**
     * Constructor
     * 
     * @param bean the {@link TransferBean}
     */
    public BillWizardSingleSelectionTableML(TransferBean bean) {
        this.bean = bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 1) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                BillTableModel model = (BillTableModel) table.getModel();
                List<BillBean> beans = new ArrayList<BillBean>();
                beans.add(model.getRow(selectedRowModel));
                bean.setDocuments(beans);
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
