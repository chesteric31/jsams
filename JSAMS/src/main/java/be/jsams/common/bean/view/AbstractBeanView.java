package be.jsams.common.bean.view;

import be.jsams.common.bean.model.AbstractIdentityBean;

import com.jgoodies.binding.PresentationModel;
import com.jgoodies.binding.list.SelectionInList;
import com.jgoodies.binding.value.ValueHolder;
import com.jgoodies.common.collect.ObservableList;

/**
 * Abstract class for the viewing of a bean.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractBeanView<B extends AbstractIdentityBean<?, ?>> extends PresentationModel<B> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6149471709494092949L;

    /**
     * Holds the bean's list model plus a selection.
     */
    private SelectionInList<B> selectionInList;

    private ViewFactory<B> viewFactory;

    /**
     * Constructor
     * 
     * @param bean the bean
     */
    public AbstractBeanView(B bean) {
        super(bean);
        viewFactory = new ViewFactory<B>();
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

    /**
     * @return the viewFactory
     */
    public ViewFactory<B> getViewFactory() {
        return viewFactory;
    }
    
}
