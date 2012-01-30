package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.AboutDialog;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Specific menu builder for help menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsHelpMenuBuilder extends AbstractMenuBuilder {

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        JsamsMenu helpMenu = new JsamsMenu(JsamsI18nResource.MENU_HELP);
        JsamsMenuItem helpMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_HELP, "apps/help-browser.png");
        helpMenu.add(helpMI);
        helpMenu.add(new JSeparator());
        JsamsMenuItem aboutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_ABOUT,
                "categories/applications-office.png");
        aboutMI.setAction(aboutAction(aboutMI.getText(), aboutMI.getIcon()));
        helpMenu.add(aboutMI);
        return helpMenu;
    }

    /**
     * {@link AbstractAction} to open the about dialog.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to open the about dialog
     */
    private Action aboutAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 6621546191772229680L;

            public void actionPerformed(ActionEvent event) {
                new AboutDialog(JsamsDesktop.getInstance().getMainWindow(), JsamsI18nResource.TITLE_ABOUT);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enableMenuItems(boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
