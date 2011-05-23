package be.jsams.server.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import be.jsams.common.bean.model.AbstractIdentityBean;

/**
 * Super class for Identity interface.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@MappedSuperclass
public abstract class AbstractIdentity implements Identity {

    private Long id;

    /**
     * Constructor.
     */
    public AbstractIdentity() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractIdentityBean}
     */
    public AbstractIdentity(final AbstractIdentityBean<?, ?> bean) {
        this.id = bean.getId();
    }

    /**
     * {@inheritDoc}
     */
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractIdentity [id=");
        builder.append(id);
        builder.append("]");
        return builder.toString();
    }

}
