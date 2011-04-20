package be.jsams.client.model.table;

import java.util.List;

import be.jsams.common.bean.model.AbstractIdentityBean;

import com.jgoodies.binding.adapter.AbstractTableAdapter;
import com.jgoodies.common.collect.ArrayListModel;

/**
 * Customized abstract table model for all search panels.
 * 
 * @param <D>
 *            an object extension of {@link AbstractIdentityBean}
 * 
 * @author chesteric31
 * @version $$Rev: 689 $$ $$Date::                  $$ $$Author$$
 */
public abstract class JsamsTableModel<D extends AbstractIdentityBean<?, ?>> extends AbstractTableAdapter<D> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2372165332757954578L;

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
     * Constructor
     * 
     * @param listBean
     *            a list of data bean
     */
    public JsamsTableModel(final List<D> listBean) {
        super(new ArrayListModel<D>(listBean));
    }

    /**
     * Removes from the ListModel the bean at index parameter.
     * 
     * @param index
     *            the index of the bean to delete
     */
    @SuppressWarnings("unchecked")
    public void remove(int index) {
        ((ArrayListModel<D>) getListModel()).remove(index);
    }

    /**
     * Clears all beans from the ListModel.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        ((ArrayListModel<D>) getListModel()).clear();
    }

}
