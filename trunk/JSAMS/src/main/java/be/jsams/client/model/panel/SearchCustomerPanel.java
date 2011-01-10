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
import be.jsams.client.model.dialog.EditCustomerDialog;
import be.jsams.client.model.table.CustomerTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.CustomerTableMouseListener;
import be.jsams.server.model.Address;
import be.jsams.server.model.ContactInformation;
import be.jsams.server.model.Customer;
import be.jsams.server.model.LegalForm;
import be.jsams.server.model.PaymentMode;
import be.jsams.server.service.CustomerService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.mysql.jdbc.StringUtils;

/**
 * Search {@link JPanel} for Customer objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCustomerPanel extends SearchPanel<Customer, CustomerTableMouseListener, CustomerService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2222078506888522042L;

    protected static final Log LOGGER = LogFactory.getLog(SearchCustomerPanel.class);

    private static final int DEFAULT_COLUMN_SPAN = 1;

    private static final int MAX_CHARACTERS = 50;

    private static final int MAX_NUMBERS = 10;

    private JsamsTextField textFieldName = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldBillingZipCode = new JsamsTextField(MAX_NUMBERS);
    private JsamsTextField textFieldPhone = new JsamsTextField(MAX_NUMBERS);

    private JComboBox comboBoxPaymentMode;
    private JComboBox comboBoxLegalForm;

    /**
     * Constructor
     */
    public SearchCustomerPanel() {
        super();
        super.setService(JsamsApplicationContext.getCustomerService());
        super.setMouseListener(new CustomerTableMouseListener());
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JPanel buildSearchCriteriaPanel() {
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_NAME.getKey(), textFieldName, maxColumnSpan);
        builder.nextLine();
        List<PaymentMode> allPaymentModes = JsamsApplicationContext.getPaymentModeDao().findAll();
        ArrayList<PaymentMode> allWithNull = new ArrayList<PaymentMode>();
        allWithNull.add(null);
        allWithNull.addAll(allPaymentModes);
        comboBoxPaymentMode = new JComboBox(allWithNull.toArray());
        comboBoxPaymentMode.setRenderer(new TranslatableComboBoxRenderer());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getKey(), comboBoxPaymentMode);
        List<LegalForm> allLegalForms = JsamsApplicationContext.getLegalFormDao().findAll();
        ArrayList<LegalForm> allFormsWithNulls = new ArrayList<LegalForm>();
        allFormsWithNulls.add(null);
        allFormsWithNulls.addAll(allLegalForms);
        comboBoxLegalForm = new JComboBox(allFormsWithNulls.toArray());
        comboBoxLegalForm.setRenderer(new TranslatableComboBoxRenderer());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_LEGAL_FORM.getKey(), comboBoxLegalForm);
        builder.nextLine();
        builder
                .appendI15d(JsamsI18nLabelResource.LABEL_ZIP_CODE.getKey(), textFieldBillingZipCode,
                        DEFAULT_COLUMN_SPAN);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PHONE.getKey(), textFieldPhone, DEFAULT_COLUMN_SPAN);

        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        new EditCustomerDialog(JsamsI18nResource.TITLE_EDIT_CUSTOMER, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) resultTable.getValueAt(selectedRow, 0);
            Customer selectedCustomer = getService().findById(id);
            new EditCustomerDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT, selectedCustomer);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonRemove() {
        // TODO Auto-generated method stub
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {        
        String name = null;
        Address address = null;
        ContactInformation contactInformation = null;
        if (!StringUtils.isNullOrEmpty(textFieldName.getText())) {
            name = textFieldName.getText();
        }
        if (!StringUtils.isNullOrEmpty(textFieldBillingZipCode.getText())) {
            address = new Address();
            address.setZipCode(Integer.parseInt(textFieldBillingZipCode.getText()));
        }
        if (!StringUtils.isNullOrEmpty(textFieldPhone.getText())) {
            contactInformation = new ContactInformation();
            contactInformation.setPhone(textFieldPhone.getText());
        }
        PaymentMode paymentMode = (PaymentMode) comboBoxPaymentMode.getSelectedItem();
        LegalForm legalForm = (LegalForm) comboBoxLegalForm.getSelectedItem();
        final Customer criteria = new Customer();
        criteria.setPaymentMode(paymentMode);
        criteria.setLegalForm(legalForm);
        criteria.setName(name);
        criteria.setBillingAddress(address);
        criteria.setContactInformation(contactInformation);

        List<Customer> customers = ((CustomerService) super.getService()).findByCriteria(criteria);

        fillTable(customers);
    }
    /**
     * Fills the data table.
     * 
     * @param customers
     *            the {@link Customer} list
     */
    private void fillTable(final List<Customer> customers) {
        CustomerTableModel model = new CustomerTableModel();
        model.setData(customers);
        resultTable.setModel(model);

        JTableHeader tableHeader = resultTable.getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        resultTable.setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        resultTable.setDefaultRenderer(Long.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Integer.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Double.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(BigDecimal.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(String.class, defaultCellRenderer);

        resultTable.repaint();
    }

}
