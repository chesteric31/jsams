package be.jsams.client.swing.listener.wizard.selection.document.multiple;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.DeliveryOrderTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Delivery order wizard multiple selection.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DeliveryOrderWizardMultipleSelectionTableML extends AbstractWizardMultipleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public DeliveryOrderWizardMultipleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int[] selectedRows) {
        if (selectedRows != null && selectedRows.length > 0) {
            List<DeliveryOrderBean> beans = new ArrayList<DeliveryOrderBean>();
            for (int selectedRow : selectedRows) {
                if (selectedRow > -1) {
                    int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                    DeliveryOrderTableModel model = (DeliveryOrderTableModel) table.getModel();
                    beans.add(model.getRow(selectedRowModel));
                }
            }
            getBean().setDocuments(beans);
        }
    }

}
