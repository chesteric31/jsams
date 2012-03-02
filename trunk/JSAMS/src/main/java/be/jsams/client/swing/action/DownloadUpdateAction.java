package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.desktop.JsamsDesktop;

/**
 * Download update action to download an update from the net.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DownloadUpdateAction extends AbstractAction {

    /**
     * Serial
     */
    private static final long serialVersionUID = 4858347392507840243L;

    /**
     * Constructor.
     *  
     * @param text the action text
     * @param icon the icon to display
     */
    public DownloadUpdateAction(String text, Icon icon) {
        super(text, icon);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
//        JsamsApplicationContext.getDownloaderService().retrieveAvailableUpdateFile();
        JsamsDesktop.getInstance().stopNow();
    }

}
