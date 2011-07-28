package be.jsams.client.swing.component;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import be.jsams.client.model.table.AbstractJsamsTableModel;

/**
 * Specific {@link JTable} for JSAMS with auto resize capabilities when the call
 * to setModel method.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class JsamsTable extends JTable {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5780739787448004020L;

    private boolean autoResizeColumnWidth = false;

    /**
     * Constructor.
     */
    public JsamsTable() {
        this(true);
    }

    /**
     * Constructor.
     * 
     * @param selectionMode selection mode to use
     */
    public JsamsTable(int selectionMode) {
        this(true, selectionMode);
    }
    
    /**
     * Constructor.
     * 
     * @param autoResizeColumnWidth true to auto resize the column width, false
     *            otherwise
     */
    public JsamsTable(final boolean autoResizeColumnWidth) {
        this(autoResizeColumnWidth, ListSelectionModel.SINGLE_SELECTION);
    }
    
    /**
     * Constructor.
     * 
     * @param autoResizeColumnWidth true to auto resize the column width, false
     *            otherwise
     * @param selectionMode selection mode to use
     */
    public JsamsTable(final boolean autoResizeColumnWidth, int selectionMode) {
        super();
        this.autoResizeColumnWidth = autoResizeColumnWidth;
        setSelectionMode(selectionMode);
    }

    /**
     * {@inheritDoc}
     */
    public void setModel(final AbstractJsamsTableModel<?> model) {
        super.setModel(model);
        if (autoResizeColumnWidth) {
            autoResizeColumnWidth();
        }
    }

    /**
     * Methods that auto resize the column width following the size of the data.
     */
    private void autoResizeColumnWidth() {
        final int margin = 5;

        for (int i = 0; i < getColumnCount(); i++) {
            int vColIndex = i;
            DefaultTableColumnModel colModel = (DefaultTableColumnModel) getColumnModel();
            TableColumn col = colModel.getColumn(vColIndex);
            int width = 0;

            // Get width of column header
            TableCellRenderer renderer = col.getHeaderRenderer();

            if (renderer == null) {
                renderer = getTableHeader().getDefaultRenderer();
            }

            Component comp = renderer.getTableCellRendererComponent(this, col.getHeaderValue(), false, false, 0, 0);

            width = comp.getPreferredSize().width;

            // Get maximum width of column data
            for (int r = 0; r < getRowCount(); r++) {
                renderer = getCellRenderer(r, vColIndex);
                comp = renderer.getTableCellRendererComponent(this, this.getValueAt(r, vColIndex), false, false, r,
                        vColIndex);
                width = Math.max(width, comp.getPreferredSize().width);
            }

            // Add margin
            width += 2 * margin;

            // Set the width
            col.setPreferredWidth(width);
        }

        getTableHeader().setReorderingAllowed(false);
    }

    /**
     * Clears the table. (remove all rows)
     */
    public synchronized void clear() {
        // only if instance of jsamsTableModel and not defaultTableModel
        if (getModel() instanceof AbstractJsamsTableModel<?>) {
            AbstractJsamsTableModel<?> model = (AbstractJsamsTableModel<?>) getModel();
            model.clear();
        }
    }

}
