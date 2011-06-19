package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.common.bean.model.CivilityBean;
import be.jsams.server.dao.CivilityDao;
import be.jsams.server.model.Civility;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link CivilityBean} from DAO list model and entity model. 
 *
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class CivilityBeanBuilder {
    
    private Civility model;
    
    private ObservableList<CivilityBean> listModel;
    
    private CivilityDao dao;

    /**
     * Build the {@link CivilityBean}.
     * 
     * @return the built {@link CivilityBean}
     */
    public CivilityBean build() {
        CivilityBean bean = null;
        List<Civility> civilities = dao.findAll();
        listModel = new ArrayListModel<CivilityBean>();
        for (Civility civility : civilities) {
            listModel.add(new CivilityBean(civility));
        }
        if (model == null) {
            listModel.add(0, null);
            bean = new CivilityBean(listModel);
        } else {
            bean = new CivilityBean(listModel, model);
        }
        return bean;
    }

    /**
     * @return the model
     */
    public Civility getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Civility model) {
        this.model = model;
    }

    /**
     * @return the listModel
     */
    public ObservableList<CivilityBean> getListModel() {
        return listModel;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<CivilityBean> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the dao
     */
    public CivilityDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(CivilityDao dao) {
        this.dao = dao;
    }

}
