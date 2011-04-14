package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.common.bean.model.LegalFormBean;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.model.LegalForm;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link LegalFormBean} from DAO list model and entity model. 
 *
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class LegalFormBeanBuilder {

    private LegalForm model;

    private ObservableList<LegalFormBean> listModel;

    private LegalFormDao dao;

    /**
     * Build the {@link LegalFormBean}.
     * 
     * @return the built {@link LegalFormBean}
     */
    public LegalFormBean build() {
        List<LegalForm> forms = dao.findAll();
        listModel = new ArrayListModel<LegalFormBean>();
        if (forms != null && !forms.isEmpty()) {
            for (LegalForm form : forms) {
                listModel.add(new LegalFormBean(form));
            }
        }
        if (model != null) {
            return new LegalFormBean(listModel, model);
        } else {
            listModel.add(0, null);
            return new LegalFormBean(listModel);
        }
    }

    /**
     * @return the model
     */
    public LegalForm getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(LegalForm model) {
        this.model = model;
    }

    /**
     * @return the listModel
     */
    public ObservableList<LegalFormBean> getListModel() {
        return listModel;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<LegalFormBean> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the dao
     */
    public LegalFormDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(LegalFormDao dao) {
        this.dao = dao;
    }

}
