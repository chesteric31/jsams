package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditEstimateDialog;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;

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
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        CustomerBean customer = JsamsApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        AgentBean agent = JsamsApplicationContext.getAgentBeanBuilder().build(null, currentSociety);
        EstimateBean bean = new EstimateBean(currentSociety, customer, agent);
        new EditEstimateDialog(JsamsI18nResource.TITLE_EDIT_ESTIMATE, bean);
    }

}
