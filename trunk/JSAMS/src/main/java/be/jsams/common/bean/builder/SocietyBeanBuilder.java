package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.server.dao.LegalFormDao;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link SocietyBean} from DAO list model and
 * entity model.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyBeanBuilder {

    private Society model;
    
    private ObservableList<SocietyBean> listModel;
    
    private SocietyDao dao;
    
    private LegalFormBeanBuilder legalFormBuilder = new LegalFormBeanBuilder();

    private LegalFormDao legalFormDao;

    /**
     * Build the {@link SocietyBean}.
     * 
     * @param newOne to create a new one {@link SocietyBean} from scratch
     * 
     * @return the built {@link SocietyBean}
     */
    public SocietyBean build(boolean newOne) {
        SocietyBean bean;
        legalFormBuilder.setDao(getLegalFormDao());
        if (newOne) {
            bean = new SocietyBean();
            bean.setLegalForm(legalFormBuilder.build());
            return bean;
        } else {
            List<Society> societies = dao.findAll();
            listModel = new ArrayListModel<SocietyBean>();
            if (societies != null && !societies.isEmpty()) {
                for (Society society : societies) {
                    LegalForm form = society.getLegalForm();
                    if (form != null) {
                        legalFormBuilder.setModel(form);
                    }
                    bean = new SocietyBean(society);
                    bean.setLegalForm(legalFormBuilder.build());
                    listModel.add(bean);
                }
            }
            if (model != null) {
                bean = new SocietyBean(listModel, model);
            } else {
                listModel.add(0, null);
                bean = new SocietyBean(listModel);
            }
        }
        return bean;
    }

    /**
     * @return the model
     */
    public Society getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Society model) {
        this.model = model;
    }

    /**
     * @return the listModel
     */
    public ObservableList<SocietyBean> getListModel() {
        return listModel;
    }

    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<SocietyBean> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the dao
     */
    public SocietyDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(SocietyDao dao) {
        this.dao = dao;
    }

    /**
     * @return the {@link LegalFormDao}
     */
    public LegalFormDao getLegalFormDao() {
        return legalFormDao;
    }

    /**
     * @param dao the {@link LegalFormDao} to set
     */
    public void setLegalFormDao(LegalFormDao dao) {
        this.legalFormDao = dao;
    }
    
}
