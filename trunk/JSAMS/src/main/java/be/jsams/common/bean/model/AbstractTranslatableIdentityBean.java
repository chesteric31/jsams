package be.jsams.common.bean.model;

import be.jsams.common.bean.view.AbstractBeanView;
import be.jsams.server.model.AbstractTranslatableIdentity;

/**
 * Abstract class for all beans that have an id and a English label, French
 * label and Dutch label.
 * 
 * @param <M> an extension of {@link AbstractTranslatableIdentity}
 * @param <V> an extension of {@link AbstractBeanView}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractTranslatableIdentityBean
        <M extends AbstractTranslatableIdentity, V extends AbstractBeanView<?>>
        extends AbstractIdentityBean<M, V> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2973569262423721024L;

    private String label;
    private String labelFr;
    private String labelNl;

    public static final String LABEL_PROPERTY = "label";
    public static final String LABEL_FR_PROPERTY = "labelFr";
    public static final String LABEL_NL_PROPERTY = "labelNl";

    /**
     * Constructor
     */
    public AbstractTranslatableIdentityBean() {
        super();
    }

    /**
     * Constructor with the {@link AbstractTranslatableIdentity} model
     * 
     * @param model the {@link AbstractTranslatableIdentity} model to use
     */
    public AbstractTranslatableIdentityBean(M model) {
        super(model);
        this.label = model.getLabel();
        this.labelFr = model.getLabelFr();
        this.labelNl = model.getLabelNl();
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * 
     * @param label the label to set
     */
    public void setLabel(String label) {
        String oldValue = this.label;
        this.label = label;
        firePropertyChange(LABEL_PROPERTY, oldValue, this.label);
    }

    /**
     * 
     * @return the French label
     */
    public String getLabelFr() {
        return labelFr;
    }

    /**
     * 
     * @param labelFr the French label to set
     */
    public void setLabelFr(String labelFr) {
        String oldValue = this.labelFr;
        this.labelFr = labelFr;
        firePropertyChange(LABEL_FR_PROPERTY, oldValue, this.labelFr);
    }

    /**
     * 
     * @return the Dutch label
     */
    public String getLabelNl() {
        return labelNl;
    }

    /**
     * 
     * @param labelNl the Dutch label to set
     */
    public void setLabelNl(String labelNl) {
        String oldValue = this.labelNl;
        this.labelNl = labelNl;
        firePropertyChange(LABEL_NL_PROPERTY, oldValue, this.labelNl);
    }

    /**
     * {@inheritDoc}
     */
    public void clear() {
        setLabel(null);
        setLabelFr(null);
        setLabelNl(null);
        if (getListModel() != null && !getListModel().isEmpty()) {
            setSelection(getListModel().get(0));
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        AbstractTranslatableIdentityBean<M, V> other = (AbstractTranslatableIdentityBean<M, V>) bean;
        setId(other.getId());
        setLabel(other.getLabel());
        setLabelFr(other.getLabelFr());
        setLabelNl(other.getLabelNl());
        if (other.getSelection() != null) {
            setSelection(other.getSelection());
        }
        // setListModel(other.getListModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result;
        if (label == null) {
            result += 0;
        } else {
            result += label.hashCode();
        }
        if (labelFr == null) {
            result += 0;
        } else {
            result += labelFr.hashCode();
        }
        if (labelNl == null) {
            result += 0;
        } else {
            result += labelNl.hashCode();
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
        if (!(obj instanceof AbstractTranslatableIdentityBean)) {
            return false;
        }
        AbstractTranslatableIdentityBean<?, ?> other = (AbstractTranslatableIdentityBean<?, ?>) obj;
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

}
