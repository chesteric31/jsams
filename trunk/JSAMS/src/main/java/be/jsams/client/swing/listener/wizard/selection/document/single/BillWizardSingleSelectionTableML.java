package be.jsams.client.swing.listener.wizard.selection.document.single;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.BillTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.BillBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Bill wizard single selection table.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class BillWizardSingleSelectionTableML extends AbstractWizardSingleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public BillWizardSingleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int selectedRowModel) {
        BillTableModel model = (BillTableModel) table.getModel();
        List<BillBean> beans = new ArrayList<BillBean>();
        beans.add(model.getRow(selectedRowModel));
        getBean().setDocuments(beans);
    }

}
