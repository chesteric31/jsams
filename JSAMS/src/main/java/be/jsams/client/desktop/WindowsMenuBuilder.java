package be.jsams.client.desktop;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JSeparator;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.component.JsamsCloseableTabbedPane;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Specific menu builder for windows menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class WindowsMenuBuilder extends AbstractMenuBuilder {

    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        JsamsMenu windowsMenu = new JsamsMenu(I18nResource.MENU_WINDOWS);
        JsamsMenuItem closeWindowMI = new JsamsMenuItem(I18nResource.MENU_ITEM_CLOSE_WINDOW,
                "emblems/emblem-unreadable.png");
        closeWindowMI.setAction(closeWindowAction(closeWindowMI.getText(), closeWindowMI.getIcon()));
        windowsMenu.add(closeWindowMI);
        JsamsMenuItem closeAllWindowsMI = new JsamsMenuItem(I18nResource.MENU_ITEM_CLOSE_ALL_WINDOWS);
        closeAllWindowsMI.setAction(closeAllWindowsAction(closeAllWindowsMI.getText(), closeAllWindowsMI.getIcon()));
        windowsMenu.add(closeAllWindowsMI);
        windowsMenu.add(new JSeparator());
        JsamsMenuItem nextMI = new JsamsMenuItem(I18nResource.MENU_ITEM_NEXT, "actions/go-next.png");
        nextMI.setAction(nextAction(nextMI.getText(), nextMI.getIcon()));
        windowsMenu.add(nextMI);
        JsamsMenuItem previousMI = new JsamsMenuItem(I18nResource.MENU_ITEM_PREVIOUS, "actions/go-previous.png");
        previousMI.setAction(previousAction(previousMI.getText(), previousMI.getIcon()));
        windowsMenu.add(previousMI);
        return windowsMenu;
    }
    
    /**
     * {@link AbstractAction} for close the current selected tab.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to close the current selected tab
     */
    private Action closeWindowAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -42102511813270510L;

            public void actionPerformed(ActionEvent event) {
                JsamsCloseableTabbedPane tabbedPane = Desktop.getInstance().getMainWindow().getTabbedPane();
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex > -1) {
                    tabbedPane.remove(selectedIndex);
                }
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} for close all tabs.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to close all tabs.
     */
    private Action closeAllWindowsAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -6678049688631891662L;

            public void actionPerformed(ActionEvent event) {
                MainFrame mainFrame = Desktop.getInstance().getMainWindow();
                mainFrame.getTabbedPane().removeAll();
                mainFrame.enableTabbedPane(false);
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} to switch to the next tab.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to switch to the next tab.
     */
    private Action nextAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 8627300338079311297L;

            public void actionPerformed(ActionEvent event) {
                JsamsCloseableTabbedPane tabbedPane = Desktop.getInstance().getMainWindow().getTabbedPane();
                int tabCount = tabbedPane.getTabCount();
                if (tabCount > 1) {
                    int selectedIndex = tabbedPane.getSelectedIndex();
                    if (selectedIndex < tabCount - 1) {
                        tabbedPane.setSelectedIndex(selectedIndex + 1);
                    }
                }
            }
        };
        action.putValue(Action.NAME, text);
        action.putValue(Action.SMALL_ICON, icon);
        return action;
    }

    /**
     * {@link AbstractAction} to switch to the previous tab.
     * 
     * @param text the text to display
     * @param icon the {@link Icon} to display
     * @return an {@link Action} to switch to the previous tab.
     */
    private Action previousAction(String text, Icon icon) {
        AbstractAction action = new AbstractAction() {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -338592317588159864L;

            public void actionPerformed(ActionEvent event) {
                JsamsCloseableTabbedPane tabbedPane = Desktop.getInstance().getMainWindow().getTabbedPane();
                int tabCount = tabbedPane.getTabCount();
                if (tabCount > 1) {
                    int selectedIndex = tabbedPane.getSelectedIndex();
                    if (selectedIndex > 0) {
                        tabbedPane.setSelectedIndex(selectedIndex - 1);
                    }
                }
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
        throw new UnsupportedOperationException();
    }

}
