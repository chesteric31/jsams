package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditCustomerDialog;
import be.jsams.client.model.panel.SearchAgentPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.server.model.Agent;

/**
 * {@link AbstractAction} to launch {@link SearchAgentPanel}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchAgentAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8266044738535674682L;

    private EditCustomerDialog editDialog;

    /**
     * Constructor.
     * 
     * @param editCustomerDialog the {@link EditCustomerDialog}
     */
    public SearchAgentAction(EditCustomerDialog editCustomerDialog) {
        editDialog = editCustomerDialog;
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        AgentTableMouseListener customListener = new AgentTableMouseListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                JsamsTable table = (JsamsTable) e.getSource();
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow > -1) {
                        Long id = (Long) table.getValueAt(selectedRow, 0);
                        Agent selectedAgent = JsamsApplicationContext.getAgentService().findById(id);
                        editDialog.getTextFieldAgent().setText(selectedAgent.getName());
                        table.getEventuallyJsamsDialog().setVisible(false);
                    }
                }
            }
        };
        SearchAgentPanel searchAgentPanel = new SearchAgentPanel(new Agent(), customListener, JsamsApplicationContext
                .getAgentService(), false);
        JsamsDialog dialog = new JsamsDialog(null, JsamsI18nResource.TITLE_SEARCH_AGENT, IconUtil.TITLE_ICON_PREFIX
                + "categories/applications-development.png");
        dialog.add(searchAgentPanel);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }

}
