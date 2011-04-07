package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import be.jsams.common.bean.model.AbstractNamedIdentityBean;

/**
 * {@link MappedSuperclass} for all entities that have the column: name.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@MappedSuperclass
public abstract class AbstractNamedIdentity extends AbstractIdentity {

    private String name;

    /**
     * Constructor.
     */
    public AbstractNamedIdentity() {
    }

    /**
     * Constructor.
     * 
     * @param bean
     *            the {@link AbstractNamedIdentityBean}
     */
    public AbstractNamedIdentity(final AbstractNamedIdentityBean<?, ?> bean) {
        super(bean);
        setName(bean.getName());
    }

    /**
     * 
     * @return the name
     */
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractNamedIdentity [name=");
        builder.append(name);
        builder.append("]");
        return builder.toString();
    }

}
