package be.jsams.common.bean.builder;

import java.util.List;

import be.jsams.common.bean.model.SocietyBean;
import be.jsams.server.dao.SocietyDao;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.Society;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link SocietyBean} from DAO list model and entity model.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SocietyBeanBuilder {

    private Society model;

    private ObservableList<SocietyBean> listModel;

    private SocietyDao dao;

    private LegalFormBeanBuilder legalFormBuilder;

    /**
     * Build the {@link SocietyBean}.
     * 
     * @param newOne to create a new one {@link SocietyBean} from scratch
     * 
     * @return the built {@link SocietyBean}
     */
    public SocietyBean build(boolean newOne) {
        SocietyBean bean;
        if (newOne) {
            bean = new SocietyBean();
            legalFormBuilder.setModel(null);
            bean.setLegalForm(legalFormBuilder.build());
            return bean;
        } else {
            List<Society> societies = dao.findAll();
            if (listModel == null) {
                listModel = new ArrayListModel<SocietyBean>();
            } else {
                listModel.clear();
            }
            if (societies != null && !societies.isEmpty()) {
                for (Society society : societies) {
                    LegalForm form = society.getLegalForm();
                    legalFormBuilder.setModel(form);
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
            bean.setLegalForm(legalFormBuilder.build());
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
     * @return the legalFormBuilder
     */
    public LegalFormBeanBuilder getLegalFormBuilder() {
        return legalFormBuilder;
    }

    /**
     * @param legalFormBuilder the legalFormBuilder to set
     */
    public void setLegalFormBuilder(LegalFormBeanBuilder legalFormBuilder) {
        this.legalFormBuilder = legalFormBuilder;
    }

}
