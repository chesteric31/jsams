package be.jsams.common.bean.builder.management;

import be.jsams.common.bean.builder.CivilityBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.server.model.Civility;
import be.jsams.server.model.management.Agent;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link AgentBean} from entity model. 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentBeanBuilder {

    private Agent model;
    
    private ObservableList<AgentBean> listModel;
    
    private CivilityBeanBuilder civilityBeanBuilder;

    /**
     * Builds the {@link AgentBean}.
     * 
     * @param model the model to use to create the bean, null if this is a new bean from scratch to build
     * @param society the {@link SocietyBean} linked (current society)
     * 
     * @return the built {@link AgentBean}
     */
    public AgentBean build(Agent model, SocietyBean society) {
        AgentBean bean;
        if (model == null) {
            bean = new AgentBean(society);
            civilityBeanBuilder.setModel(null);
            bean.setCivility(civilityBeanBuilder.build());
        } else {
            if (listModel == null) {
                listModel = new ArrayListModel<AgentBean>();
            } else {
                listModel.clear();
            }
            Civility civility = model.getCivility();
            if (civility != null) {
                civilityBeanBuilder.setModel(civility);
            }
            bean = new AgentBean(model, society);
            bean.setCivility(civilityBeanBuilder.build());
            listModel.add(bean);
        }
        return bean;
    }
    
    /**
     * @return the civilityBeanBuilder
     */
    public CivilityBeanBuilder getCivilityBeanBuilder() {
        return civilityBeanBuilder;
    }

    /**
     * @param civilityBeanBuilder the civilityBeanBuilder to set
     */
    public void setCivilityBeanBuilder(CivilityBeanBuilder civilityBeanBuilder) {
        this.civilityBeanBuilder = civilityBeanBuilder;
    }

    /**
     * @return the model
     */
    public Agent getModel() {
        return model;
    }
    /**
     * @param model the model to set
     */
    public void setModel(Agent model) {
        this.model = model;
    }
    /**
     * @return the listModel
     */
    public ObservableList<AgentBean> getListModel() {
        return listModel;
    }
    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<AgentBean> listModel) {
        this.listModel = listModel;
    }
    
}
