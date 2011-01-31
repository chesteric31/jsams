package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Agent;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentTableModel extends JsamsTableModel<Agent> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1514081531839355887L;

    /**
     * The columns name
     */
    private List<I18nString> columnNames = Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_NAME,
            JsamsI18nResource.COLUMN_FUNCTION, JsamsI18nResource.COLUMN_PHONE, JsamsI18nResource.COLUMN_ZIP_CODE,
            JsamsI18nResource.COLUMN_CITY);

    /**
     * @return the columns count
     */
    public int getColumnCount() {
        return columnNames.size();
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return getData().get(rowIndex).getId();
        case ONE:
            return getData().get(rowIndex).getName();
        case TWO:
            return getData().get(rowIndex).getFunction();
        case THREE:
            return getData().get(rowIndex).getContactInformation().getPhone();
        case FOUR:
            return getData().get(rowIndex).getAddress().getZipCode();
        case FIVE:
            return getData().get(rowIndex).getAddress().getCity();
        default:
            return "";
        }
    }

    /**
     * @param columnIndex
     *            the column
     * @return the column class
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return Long.class;
        case ONE:
            return String.class;
        case TWO:
            return String.class;
        case THREE:
            return String.class;
        case FOUR:
            return Integer.class;
        case FIVE:
            return String.class;
        default:
            return Object.class;
        }
    }

    /**
     * @param columnIndex
     *            the column
     * @return the column name
     */
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex).getTranslation();
    }

}
