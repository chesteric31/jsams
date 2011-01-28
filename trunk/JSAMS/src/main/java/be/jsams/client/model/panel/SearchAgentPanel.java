package be.jsams.client.model.panel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditAgentDialog;
import be.jsams.client.model.table.AgentTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.server.model.Address;
import be.jsams.server.model.Agent;
import be.jsams.server.model.Civility;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.service.AgentService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.mysql.jdbc.StringUtils;

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
        new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, null);
        refresh();
    }

    @Override
    protected void performButtonModify() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) getResultTable().getValueAt(selectedRow, 0);
            Agent selectedAgent = getService().findById(id);
            new EditAgentDialog(JsamsI18nResource.TITLE_EDIT_AGENT, selectedAgent);
            refresh();
        }
    }

    @Override
    protected void performButtonRemove() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) getResultTable().getValueAt(selectedRow, 0);
            getService().delete(id);
            refresh();
        }
    }

    @Override
    public void performOk() {
        String name = null;
        Address address = null;
        ContactInformation contactInformation = null;
        String function = null;
        if (!StringUtils.isNullOrEmpty(textFieldName.getText())) {
            name = textFieldName.getText();
        }
        if (!StringUtils.isNullOrEmpty(textFieldZipCode.getText())) {
            address = new Address();
            address.setZipCode(Integer.parseInt(textFieldZipCode.getText()));
        }
        if (!StringUtils.isNullOrEmpty(textFieldCity.getText())) {
            if (address == null) {
                address = new Address();
            }
            address.setCity(textFieldCity.getText());
        }

        if (!StringUtils.isNullOrEmpty(textFieldFunction.getText())) {
            function = textFieldFunction.getText();
        }
        if (!StringUtils.isNullOrEmpty(textFieldPhone.getText())) {
            contactInformation = new ContactInformation();
            contactInformation.setPhone(textFieldPhone.getText());
        }
        Civility civility = (Civility) comboBoxCivility.getSelectedItem();
        final Agent criteria = new Agent();
        criteria.setCivility(civility);
        criteria.setName(name);
        criteria.setFunction(function);
        criteria.setAddress(address);
        criteria.setContactInformation(contactInformation);

        List<Agent> agents = ((AgentService) super.getService()).findByCriteria(criteria);

        fillTable(agents);
    }
    
    /**
     * Fills the data table.
     * 
     * @param agents
     *            the {@link Agent} list
     */
    private void fillTable(final List<Agent> agents) {
        AgentTableModel model = new AgentTableModel();
        model.setData(agents);
        getResultTable().setModel(model);

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Integer.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Double.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(BigDecimal.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);

        getResultTable().repaint();
    }

}
