package be.jsams.common.bean.model;

import be.jsams.common.bean.view.LegalFormBeanView;
import be.jsams.server.model.LegalForm;

import com.jgoodies.common.collect.ObservableList;

/**
 * Bean model for {@link LegalForm} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormBean extends AbstractTranslatableIdentityBean<LegalForm, LegalFormBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 426593985962833277L;

    /**
     * Constructor.
     * 
     * @param model the {@link LegalForm}
     */
    public LegalFormBean(LegalForm model) {
        super(model);
        setView(buildView());
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     */
    public LegalFormBean(ObservableList<LegalFormBean> list) {
        super();
        setListModel(list);
        setSelection(list.get(0));
        setView(buildView());
    }

    /**
     * Constructor
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link LegalForm} object
     */
    public LegalFormBean(ObservableList<LegalFormBean> list, LegalForm model) {
        this(model);
        setListModel(list);
        setSelection(this);
        setView(buildView());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LegalFormBeanView buildView() {
        return new LegalFormBeanView(this);
    }

}
