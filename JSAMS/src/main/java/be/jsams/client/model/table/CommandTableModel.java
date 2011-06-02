package be.jsams.client.model.table;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.sale.CommandBean;

/**
 * {@link AbstractJsamsTableModel} for {@link CommandBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CommandTableModel extends AbstractJsamsTableModel<CommandBean> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1772992590405438534L;

    /**
     * Default constructor.
     */
    public CommandTableModel() {
        super();
    }
    
    /**
     * Constructor
     * 
     * @param listBean a list of {@link CommandBean}
     */
    public CommandTableModel(List<CommandBean> listBean) {
        super(listBean);
        setColumnNames(Arrays.asList(JsamsI18nResource.COLUMN_ID, JsamsI18nResource.COLUMN_CREATION_DATE,
                JsamsI18nResource.COLUMN_TRANSFERRED, JsamsI18nResource.COLUMN_REMARK,
                JsamsI18nResource.COLUMN_DISCOUNT_RATE));
    }

    /**
     * {@inheritDoc}
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        CommandBean command = (CommandBean) getRow(rowIndex);
        switch (columnIndex) {
        case ZERO:
            return command.getId();
        case ONE:
            return command.getCreationDate();
        case TWO:
            return command.isTransferred();
        case THREE:
            return command.getRemark();
        case FOUR:
            return command.getDiscountRate();
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
            return Boolean.class;
        case THREE:
            return String.class;
        case FOUR:
            return Double.class;
        default:
            return Object.class;
        }
    }

}
