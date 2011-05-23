package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import be.jsams.common.bean.model.AbstractTranslatableIdentityBean;

/**
 * {@link MappedSuperclass} for all entities that have the columns: label in
 * English, French and Dutch.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
@MappedSuperclass
public abstract class AbstractTranslatableIdentity extends AbstractIdentity {

    private String label;
    private String labelFr;
    private String labelNl;

    /**
     * Constructor.
     */
    public AbstractTranslatableIdentity() {
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractTranslatableIdentityBean}
     */
    public AbstractTranslatableIdentity(final AbstractTranslatableIdentityBean<?, ?> bean) {
        super(bean);
        this.label = bean.getLabel();
        this.labelFr = bean.getLabelFr();
        this.labelNl = bean.getLabelNl();
    }

    /**
     * 
     * @return label in English
     */
    @Column(name = "LABEL")
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label the English label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * 
     * @return label in French
     */
    @Column(name = "LABEL_FR")
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * 
     * @param labelFr the French label to set
     */
    public void setLabelFr(String labelFr) {
        this.labelFr = labelFr;
    }

    /**
     * 
     * @return label in Dutch
     */
    @Column(name = "LABEL_NL")
    public String getLabelNl() {
        return labelNl;
    }

    /**
     * 
     * @param labelNl the Dutch label to set
     */
    public void setLabelNl(String labelNl) {
        this.labelNl = labelNl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("AbstractTranslatableIdentity [label=");
        builder.append(label);
        builder.append(", labelFr=");
        builder.append(labelFr);
        builder.append(", labelNl=");
        builder.append(labelNl);
        builder.append("]");
        return builder.toString();
    }

}
