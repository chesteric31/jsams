package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.edit.sale.EditEstimateValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.view.sale.EstimateBeanView;
import be.jsams.server.model.management.Agent;
import be.jsams.server.model.management.Customer;
import be.jsams.server.service.sale.EstimateService;

/**
 * Edit Estimate {@link AbstractEditDialog}, to create or update a
 * {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditEstimateDialog extends AbstractEditDialog<EstimateBean, EditEstimateValidator, EstimateService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 646774314391865523L;

    /**
     * Constructor.
     * 
     * @param title the {@link I18nString} title
     * @param model the {@link EstimateBean} model
     */
    public EditEstimateDialog(final I18nString title, EstimateBean model) {
        super(null, title, model, new EditEstimateValidator(), ApplicationContext.getEstimateService());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveOriginalModel() {
        SocietyBean society = getModel().getSociety();
        CustomerBean customer = getModel().getCustomer();
        AgentBean agent = getModel().getAgent();
        EstimateBean originalModel = new EstimateBean(society, customer, agent);
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(new Customer(customer), society);
        originalModel.setCustomer(customerBean);
        AgentBean agentBean = ApplicationContext.getAgentBeanBuilder().build(new Agent(agent), society);
        originalModel.setAgent(agentBean);
        setOriginalModel(originalModel);
        getOriginalModel().refresh(getModel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel initComponents() {
        EstimateBeanView view = getModel().getView();
        return view.createEditView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        EstimateBean model = getModel();
        super.postPerformOk(model);
    }

}
