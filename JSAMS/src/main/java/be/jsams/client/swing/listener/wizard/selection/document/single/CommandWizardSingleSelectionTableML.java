package be.jsams.client.swing.listener.wizard.selection.document.single;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.model.table.sale.CommandTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Command wizard single selection table.
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class CommandWizardSingleSelectionTableML extends AbstractWizardSingleSelectionTableML {

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean}
     */
    public CommandWizardSingleSelectionTableML(TransferBean bean) {
        super(bean);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleSelection(JsamsTable table, int selectedRowModel) {
        CommandTableModel model = (CommandTableModel) table.getModel();
        List<CommandBean> beans = new ArrayList<CommandBean>();
        beans.add(model.getRow(selectedRowModel));
        getBean().setDocuments(beans);
    }

}
