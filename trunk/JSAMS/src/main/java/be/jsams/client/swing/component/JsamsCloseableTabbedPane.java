package be.jsams.client.swing.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * An extension of {@link JTabbedPane} with a closing button and a icon that represents the business.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class JsamsCloseableTabbedPane extends JTabbedPane {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -5934631016465665535L;

    /**
     * Constructor.
     */
    public JsamsCloseableTabbedPane() {
        super();
    }

    /**
     * Adds a tab.
     * 
     * @param title
     *            the title table
     * @param iconFileName
     *            the icon file name
     * @param component
     *            the tab content panel
     */
    public void addTab(I18nString title, final String iconFileName, final Component component) {
        JPanel tab = new JPanel();
        tab.setOpaque(false);

        JsamsLabel tabTitle = null;
        ImageIcon tabIcon = null;
        if (iconFileName == null) {
            tabTitle = new JsamsLabel(title);
        } else {
            tabIcon = new ImageIcon(IconUtil.buildIcon(IconUtil.MENU_ICON_PREFIX + iconFileName));
            tabTitle = new JsamsLabel(title, tabIcon);
        }

        JsamsButton tabCloseButton = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "emblems/emblem-unreadable.png");
        Icon icon = tabCloseButton.getIcon();
        tabCloseButton.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        tabCloseButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                int closeTabNumber = indexOfComponent(component);
                removeTabAt(closeTabNumber);
            }
        });

        tab.add(tabTitle);
        tab.add(tabCloseButton);

        super.addTab(null, component);
        setTabComponentAt(getTabCount() - 1, tab);
        setSelectedComponent(component);
    }
}
