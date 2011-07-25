package be.jsams.common.bean.view.sale;

import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.view.AbstractBeanView;

/**
 * Abstract class for the viewing of the sale beans: estimate, command, delivery
 * order, bill and credit note.
 * 
 * @param <B> an extension of {@link AbstractDocumentBean}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractDocumentBeanView<B extends AbstractDocumentBean<?, ?>> extends AbstractBeanView<B> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -6357480829600225339L;

    private JsamsTable detailsTable;

    /**
     * Constructor
     * 
     * @param bean the {@link AbstractDocumentBean}
     */
    public AbstractDocumentBeanView(B bean) {
        super(bean);
    }

    /**
     * @return the detailsTable
     */
    public JsamsTable getDetailsTable() {
        return detailsTable;
    }

    /**
     * @param detailsTable the detailsTable to set
     */
    public void setDetailsTable(JsamsTable detailsTable) {
        this.detailsTable = detailsTable;
    }

}
