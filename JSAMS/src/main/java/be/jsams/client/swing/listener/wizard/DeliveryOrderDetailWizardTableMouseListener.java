package be.jsams.client.swing.listener.wizard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.DeliveryOrderDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class DeliveryOrderDetailWizardTableMouseListener implements MouseListener {

    private TransferBean bean;

    /**
     * Constructor
     * 
     * @param bean the {@link TransferBean}
     */
    public DeliveryOrderDetailWizardTableMouseListener(TransferBean bean) {
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
                List<DeliveryOrderDetailBean> beans = new ArrayList<DeliveryOrderDetailBean>();
                Map<Long, List<DeliveryOrderDetailBean>> map = new HashMap<Long, List<DeliveryOrderDetailBean>>();
                for (int selectedRow : selectedRows) {
                    if (selectedRow > -1) {
                        int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                        DeliveryOrderDetailWizardTableModel model = (DeliveryOrderDetailWizardTableModel) table
                                .getModel();
                        DeliveryOrderDetailBean row = model.getRow(selectedRowModel);
                        beans.add(row);
                        Long id = row.getId();
                        List<DeliveryOrderDetailBean> list = map.get(id);
                        if (list == null) {
                            list = new ArrayList<DeliveryOrderDetailBean>();
                        }
                        list.add(row);
                        map.put(id, list);
                    }
                }
                bean.setDeliveryOrderDetails(map);
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
