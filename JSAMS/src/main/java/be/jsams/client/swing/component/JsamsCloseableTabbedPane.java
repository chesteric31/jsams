package be.jsams.client.swing.component;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.utils.IconUtil;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class JsamsCloseableTabbedPane extends JTabbedPane {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5934631016465665535L;

	public JsamsCloseableTabbedPane() {
		super();
	}

	public void addTab(I18nString title, Component component) {
		final JPanel content = new JPanel();
		JPanel tab = new JPanel();
		tab.setOpaque(false);

		JsamsLabel tabLabel = new JsamsLabel(title);
		JsamsButton tabCloseButton = new JsamsButton(
				IconUtil.MENU_ICON_PREFIX + "emblems/emblem-unreadable.png");
		tabCloseButton.setPreferredSize(new Dimension(tabCloseButton.getIcon()
				.getIconWidth(), tabCloseButton.getIcon().getIconHeight()));
		tabCloseButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int closeTabNumber = indexOfComponent(content);
				removeTabAt(closeTabNumber);
			}
		});

		tab.add(tabLabel);
		tab.add(tabCloseButton);

		super.addTab(null, content);
		setTabComponentAt(getTabCount() - 1, tab);
	}
}
