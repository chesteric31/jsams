package be.jsams.client.swing.listener.wizard.selection.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.sale.CommandDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Command detail wizard table mouse listener.
 *
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class CommandDetailWizardTableMouseListener extends AbstractDetailWizardTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public CommandDetailWizardTableMouseListener(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
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
        getBean().setCommandDetails(map);
    }


}
