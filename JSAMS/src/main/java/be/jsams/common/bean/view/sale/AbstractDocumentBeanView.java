package be.jsams.common.bean.view.sale;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import be.jsams.client.renderer.JsamsBooleanTableCellRenderer;
import be.jsams.client.renderer.JsamsTableCellRenderer;
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
     * Constructor.
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

    /**
     * {@inheritDoc}
     */
    public abstract void release();

    /**
     * Updates details table rendering.
     */
    public void updateDetailsTableRendering() {
        JTableHeader tableHeader = detailsTable.getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        detailsTable.setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        detailsTable.setDefaultRenderer(Long.class, defaultCellRenderer);
        detailsTable.setDefaultRenderer(Integer.class, defaultCellRenderer);
        detailsTable.setDefaultRenderer(Double.class, defaultCellRenderer);
        detailsTable.setDefaultRenderer(String.class, defaultCellRenderer);
        detailsTable.setDefaultRenderer(Boolean.class, new JsamsBooleanTableCellRenderer());
    }

}
