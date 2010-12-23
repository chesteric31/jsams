package be.jsams.client.renderer;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import be.jsams.client.i18n.UserContext;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;

/**
 * Generic combo-box renderer.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class GenericComboBoxRenderer implements ListCellRenderer {
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
		} else if (value instanceof Civility) {
			if (UserContext.isFrench()) {
				theText = ((Civility) value).getLabelFr();
			} else if (UserContext.isDutch()) {
				theText = ((Civility) value).getLabelNl();
			} else {
				theText = ((Civility) value).getLabel();
			}
		} else if (value instanceof PaymentMode) {
			if (UserContext.isFrench()) {
				theText = ((PaymentMode) value).getLabelFr();
			} else if (UserContext.isDutch()) {
				theText = ((PaymentMode) value).getLabelNl();
			} else {
				theText = ((PaymentMode) value).getLabel();
			}
		}
		renderer.setText(theText);
		return renderer;
	}
}
