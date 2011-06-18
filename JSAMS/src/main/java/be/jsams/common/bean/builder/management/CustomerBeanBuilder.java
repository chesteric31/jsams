package be.jsams.common.bean.builder.management;

import be.jsams.common.bean.builder.CivilityBeanBuilder;
import be.jsams.common.bean.builder.LegalFormBeanBuilder;
import be.jsams.common.bean.builder.PaymentModeBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.server.model.Civility;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;

import com.jgoodies.common.collect.ArrayListModel;
import com.jgoodies.common.collect.ObservableList;

/**
 * Builder that makes a {@link CustomerBean} from entity model. 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerBeanBuilder {

    private Customer model;
    
    private ObservableList<CustomerBean> listModel;
    
    private CivilityBeanBuilder civilityBeanBuilder;
    private LegalFormBeanBuilder legalFormBeanBuilder;
    private PaymentModeBeanBuilder paymentModeBeanBuilder;
    private AgentBeanBuilder agentBeanBuilder;

    /**
     * Build the {@link CustomerBean}.
     * 
     * @param model the model to use to create the bean, null if this is a new bean from scratch to build
     * @param society the {@link SocietyBean} linked (current society)
     * 
     * @return the built {@link CustomerBean}
     */
    public CustomerBean build(Customer model, SocietyBean society) {
        CustomerBean bean;
        if (model == null) {
            bean = new CustomerBean(society);
            legalFormBeanBuilder.setModel(null);
            bean.setLegalForm(legalFormBeanBuilder.build());
            civilityBeanBuilder.setModel(null);
            bean.setCivility(civilityBeanBuilder.build());
            paymentModeBeanBuilder.setModel(null);
            bean.setPaymentMode(paymentModeBeanBuilder.build());
            agentBeanBuilder.setModel(null);
            bean.setAgent(agentBeanBuilder.build(null, society));
        } else {
            if (listModel == null) {
                listModel = new ArrayListModel<CustomerBean>();
            } else {
                listModel.clear();
            }
            LegalForm form = model.getLegalForm();
            if (form != null) {
                legalFormBeanBuilder.setModel(form);
            }
            Civility civility = model.getCivility();
            if (civility != null) {
                civilityBeanBuilder.setModel(civility);
            }
            PaymentMode mode = model.getPaymentMode();
            if (mode != null) {
                paymentModeBeanBuilder.setModel(mode);
            }
            Agent agent = model.getAgent();
            bean = new CustomerBean(model, society);
            bean.setLegalForm(legalFormBeanBuilder.build());
            bean.setCivility(civilityBeanBuilder.build());
            bean.setPaymentMode(paymentModeBeanBuilder.build());
            bean.setAgent(agentBeanBuilder.build(agent, society));
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
     * @return the legalFormBeanBuilder
     */
    public LegalFormBeanBuilder getLegalFormBeanBuilder() {
        return legalFormBeanBuilder;
    }

    /**
     * @param legalFormBeanBuilder the legalFormBeanBuilder to set
     */
    public void setLegalFormBeanBuilder(LegalFormBeanBuilder legalFormBeanBuilder) {
        this.legalFormBeanBuilder = legalFormBeanBuilder;
    }

    /**
     * @return the paymentModeBeanBuilder
     */
    public PaymentModeBeanBuilder getPaymentModeBeanBuilder() {
        return paymentModeBeanBuilder;
    }

    /**
     * @param paymentModeBeanBuilder the paymentModeBeanBuilder to set
     */
    public void setPaymentModeBeanBuilder(PaymentModeBeanBuilder paymentModeBeanBuilder) {
        this.paymentModeBeanBuilder = paymentModeBeanBuilder;
    }

    /**
     * @return the model
     */
    public Customer getModel() {
        return model;
    }
    /**
     * @param model the model to set
     */
    public void setModel(Customer model) {
        this.model = model;
    }
    /**
     * @return the listModel
     */
    public ObservableList<CustomerBean> getListModel() {
        return listModel;
    }
    /**
     * @param listModel the listModel to set
     */
    public void setListModel(ObservableList<CustomerBean> listModel) {
        this.listModel = listModel;
    }

    /**
     * @return the agentBeanBuilder
     */
    public AgentBeanBuilder getAgentBeanBuilder() {
        return agentBeanBuilder;
    }

    /**
     * @param agentBeanBuilder the agentBeanBuilder to set
     */
    public void setAgentBeanBuilder(AgentBeanBuilder agentBeanBuilder) {
        this.agentBeanBuilder = agentBeanBuilder;
    }

}
