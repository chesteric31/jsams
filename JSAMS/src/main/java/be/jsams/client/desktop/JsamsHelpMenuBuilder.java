package be.jsams.client.desktop;

import javax.swing.JSeparator;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Specific menu builder for help menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsHelpMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu helpMenu;
    private JsamsMenuItem helpMI;
    private JsamsMenuItem aboutMI;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
        helpMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP, "apps/help-browser.png");
        helpMenu.add(helpMI);
        helpMenu.add(new JSeparator());
        aboutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT, "categories/applications-office.png");
        helpMenu.add(aboutMI);
        return helpMenu;
    }

}
