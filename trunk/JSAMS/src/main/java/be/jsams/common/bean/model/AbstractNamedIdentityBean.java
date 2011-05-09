package be.jsams.common.bean.model;

import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.server.model.AbstractNamedIdentity;

/**
 * Abstract class for all beans that have an id and a name.
 * 
 * @param <M>
 *            and extension of {@link AbstractNamedIdentity}
 * @param <V>
 *            an extension of {@link AbstractBeanView}
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractNamedIdentityBean<M extends AbstractNamedIdentity, V extends AbstractBeanView<?, ?, ?>>
        extends AbstractIdentityBean<M, V> {

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
     * @param model
     *            the {@link AbstractNamedIdentity} model
     */
    public AbstractNamedIdentityBean(M model) {
        super(model);
        setName(model.getName());
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
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

}
