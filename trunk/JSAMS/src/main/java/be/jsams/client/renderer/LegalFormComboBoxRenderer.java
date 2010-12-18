package be.jsams.client.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.jsams.client.i18n.UserContext;
import be.jsams.server.model.LegalForm;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormComboBoxRenderer implements ListCellRenderer {
	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		String theText = " ";

		JLabel renderer = (JLabel) defaultRenderer
				.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);
		if (value instanceof LegalForm) {
			if (UserContext.isFrench()) {
				theText = ((LegalForm) value).getLabelFr();
			} else if (UserContext.isDutch()) {
				theText = ((LegalForm) value).getLabelNl();
			} else {
				theText = ((LegalForm) value).getLabel();
			}
		}
		renderer.setText(theText);
		return renderer;
	}
}
