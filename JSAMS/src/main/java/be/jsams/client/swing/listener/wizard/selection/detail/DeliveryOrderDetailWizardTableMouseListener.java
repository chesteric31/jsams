package be.jsams.client.swing.listener.wizard.selection.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.sale.DeliveryOrderDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Delivery order detail wizard table mouse listener.
 * 
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class DeliveryOrderDetailWizardTableMouseListener extends AbstractDetailWizardTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public DeliveryOrderDetailWizardTableMouseListener(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
        List<DeliveryOrderDetailBean> beans = new ArrayList<DeliveryOrderDetailBean>();
        Map<Long, List<DeliveryOrderDetailBean>> map = new HashMap<Long, List<DeliveryOrderDetailBean>>();
        for (int selectedRow : selectedRows) {
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                DeliveryOrderDetailWizardTableModel model = (DeliveryOrderDetailWizardTableModel) table.getModel();
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
        getBean().setDeliveryOrderDetails(map);
    }

}
