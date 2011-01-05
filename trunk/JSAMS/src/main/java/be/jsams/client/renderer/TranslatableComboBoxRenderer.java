package be.jsams.client.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.jsams.client.i18n.UserContext;
import be.jsams.server.model.AbstractTranslatableIdentity;

/**
 * Combo box renderer for object that have translatable label from DB.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TranslatableComboBoxRenderer implements ListCellRenderer {

	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String theText = " ";

		JLabel renderer = (JLabel) defaultRenderer
				.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
		if (value != null) {
			if (UserContext.isFrench()) {
				theText = ((AbstractTranslatableIdentity) value).getLabelFr();
			} else if (UserContext.isDutch()) {
				theText = ((AbstractTranslatableIdentity) value).getLabelNl();
			} else {
				theText = ((AbstractTranslatableIdentity) value).getLabel();
			}
		}
		renderer.setText(theText);
		return renderer;
	}

}
