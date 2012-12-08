package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditEstimateDialog;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.EstimateMediator;

/**
 * {@link AbstractAction} to launch {@link EditEstimateDialog}.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class NewEstimateAction extends AbstractAction {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -3183414113449758536L;

    /**
     * {@inheritDoc}
     */
    public void actionPerformed(ActionEvent arg0) {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customer = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = ApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
        EstimateBean bean = new EstimateBean(currentSociety, customer, agent);
        EstimateMediator mediator = new EstimateMediator();
        mediator.setEstimateBean(bean);
        bean.setMediator(mediator);
        new EditEstimateDialog(I18nResource.TITLE_EDIT_ESTIMATE, bean);
    }

}
