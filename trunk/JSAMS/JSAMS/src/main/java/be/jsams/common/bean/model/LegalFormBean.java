package be.jsams.common.bean.model;

import java.util.ArrayList;
import java.util.List;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.common.bean.view.LegalFormBeanView;
import be.jsams.server.model.LegalForm;

import com.jgoodies.common.collect.ArrayListModel;

/**
 * 
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class LegalFormBean extends AbstractTranslatableIdentityBean<LegalForm, LegalFormBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 426593985962833277L;

    private static ArrayListModel<LegalFormBean> list = new ArrayListModel<LegalFormBean>();
    static {
        List<LegalForm> allLegalForms = JsamsApplicationContext.getLegalFormDao().findAll();
        List<LegalFormBean> beans = new ArrayList<LegalFormBean>();
        for (LegalForm legalForm : allLegalForms) {
            beans.add(new LegalFormBean(legalForm));
        }
        list.add(null);
        list.addAll(beans);
    }

    /**
     * Default constructor
     */
    public LegalFormBean() {
        super();
        setListModel(list);
        setSelection(list.get(0));
    }

    /**
     * Constructor
     * 
     * @param model
     *            the {@link LegalForm}
     */
    public LegalFormBean(LegalForm model) {
        super(model);
        setListModel(list);
        setSelection(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LegalFormBeanView getView() {
        return new LegalFormBeanView(this);
    }


}
