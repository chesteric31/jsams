package be.jsams.client.swing.listener.search.management;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditAgentDialog;
import be.jsams.client.model.table.AgentTableModel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.search.AbstractTableMouseListener;

/**
 * Customized Mouse Listener for Agent table double click.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentTableMouseListener extends AbstractTableMouseListener {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void handleDoubleClicking(JsamsTable table, int selectedRowModel) {
        AgentTableModel model = (AgentTableModel) table.getModel();
        new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, model.getRow(selectedRowModel));
    }

}
