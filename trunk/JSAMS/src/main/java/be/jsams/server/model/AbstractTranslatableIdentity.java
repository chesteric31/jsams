package be.jsams.server.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * {@link MappedSuperclass} for all entities that have the columns: label in English, French and Dutch.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
@MappedSuperclass
public class AbstractTranslatableIdentity extends AbstractIdentity {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5943662285794644039L;

    private String label;
    private String labelFr;
    private String labelNl;

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
     * @param label
     *            the English label to set
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
     * @param labelFr
     *            the French label to set
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
     * @param labelNl
     *            the Dutch label to set
     */
    public void setLabelNl(String labelNl) {
        this.labelNl = labelNl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((labelFr == null) ? 0 : labelFr.hashCode());
        result = prime * result + ((labelNl == null) ? 0 : labelNl.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        // if (!super.equals(obj)) {
        // return false;
        // }
        if (!(obj instanceof AbstractTranslatableIdentity)) {
            return false;
        }
        AbstractTranslatableIdentity other = (AbstractTranslatableIdentity) obj;
        if (label == null) {
            if (other.label != null) {
                return false;
            }
        } else if (!label.equals(other.label)) {
            return false;
        }
        if (labelFr == null) {
            if (other.labelFr != null) {
                return false;
            }
        } else if (!labelFr.equals(other.labelFr)) {
            return false;
        }
        if (labelNl == null) {
            if (other.labelNl != null) {
                return false;
            }
        } else if (!labelNl.equals(other.labelNl)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AbstractTranslatableIdentity [label=" + label + ", labelFr=" + labelFr + ", labelNl=" + labelNl + "]";
    }

}
