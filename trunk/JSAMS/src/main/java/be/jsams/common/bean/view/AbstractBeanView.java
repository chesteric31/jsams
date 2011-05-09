package be.jsams.common.bean.view;

import javax.swing.JComponent;
import javax.swing.JPanel;

import be.jsams.common.bean.model.AbstractIdentityBean;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.common.collect.ObservableList;

/**
 * Abstract class for the viewing of a bean.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 * @param <J> an extension of {@link JComponent} for editing
 * @param <K> an extension of {@link JPanel} for searching
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractBeanView<B extends AbstractIdentityBean<?, ?>, J extends JComponent, K extends JPanel>
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
     * @param bean the bean
     */
    public AbstractBeanView(B bean) {
        super(bean);
        ObservableList<?> listModel = bean.getListModel();
        ValueHolder listHolder = new ValueHolder(listModel, true);
        selectionInList = new SelectionInList<B>(listHolder, getModel(B.SELECTION_PROPERTY));
    }

    /**
     * 
     * @return the {@link SelectionInList}
     */
    public SelectionInList<B> getSelectionInList() {
        return selectionInList;
    }

}
