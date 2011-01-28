package be.jsams.client.model.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import be.jsams.server.model.AbstractIdentity;

/**
 * Customized abstract table model for all search panels.
 * 
 * @param <D>
 *            an object extension of {@link AbstractIdentity}
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public abstract class JsamsTableModel<D extends AbstractIdentity> extends AbstractTableModel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2372165332757954578L;

    /**
     * The list of data
     */
    private List<D> data;

    /**
     * 
     * @return a list of data
     */
    public List<D> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *            the list of data
     */
    public void setData(List<D> data) {
        this.data = data;
    }

    /**
     * @return the rows count
     */
    public int getRowCount() {
        return data.size();
    }

    /**
     * Clears all data.
     */
    public void clear() {
        data.clear();
    }

}
