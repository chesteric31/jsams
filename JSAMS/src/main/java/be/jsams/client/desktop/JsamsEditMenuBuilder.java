package be.jsams.client.desktop;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsMenu;
import be.jsams.client.swing.component.JsamsMenuItem;

/**
 * Specific menu builder for edit menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsEditMenuBuilder extends AbstractMenuBuilder {

    private JsamsMenu editMenu;
    private JsamsMenuItem cutMI;
    private JsamsMenuItem copyMI;
    private JsamsMenuItem pasteMI;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public JsamsMenu build() {
        editMenu = new JsamsMenu(JsamsI18nResource.MENU_EDIT);
        cutMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_CUT, "actions/edit-cut.png");
        cutMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_MASK));
        editMenu.add(cutMI);
        copyMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_COPY, "actions/edit-copy.png");
        copyMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_MASK));
        editMenu.add(copyMI);
        pasteMI = new JsamsMenuItem(JsamsI18nResource.MENU_ITEM_PASTE, "actions/edit-paste.png");
        pasteMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_MASK));
        editMenu.add(pasteMI);
        return editMenu;
    }

}
