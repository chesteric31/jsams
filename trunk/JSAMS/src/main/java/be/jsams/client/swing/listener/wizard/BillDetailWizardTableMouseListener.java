package be.jsams.client.swing.listener.wizard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.BillDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class BillDetailWizardTableMouseListener implements MouseListener {

    private TransferBean bean;

    /**
     * Constructor
     * 
     * @param bean the {@link TransferBean}
     */
    public BillDetailWizardTableMouseListener(TransferBean bean) {
        this.bean = bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 1) {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows != null && selectedRows.length > 0) {
                List<BillDetailBean> beans = new ArrayList<BillDetailBean>();
                Map<Long, List<BillDetailBean>> map = new HashMap<Long, List<BillDetailBean>>();
                for (int selectedRow : selectedRows) {
                    if (selectedRow > -1) {
                        int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                        BillDetailWizardTableModel model = (BillDetailWizardTableModel) table.getModel();
                        BillDetailBean row = model.getRow(selectedRowModel);
                        beans.add(row);
                        Long id = row.getId();
                        List<BillDetailBean> list = map.get(id);
                        if (list == null) {
                            list = new ArrayList<BillDetailBean>();
                        }
                        list.add(row);
                        map.put(id, list);
                    }
                }
                bean.setBillDetails(map);
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
