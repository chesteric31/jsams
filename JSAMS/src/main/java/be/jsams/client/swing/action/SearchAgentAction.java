package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.SearchAgentPanel;
import be.jsams.client.swing.component.JsamsDialog;
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

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent arg0) {
        SearchAgentPanel searchAgentPanel = new SearchAgentPanel(new Agent(), new AgentTableMouseListener(),
                JsamsApplicationContext.getAgentService(), false);
        JsamsDialog dialog = new JsamsDialog(null, JsamsI18nResource.TITLE_SEARCH_AGENT,
                IconUtil.TITLE_ICON_PREFIX + "categories/applications-development.png");
        dialog.add(searchAgentPanel);
        dialog.pack();
        dialog.setVisible(true);
        dialog.setLocationRelativeTo(null);
    }

}
