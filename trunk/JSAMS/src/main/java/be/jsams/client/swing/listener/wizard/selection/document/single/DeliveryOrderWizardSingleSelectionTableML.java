package be.jsams.client.swing.listener.wizard.selection.document.single;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.DeliveryOrderTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Delivery order wizard single selection table.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DeliveryOrderWizardSingleSelectionTableML extends AbstractWizardSingleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public DeliveryOrderWizardSingleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int selectedRowModel) {
        DeliveryOrderTableModel model = (DeliveryOrderTableModel) table.getModel();
        List<DeliveryOrderBean> beans = new ArrayList<DeliveryOrderBean>();
        beans.add(model.getRow(selectedRowModel));
        getBean().setDocuments(beans);
    }

}
