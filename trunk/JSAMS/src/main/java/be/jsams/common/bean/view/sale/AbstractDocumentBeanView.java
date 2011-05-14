package be.jsams.common.bean.view.sale;

import javax.swing.JComponent;
import javax.swing.JPanel;

import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.view.AbstractBeanView;

/**
 * Abstract class for the viewing of the sale beans: estimate,
 * command, delivery order, bill and credit note.
 *
 * @param <B> an extension of {@link AbstractDocumentBean}
 * @param <J> an extension of {@link JComponent} for editing
 * @param <K> an extension of {@link JPanel} for searching
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractDocumentBeanView<B extends AbstractDocumentBean<?, ?>,
        J extends JComponent, K extends JPanel> extends AbstractBeanView<B, J, K> {

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
