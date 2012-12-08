package be.jsams.client.desktop;

import java.awt.event.KeyEvent;

import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.action.ChooseSocietyAction;
import be.jsams.client.swing.action.EditSocietyAction;
import be.jsams.client.swing.action.ExitAction;
import be.jsams.client.swing.action.GeneralParametersAction;
import be.jsams.client.swing.action.RestartAction;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Specific menu builder for file menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class FileMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu fileMenu;
    private JsamsMenuItem newMI;
    private JsamsMenuItem openMI;
    private JsamsMenuItem closeMI;
    private JsamsMenuItem societyParametersMI;
    private JsamsMenuItem generalParametersMI;
    private JsamsMenuItem exitMI;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        fileMenu = new JsamsMenu(I18nResource.MENU_FILE);
        newMI = new JsamsMenuItem(I18nResource.MENU_ITEM_NEW, "actions/folder-new.png");
        newMI.setAction(new EditSocietyAction(newMI.getText(), newMI.getIcon(), EditSocietyAction.NEW_ONE_MODE));
        newMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
        fileMenu.add(newMI);
        openMI = new JsamsMenuItem(I18nResource.MENU_ITEM_OPEN, "actions/document-open.png");
        openMI.setAction(new ChooseSocietyAction(openMI.getText(), openMI.getIcon()));
        openMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_MASK));
        fileMenu.add(openMI);
        closeMI = new JsamsMenuItem(I18nResource.MENU_ITEM_CLOSE, "status/folder-visiting.png");
        closeMI.setAction(new RestartAction(closeMI.getText(), closeMI.getIcon()));
        fileMenu.add(closeMI);
        fileMenu.add(new JSeparator());
        societyParametersMI = new JsamsMenuItem(I18nResource.MENU_ITEM_SOCIETY_PARAMETERS,
                "actions/document-properties.png");
        societyParametersMI.setAction(new EditSocietyAction(societyParametersMI.getText(), societyParametersMI
                .getIcon(), EditSocietyAction.CURRENT_SOCIETY_MODE));
        fileMenu.add(societyParametersMI);
        generalParametersMI = new JsamsMenuItem(I18nResource.MENU_ITEM_GENERAL_PARAMETERS,
                "actions/system-shutdown.png");
        generalParametersMI.setAction(new GeneralParametersAction(generalParametersMI.getText(), generalParametersMI
                .getIcon()));
        fileMenu.add(generalParametersMI);
        fileMenu.add(new JSeparator());
        exitMI = new JsamsMenuItem(I18nResource.MENU_ITEM_EXIT_APPLICATION, "actions/system-log-out.png");
        exitMI.setAction(new ExitAction(exitMI.getText(), exitMI.getIcon()));
        fileMenu.add(exitMI);
        
        enableMenuItems(false);
        
        return fileMenu;
    }
    
    /**
     * @return the societyParametersMI
     */
    public JsamsMenuItem getSocietyParametersMI() {
        return societyParametersMI;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMenuItems(boolean value) {
        closeMI.setEnabled(value);
        societyParametersMI.setEnabled(value);
    }

}
