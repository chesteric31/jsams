package be.jsams.client.model.dialog;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.validator.AgentValidator;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.AgentBean;
import be.jsams.common.bean.model.CivilityBean;
import be.jsams.common.bean.view.AgentBeanView;
import be.jsams.common.bean.view.CivilityBeanView;
import be.jsams.server.model.Civility;
import be.jsams.server.service.AgentService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Agent {@link AbstractEditDialog}, to create or update a {@link AgentBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditAgentDialog extends AbstractEditDialog<AgentBean, AgentValidator, AgentService> {

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
     *            the {@link AgentBean} model
     */
    public EditAgentDialog(final I18nString title, AgentBean model) {
        super(null, title, "categories/applications-development.png");
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
        AgentBeanView view = getModel().getView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        AgentBean bean = getModel();
        AbstractIdentityBean<Civility, CivilityBeanView> civility = bean.getCivility().getSelection();
        if (civility != null) {
            bean.setCivility((CivilityBean) civility);
        }
        super.postPerformOk(bean);
    }

}
