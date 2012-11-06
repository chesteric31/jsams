package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;

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

    private static final Log LOGGER = LogFactory.getLog(DownloadUpdateAction.class);
    private final boolean info = LOGGER.isInfoEnabled();

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
        int confirm = JOptionPane.showConfirmDialog(null, JsamsI18nResource.CONFIRMATION_UPDATE);
        if (confirm == 0) {
            List<String> updateJars = JsamsApplicationContext.getDownloaderService().downloadUpdate();
            executeUpdates(updateJars);
            JsamsDesktop.getInstance().stopNow();
        }
    }

    /**
     * Executes the update jars.
     * 
     * TODO test if the installation of updater doesn't broke the current
     * program...
     * 
     * @param updateJars a list of update jars
     */
    private void executeUpdates(List<String> updateJars) {
        for (String updateJar : updateJars) {
            try {
                if (info) {
                    LOGGER.info("java -jar with:" + updateJar);
                }
                Process process = Runtime.getRuntime().exec("java -jar " + updateJar);
                process.waitFor();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
}
