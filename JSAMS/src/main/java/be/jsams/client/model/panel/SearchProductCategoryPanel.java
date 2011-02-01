package be.jsams.client.model.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.EditProductCategoryDialog;
import be.jsams.client.model.table.ProductCategoryTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.ProductCategoryTableMouseListener;
import be.jsams.server.model.ProductCategory;
import be.jsams.server.service.ProductCategoryService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.mysql.jdbc.StringUtils;

/**
 * Search Product category panel.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class SearchProductCategoryPanel extends
        SearchPanel<ProductCategory, ProductCategoryTableMouseListener, ProductCategoryService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2569005952852992437L;

    protected static final Log LOGGER = LogFactory.getLog(SearchProductCategoryPanel.class);

    private static final int MAX_CHARACTERS = 50;

    private JsamsTextField textFieldLabel = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldLabelFr = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldLabelNl = new JsamsTextField(MAX_CHARACTERS);

    /**
     * Constructor.
     * 
     * @param m
     *            the {@link ProductCategory}
     * @param l
     *            the {@link ProductCategoryTableMouseListener}
     * @param s
     *            the {@link ProductCategoryService}
     * @param showManagementButtons
     *            the boolean to show or not the management buttons panel
     */
    public SearchProductCategoryPanel(ProductCategory m, ProductCategoryTableMouseListener l,
            ProductCategoryService s, final boolean showManagementButtons) {
        super(m, l, s, showManagementButtons);
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    @Override
    protected JPanel buildSearchCriteriaPanel() {
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getKey(), textFieldLabel);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getKey(), textFieldLabelFr);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getKey(), textFieldLabelNl);
        builder.nextLine();
        return builder.getPanel();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        String name = null;
        if (!StringUtils.isNullOrEmpty(textFieldLabel.getText())) {
            name = textFieldLabel.getText();
        }
        String nameFr = null;
        if (!StringUtils.isNullOrEmpty(textFieldLabelFr.getText())) {
            nameFr = textFieldLabelFr.getText();
        }
        String nameNl = null;
        if (!StringUtils.isNullOrEmpty(textFieldLabelNl.getText())) {
            nameNl = textFieldLabelNl.getText();
        }
        final ProductCategory criteria = new ProductCategory();
        criteria.setLabel(name);
        criteria.setLabelFr(nameFr);
        criteria.setLabelNl(nameNl);

        List<ProductCategory> categories = ((ProductCategoryService) super.getService()).findByCriteria(criteria);

        fillTable(categories);
    }

    /**
     * Fills the data table.
     * 
     * @param categories
     *            the {@link ProductCategory} list
     */
    private void fillTable(final List<ProductCategory> categories) {
        ProductCategoryTableModel model = new ProductCategoryTableModel();
        model.setData(categories);
        getResultTable().setModel(model);

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);

        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        new EditProductCategoryDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT_CATEGORY, null);
        refresh();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) getResultTable().getValueAt(selectedRow, 0);
            ProductCategory selectedCategoryProduct = getService().findById(id);
            new EditProductCategoryDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT_CATEGORY, selectedCategoryProduct);
            refresh();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonRemove() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) getResultTable().getValueAt(selectedRow, 0);
            getService().delete(id);
            refresh();
        }
    }
}
