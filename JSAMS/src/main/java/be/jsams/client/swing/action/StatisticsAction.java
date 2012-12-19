package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.panel.StatisticsPanel;
import be.jsams.client.swing.utils.IconResource;

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
     * Constructor.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     */
    public StatisticsAction(String text, Icon icon) {
        super(text, icon);
    }

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent e) {
        StatisticsPanel panel = new StatisticsPanel();
        Desktop.getInstance().getMainWindow().getTabbedPane()
                .addTab(I18nResource.TITLE_STATISTICS, IconResource.STATISTICS, panel);
    }

}
