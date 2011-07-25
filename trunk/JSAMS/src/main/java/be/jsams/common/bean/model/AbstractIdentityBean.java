package be.jsams.common.bean.model;

import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.server.model.AbstractIdentity;

import com.jgoodies.binding.beans.Model;
import com.jgoodies.common.collect.ObservableList;

/**
 * Abstract class for all beans that have an id.
 * 
 * @param <M> an extension of {@link AbstractIdentity}
 * @param <V> an extension of {@link AbstractBeanView}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractIdentityBean<M extends AbstractIdentity, V extends AbstractBeanView<?>> extends Model
        implements Refreshable<AbstractIdentityBean<?, ?>> {
    
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4948491302164990763L;

    private Long id;

    public static final String ID_PROPERTY = "id";

    private ObservableList<? extends AbstractIdentityBean<M, V>> listModel;
    private AbstractIdentityBean<M, V> selection;

    public static final String SELECTION_PROPERTY = "selection";
    public static final String LIST_MODEL_PROPERTY = "listModel";

    /**
     * Constructor.
     */
    public AbstractIdentityBean() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param model the {@link AbstractIdentity}
     */
    public AbstractIdentityBean(M model) {
        this();
        this.id = model.getId();
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        Long oldValue = this.id;
        this.id = id;
        firePropertyChange(ID_PROPERTY, oldValue, this.id);
    }

    /**
     * 
     * @return the {@link ObservableList}
     */
    public ObservableList<? extends AbstractIdentityBean<M, V>> getListModel() {
        return listModel;
    }

    /**
     * 
     * @param newListModel the {@link ObservableList} to set
     */
    public void setListModel(ObservableList<? extends AbstractIdentityBean<M, V>> newListModel) {
        ObservableList<? extends AbstractIdentityBean<M, V>> oldListModel = getListModel();
        this.listModel = newListModel;
        firePropertyChange(LIST_MODEL_PROPERTY, oldListModel, newListModel);
    }

    /**
     * 
     * @return the selection list
     */
    public AbstractIdentityBean<M, V> getSelection() {
        return selection;
    }

    /**
     * 
     * @param newSelection the new selected object
     */
    public void setSelection(AbstractIdentityBean<M, V> newSelection) {
        AbstractIdentityBean<M, V> oldSelection = getSelection();
        selection = newSelection;
        firePropertyChange(SELECTION_PROPERTY, oldSelection, newSelection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        // if (this == obj) {
        // return true;
        // }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof AbstractIdentityBean<?, ?>)) {
            return false;
        }
        AbstractIdentityBean<?, ?> other = (AbstractIdentityBean<?, ?>) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @return the {@link AbstractBeanView} of this class
     */
    public abstract V getView();

    /**
     * Clears the value.
     */
    public abstract void clear();

}
