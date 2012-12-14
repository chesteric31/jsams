package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;

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
        int confirm = JOptionPane.showConfirmDialog(null, I18nResource.CONFIRMATION_UPDATE);
        if (confirm == 0) {
            List<String> updateJars = ApplicationContext.getDownloaderService().downloadUpdate();
            executeUpdates(updateJars);
            Desktop.getInstance().stopNow();
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
                LOGGER.error(e);
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                LOGGER.error(e);
                throw new RuntimeException(e);
            }
        }
    }
    
}
