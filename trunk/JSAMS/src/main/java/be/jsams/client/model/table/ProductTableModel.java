package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Product;

/**
 * {@link AbstractTableModel} for {@link Product} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class ProductTableModel extends AbstractTableModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5631609209979319706L;

	private List<I18nString> columnNames = Arrays.asList(
			JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
			JsamsI18nResource.COLUMN_PRICE,
			JsamsI18nResource.COLUMN_STOCK_QUANTITY,
			JsamsI18nResource.COLUMN_REORDER_LEVEL,
			JsamsI18nResource.COLUMN_VAT_APPLICABE,
			JsamsI18nResource.COLUMN_PRODUCT_CATEGORY);

	private List<Product> data;

	public List<Product> getData() {
		return data;
	}

	public void setData(List<Product> data) {
		this.data = data;
	}

	public int getColumnCount() {
		return columnNames.size();
	}

	public int getRowCount() {
		return data.size();
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getId();
		case 1:
			return data.get(rowIndex).getName();
		case 2:
			return data.get(rowIndex).getPrice().toPlainString();
		case 3:
			return data.get(rowIndex).getQuantityStock();
		case 4:
			return data.get(rowIndex).getReorderLevel();
		case 5:
			return data.get(rowIndex).getVatApplicable().toPlainString();
		case 6:
			return data.get(rowIndex).getCategory().getName();
		default:
			return "";
		}
	}

	public String getColumnName(int columnIndex) {
		return columnNames.get(columnIndex).getTranslation();
	}

}
