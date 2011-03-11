package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.List;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.AgentBean;
import be.jsams.common.bean.model.ContactInformationBean;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentTableModel extends JsamsTableModel<AgentBean> {

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
     * Constructor
     * 
     * @param listModel a list of {@link AgentBean}
     */
    public AgentTableModel(List<AgentBean> listModel) {
        super(listModel);
    }

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
        AgentBean agent = (AgentBean) getRow(rowIndex);
        AddressBean address = agent.getAddress();
        switch (columnIndex) {
        case ZERO:
            return agent.getId();
        case ONE:
            return agent.getName();
        case TWO:
            return agent.getFunction();
        case THREE:
            ContactInformationBean contactInformation = agent.getContactInformation();
            if (contactInformation != null) {
                return contactInformation.getPhone();
            }
        case FOUR:
            if (address != null) {
                return address.getZipCode();
            }
        case FIVE:
            if (address != null) {
                return address.getCity();
            }
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
