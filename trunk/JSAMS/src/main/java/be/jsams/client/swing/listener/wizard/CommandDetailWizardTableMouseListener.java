package be.jsams.client.swing.listener.wizard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.sale.CommandDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class CommandDetailWizardTableMouseListener implements MouseListener {

    private TransferBean bean;

    /**
     * Constructor
     * 
     * @param bean the {@link TransferBean}
     */
    public CommandDetailWizardTableMouseListener(TransferBean bean) {
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
                List<CommandDetailBean> beans = new ArrayList<CommandDetailBean>();
                Map<Long, List<CommandDetailBean>> map = new HashMap<Long, List<CommandDetailBean>>();
                for (int selectedRow : selectedRows) {
                    if (selectedRow > -1) {
                        int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                        CommandDetailWizardTableModel model = (CommandDetailWizardTableModel) table.getModel();
                        CommandDetailBean row = model.getRow(selectedRowModel);
                        beans.add(row);
                        Long id = row.getCommand().getId();
                        List<CommandDetailBean> list = map.get(id);
                        if (list == null) {
                            list = new ArrayList<CommandDetailBean>();
                        }
                        list.add(row);
                        map.put(id, list);
                    }
                }
                bean.setCommandDetails(map);
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
