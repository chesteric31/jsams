package be.jsams.client.swing.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public JsamsCloseableTabbedPane() {
        super();
    }

    public void addTab(I18nString title, final String iconFileName, final Component component) {
        JPanel tab = new JPanel();
        tab.setOpaque(false);

        JsamsLabel tabTitle = null;
        ImageIcon tabIcon = null;
        if (iconFileName != null) {
            tabIcon = new ImageIcon(IconUtil.buildIcon(IconUtil.MENU_ICON_PREFIX + iconFileName));
            tabTitle = new JsamsLabel(title, tabIcon);
        } else {
            tabTitle = new JsamsLabel(title);
        }

        JsamsButton tabCloseButton = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "emblems/emblem-unreadable.png");
        tabCloseButton.setPreferredSize(new Dimension(tabCloseButton.getIcon().getIconWidth(), tabCloseButton.getIcon()
                .getIconHeight()));
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
