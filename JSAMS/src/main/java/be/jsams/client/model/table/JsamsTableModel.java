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

    /** Column number zero for switch purpose */
    protected static final int ZERO = 0;
    /** Column number one for switch purpose */
    protected static final int ONE = 1;
    /** Column number two for switch purpose */
    protected static final int TWO = 2;
    /** Column number three for switch purpose */
    protected static final int THREE = 3;
    /** Column number four for switch purpose */
    protected static final int FOUR = 4;
    /** Column number five for switch purpose */
    protected static final int FIVE = 5;
    /** Column number six for switch purpose */
    protected static final int SIX = 6;
    /** Column number seven for switch purpose */
    protected static final int SEVEN = 7;
    /** Column number eight for switch purpose */
    protected static final int EIGHT = 8;
    /** Column number nine for switch purpose */
    protected static final int NINE = 9;

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
