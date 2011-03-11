package be.jsams.common.bean.view;

import javax.swing.JComponent;

import be.jsams.common.bean.model.AbstractIdentityBean;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;

/**
 * 
 * @param <B>
 *            an extension of {@link AbstractIdentityBean}
 * @param <J>
 *            an extension of {@link JComponent} for editing
 * @param <K>
 *            an extension of {@link JComponent} for searching
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractView<B extends AbstractIdentityBean<?, ?>, J extends JComponent, K extends JComponent>
        extends PresentationModel<B> implements Viewable<J, K> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6149471709494092949L;

    /**
     * Holds the bean's list model plus a selection.
     */
    private SelectionInList<B> selectionInList;

    /**
     * Constructor
     * 
     * @param bean
     *            the bean
     */
    public AbstractView(B bean) {
        super(bean);
        selectionInList = new SelectionInList<B>(new ValueHolder(bean.getListModel(), true),
                getModel(B.PROPERTYNAME_SELECTION));
    }

    /**
     * 
     * @return the {@link SelectionInList}
     */
    public SelectionInList<B> getSelectionInList() {
        return selectionInList;
    }

}
