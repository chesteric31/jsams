package be.jsams.client.swing.listener;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.KeyStroke;

import be.jsams.client.desktop.Desktop;
import be.jsams.client.swing.component.JsamsCloseableTabbedPane;

/**
 * {@link KeyListener} for {@link JsamsCloseableTabbedPane}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class TabbedPaneKeyListener implements KeyListener {

    /**
     * {@inheritDoc}
     */
    public void keyPressed(KeyEvent e) {
        int pressedKeyCode = e.getKeyCode();

        int closeKeyCode = KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.CTRL_MASK).getKeyCode();
        if (closeKeyCode == pressedKeyCode) {
            // Remove the tab at the selected index if the CTRL-W are pressed.
            JsamsCloseableTabbedPane tabbedPane = (JsamsCloseableTabbedPane) e.getComponent();
            int selectedIndex = tabbedPane.getSelectedIndex();
            if (selectedIndex > -1) {
                tabbedPane.removeTabAt(selectedIndex);
            }
            if (tabbedPane.getTabCount() == 0) {
                Desktop.getInstance().getMainWindow().enableTabbedPane(false);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void keyReleased(KeyEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    public void keyTyped(KeyEvent e) {
    }

}
