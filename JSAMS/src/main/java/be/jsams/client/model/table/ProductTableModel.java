package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import be.jsams.server.model.Product;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
public class ProductTableModel extends AbstractTableModel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5631609209979319706L;

	private List<String> columnNames = Arrays.asList("ID", "NAME", "PRICE",
			"QTE_STOCK", "REORDER_LEVEL", "VAT_APPLICABLE", "CATEGORY");

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
			return data.get(rowIndex).getPrice();
		case 3:
			return data.get(rowIndex).getQuantityStock();
		case 4:
			return data.get(rowIndex).getReorderLevel();
		case 5:
			return data.get(rowIndex).getVatApplicable();
		case 6:
			return data.get(rowIndex).getCategory();
		default:
			return "";
		}
	}

	public String getColumnName(int columnIndex) {
		return columnNames.get(columnIndex);
	}

}
