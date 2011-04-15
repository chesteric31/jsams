package be.jsams.client.desktop;

import javax.swing.JSeparator;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Specific menu builder for windows menu.
 *
 * @author chesteric31
 * @version $Rev: 692 $ $Date::                  $ $Author$
 */
public class JsamsWindowsMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu windowsMenu;
    private JsamsMenuItem closeWindowMI;
    private JsamsMenuItem closeAllWindowsMI;
    private JsamsMenuItem nextMI;
    private JsamsMenuItem previousMI;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        windowsMenu = new JsamsMenu(JsamsI18nResource.MENU_WINDOWS);
        closeWindowMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE_WINDOW, "emblems/emblem-unreadable.png");
        windowsMenu.add(closeWindowMI);
        closeAllWindowsMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CLOSE_ALL_WINDOWS);
        windowsMenu.add(closeAllWindowsMI);
        windowsMenu.add(new JSeparator());
        nextMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_NEXT, "actions/go-next.png");
        windowsMenu.add(nextMI);
        previousMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PREVIOUS, "actions/go-previous.png");
        windowsMenu.add(previousMI);
        return windowsMenu;
    }

}
