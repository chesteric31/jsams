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
            JsamsI18nResource.COLUMN_FUNCTION, JsamsI18nResource.COLUMN_PHONE,
            JsamsI18nResource.COLUMN_ZIP_CODE, JsamsI18nResource.COLUMN_CITY);
    
    public int getColumnCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public Object getValueAt(int arg0, int arg1) {
        // TODO Auto-generated method stub
        return null;
    }

}
