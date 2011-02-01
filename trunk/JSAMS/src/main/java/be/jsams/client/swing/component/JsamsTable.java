package be.jsams.client.swing.component;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import be.jsams.client.model.panel.SearchPanel;
import be.jsams.client.model.table.JsamsTableModel;

/**
 * Specific {@link JTable} for JSAMS with auto resize capabilities when the call to setModel method.
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
     * 
     * @param autoResizeColumnWidth
     *            true to auto resize the column width, false otherwise
     */
    public JsamsTable(final boolean autoResizeColumnWidth) {
        super();
        this.autoResizeColumnWidth = autoResizeColumnWidth;
    }

    @Override
    public void setModel(final TableModel model) {
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
     * Returns the eventually {@link SearchPanel}.
     * 
     * @return the eventually {@link SearchPanel} parent container
     */
    public Container getEventuallySearchPanel() {
        for (Container p = this; p != null; p = p.getParent()) {
            if (p instanceof SearchPanel<?, ?, ?>) {
                return p;
            }
        }
        return null;
    }

    /**
     * Returns the eventually {@link JsamsDialog}.
     * 
     * @return the eventually {@link JsamsDialog} parent container
     */
    public Container getEventuallyJsamsDialog() {
        for (Container p = this; p != null; p = p.getParent()) {
            if (p instanceof JsamsDialog) {
                return p;
            }
        }
        return null;
    }

    /**
     * Clears the table. (remove all rows)
     */
    public synchronized void clear() {
        JsamsTableModel<?> model = (JsamsTableModel<?>) getModel();
        model.clear();
    }

}
