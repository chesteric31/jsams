package be.jsams.client.model.dialog;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.validator.AgentValidator;
import be.jsams.server.model.Agent;
import be.jsams.server.service.AgentService;

/**
 * Edit Agent {@link EditDialog}, to create or update a {@link Agent} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditAgentDialog extends EditDialog<Agent, AgentValidator, AgentService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2514472162732492120L;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link Agent} model
     */
    public EditAgentDialog(final I18nString title, Agent model) {
        super(null, title, "apps/system-users.png");
        super.setModel(model);
        super.setValidator(new AgentValidator());
        super.setService(JsamsApplicationContext.getAgentService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    @Override
    protected void initComponents() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void performOk() {
        // TODO Auto-generated method stub
        
    }

}
