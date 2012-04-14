package be.jsams.client.model.table.sale;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.common.bean.model.sale.CreditNoteBean;

/**
 * {@link AbstractJsamsTableModel} for {@link CreditNoteBean} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CreditNoteTableModel extends AbstractJsamsTableModel<CreditNoteBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1353333277828106285L;

    /**
     * Default constructor.
     */
    public CreditNoteTableModel() {
        super();
    }
    
    /**
     * Constructor.
     * 
     * @param listBean a list of {@link CreditNoteBean}
     */
    public CreditNoteTableModel(List<CreditNoteBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_CREATION_DATE,
                JsamsI18nResource.COLUMN_REMARK));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        CreditNoteBean bean = (CreditNoteBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return bean.getId();
        case ONE:
            return bean.getCreationDate();
        case TWO:
            return bean.getRemark();
        default:
            return "";
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
        case ZERO:
            return Long.class;
        case ONE:
            return Date.class;
        case TWO:
            return String.class;
        default:
            return Object.class;
        }
    }

}
