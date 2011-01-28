package be.jsams.client.model.panel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Civility;
import be.jsams.server.service.AgentService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;

/**
 * Search {@link JPanel} for Customer objects.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchAgentPanel extends SearchPanel<Agent, AgentTableMouseListener, AgentService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 4879600730360818739L;
    
    protected static final Log LOGGER = LogFactory.getLog(SearchAgentPanel.class);

    private static final int DEFAULT_COLUMN_SPAN = 1;

    private static final int MAX_CHARACTERS = 50;

    private JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldFunction = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldPhone = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldCity = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldZipCode = new JsamsTextField(MAX_CHARACTERS);
    
    private JComboBox comboBoxCivility;
    
    /**
     * Constructor.
     * 
     * @param m
     *            the {@link Agent}
     * @param l
     *            the {@link AgentTableMouseListener}
     * @param s
     *            the {@link AgentService}
     */
    public SearchAgentPanel(Agent m, AgentTableMouseListener l, AgentService s) {
        super(m, l, s);
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    @Override
    protected JPanel buildSearchCriteriaPanel() {
        FormLayout layout = new FormLayout(
                "right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, 50dlu", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_FUNCTION.getKey(), textFieldFunction);
        List<Civility> allCivilities = JsamsApplicationContext.getCivilityDao().findAll();
        ArrayList<Civility> allWithNull = new ArrayList<Civility>();
        allWithNull.add(null);
        allWithNull.addAll(allCivilities);
        comboBoxCivility = new JComboBox(allWithNull.toArray());
        comboBoxCivility.setRenderer(new TranslatableComboBoxRenderer());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CIVILITY.getKey(), comboBoxCivility);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_CITY.getKey(), textFieldCity);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldZipCode);
        
        return builder.getPanel();
    }

    @Override
    protected void performButtonAdd() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void performButtonModify() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void performButtonRemove() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void performOk() {
        // TODO Auto-generated method stub
        
    }

}
