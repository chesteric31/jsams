package be.jsams.common.bean.model;

import be.jsams.common.bean.view.CivilityBeanView;
import be.jsams.server.model.Civility;

import com.jgoodies.common.collect.ObservableList;

/**
 * Bean model for {@link Civility} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CivilityBean extends AbstractTranslatableIdentityBean<Civility, CivilityBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8432665851403685710L;

    /**
     * Constructor.
     * 
     * @param model the {@link Civility}
     */
    public CivilityBean(Civility model) {
        super(model);
    }

    /**
     * Constructor.
     * 
     * @param list the {@link ObservableList}
     */
    public CivilityBean(ObservableList<CivilityBean> list) {
        super();
        setListModel(list);
        setSelection(list.get(0));
    }

    /**
     * Constructor
     * 
     * @param list the {@link ObservableList}
     * @param model the {@link Civility} object
     */
    public CivilityBean(ObservableList<CivilityBean> list, Civility model) {
        this(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CivilityBeanView buildView() {
        return new CivilityBeanView(this);
    }

}
