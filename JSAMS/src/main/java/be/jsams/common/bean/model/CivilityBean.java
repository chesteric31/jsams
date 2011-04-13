package be.jsams.common.bean.model;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.view.CivilityBeanView;
import be.jsams.server.model.Civility;

import com.jgoodies.common.collect.ArrayListModel;

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

    private static ArrayListModel<CivilityBean> list = new ArrayListModel<CivilityBean>();
    static {
        list.add(null);
        for (Civility civility : JsamsApplicationContext.getCivilityDao().findAll()) {
            list.add(new CivilityBean(civility));
        }
    }

    /**
     * Default constructor.
     */
    public CivilityBean() {
        super();
        setListModel(list);
        setSelection(list.get(0));
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link Civility} object
     */
    public CivilityBean(Civility model) {
        super(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CivilityBeanView getView() {
        return new CivilityBeanView(this);
    }

}
