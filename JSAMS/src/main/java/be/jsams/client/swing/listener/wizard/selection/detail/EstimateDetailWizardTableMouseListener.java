package be.jsams.client.swing.listener.wizard.selection.detail;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import be.jsams.client.model.table.sale.EstimateDetailWizardTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Estimate detail wizard table mouse listener.
 * 
 * @author chesteric31
 * @version $Rev: 861 $ $Date:: 2011-07-28 15:09#$ $Author: chesteric31 $
 */
public class EstimateDetailWizardTableMouseListener extends AbstractDetailWizardTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public EstimateDetailWizardTableMouseListener(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
        List<EstimateDetailBean> beans = new ArrayList<EstimateDetailBean>();
        Map<Long, List<EstimateDetailBean>> map = new HashMap<Long, List<EstimateDetailBean>>();
        for (int selectedRow : selectedRows) {
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                EstimateDetailWizardTableModel model = (EstimateDetailWizardTableModel) table.getModel();
                EstimateDetailBean row = model.getRow(selectedRowModel);
                beans.add(row);
                Long id = row.getEstimate().getId();
                List<EstimateDetailBean> list = map.get(id);
                if (list == null) {
                    list = new ArrayList<EstimateDetailBean>();
                }
                list.add(row);
                map.put(id, list);
            }
        }
        getBean().setEstimateDetails(map);
    }

}
