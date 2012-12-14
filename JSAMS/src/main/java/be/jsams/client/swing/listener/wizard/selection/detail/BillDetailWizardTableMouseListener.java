package be.jsams.client.swing.listener.wizard.selection.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.sale.BillDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Bill detail wizard table.
 * 
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class BillDetailWizardTableMouseListener extends AbstractDetailWizardTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public BillDetailWizardTableMouseListener(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
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
        getBean().setBillDetails(map);
    }

}
