package be.jsams.client.swing.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditAgentDialog;
import be.jsams.client.model.panel.SearchAgentPanel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.server.model.Agent;

/**
 * Customized {@link MouseListener} for Agent table double click.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentTableMouseListener implements MouseListener {

    /**
     * {@inheritDoc}
     */
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 2) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                Long id = (Long) table.getValueAt(selectedRow, 0);
                Agent selectedAgent = JsamsApplicationContext.getAgentService().findById(id);
                new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, selectedAgent);
                SearchAgentPanel searchPanel = (SearchAgentPanel) table.getEventuallySearchPanel();
                searchPanel.refresh();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void mousePressed(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub

    }

}
