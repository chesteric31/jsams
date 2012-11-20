package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.panel.StatisticsPanel;

/**
 * {@link AbstractAction} to open {@link StatisticsPanel}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class StatisticsAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4077606886417639749L;

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        StatisticsPanel panel = new StatisticsPanel();
        JsamsDesktop.getInstance().getMainWindow().getTabbedPane()
                .addTab(JsamsI18nResource.TITLE_STATISTICS, "apps/accessories-calculator.png", panel);
    }

}
