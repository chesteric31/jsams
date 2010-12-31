package be.jsams.client.swing.listener;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.KeyStroke;

import be.jsams.client.swing.component.JsamsCloseableTabbedPane;

/**
 * {@link KeyListener} for {@link JsamsCloseableTabbedPane}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class TabbedPaneKeyListener implements KeyListener {

	/**
	 * Remove the tab at the selected index if the CTRL-W are pressed.
	 */
	public void keyPressed(KeyEvent e) {
		int pressedKeyCode = e.getKeyCode();

		int closeKeyCode = KeyStroke.getKeyStroke(KeyEvent.VK_W,
				InputEvent.CTRL_MASK).getKeyCode();
		if (closeKeyCode == pressedKeyCode) {
			JsamsCloseableTabbedPane tabbedPane = (JsamsCloseableTabbedPane) e
					.getComponent();
			int selectedIndex = tabbedPane.getSelectedIndex();
			if (selectedIndex > -1) {
				tabbedPane.removeTabAt(selectedIndex);
			}
		}
	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

}
