package be.jsams.common.bean.model;

import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.server.model.AbstractNamedIdentity;

/**
 * Abstract class for all beans that have an id and a name.
 * 
 * @param <M> an extension of {@link AbstractNamedIdentity}
 * @param <V> an extension of {@link AbstractBeanView}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractNamedIdentityBean<M extends AbstractNamedIdentity, V extends AbstractBeanView<?>> extends
        AbstractIdentityBean<M, V> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7624401581772982665L;

    private String name;

    public static final String NAME_PROPERTY = "name";

    /**
     * Constructor.
     */
    public AbstractNamedIdentityBean() {
        super();
    }

    /**
     * Constructor with {@link AbstractNamedIdentity} parameter.
     * 
     * @param model the {@link AbstractNamedIdentity} model
     */
    public AbstractNamedIdentityBean(M model) {
        super(model);
        this.name = model.getName();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        String oldValue = this.name;
        this.name = name;
        firePropertyChange(NAME_PROPERTY, oldValue, this.name);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        setName(null);
        if (getListModel() != null && !getListModel().isEmpty()) {
            setSelection(getListModel().get(0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        AbstractNamedIdentityBean<?, ?> other = (AbstractNamedIdentityBean<?, ?>) bean;
        setName(other.getName());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (name == null) {
            result += 0;
        } else {
            result += name.hashCode();
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof AbstractNamedIdentityBean)) {
            return false;
        }
        AbstractNamedIdentityBean<?, ?> other = (AbstractNamedIdentityBean<?, ?>) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    
}
