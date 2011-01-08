package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
@MappedSuperclass
public class AbstractNamedIdentity extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6640874458565482802L;

    private String name;

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
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
