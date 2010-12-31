package be.jsams.client.renderer;

import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Custom Table cell renderer for JSAMS with
 * row shading and row horizontal alignment following
 * data type.
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
public class JsamsTableCellRenderer extends DefaultTableCellRenderer {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -6717753457499479800L;
    
	public JsamsTableCellRenderer() {
        setOpaque(true);
    }

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Component renderer = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		if (row % 2 == 0 && !isSelected) {
			renderer.setBackground(Color.LIGHT_GRAY);
		} else if (!isSelected){
			renderer.setBackground(Color.WHITE);
		}

		if (value instanceof Integer || value instanceof Long
				|| value instanceof BigDecimal || value instanceof Double) {
			if (value instanceof BigDecimal) {
				NumberFormat currencyInstance = DecimalFormat
						.getCurrencyInstance();
				currencyInstance.setGroupingUsed(true);
				this.setValue(currencyInstance.format(value));
			}
			if (value instanceof Double) {
				NumberFormat percentInstance = DecimalFormat
						.getPercentInstance();
				Double doubleValue = (Double) value / 100;
				this.setValue(percentInstance.format(doubleValue));
			}
			this.setHorizontalAlignment(SwingConstants.RIGHT);
		} else if (value instanceof String) {
			this.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return this;
	}

}
