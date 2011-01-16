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
import be.jsams.client.model.dialog.EditProductDialog;
import be.jsams.client.model.table.ProductTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.ProductTableMouseListener;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;
import be.jsams.server.service.ProductService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.mysql.jdbc.StringUtils;

/**
 * Search Product panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchProductPanel extends SearchPanel<Product, ProductTableMouseListener, ProductService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2222078506888522042L;

    protected static final Log LOGGER = LogFactory.getLog(SearchProductPanel.class);

    private static final int DEFAULT_COLUMN_SPAN = 1;

    private static final int MAX_CHARACTERS = 50;

    private static final int MAX_NUMBERS = 10;

    private JsamsTextField textFieldLabel = new JsamsTextField(MAX_CHARACTERS);
    private JsamsTextField textFieldPrice = new JsamsTextField(MAX_NUMBERS);
    private JsamsTextField textFieldStockQuantity = new JsamsTextField(MAX_NUMBERS);
    private JsamsTextField textFieldReorderLevel = new JsamsTextField(MAX_NUMBERS);
    private JsamsTextField textFieldVatApplicable = new JsamsTextField(MAX_NUMBERS);

    private JComboBox comboBoxProductCategory;

    /**
     * Constructor
     */
    public SearchProductPanel() {
        super();
        super.setService(JsamsApplicationContext.getProductService());
        super.setMouseListener(new ProductTableMouseListener());
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    /**
     * {@inheritDoc}
     */
    protected JPanel buildSearchCriteriaPanel() {
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, " + "right:p, 3dlu, p:grow, 3dlu, "
                + "right:p, 3dlu, p:grow, 3dlu, " + "right:p, 3dlu, p:grow", "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        final int maxColumnSpan = 5;
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getKey(), textFieldLabel, maxColumnSpan);
        List<ProductCategory> allProductCategories = JsamsApplicationContext.getProductCategoryDao().findAll();
        ArrayList<ProductCategory> allWithNull = new ArrayList<ProductCategory>();
        allWithNull.add(null);
        allWithNull.addAll(allProductCategories);
        comboBoxProductCategory = new JComboBox(allWithNull.toArray());
        comboBoxProductCategory.setRenderer(new TranslatableComboBoxRenderer());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY.getKey(), comboBoxProductCategory,
                maxColumnSpan);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_PRICE.getKey(), textFieldPrice, DEFAULT_COLUMN_SPAN);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getKey(), textFieldStockQuantity,
                DEFAULT_COLUMN_SPAN);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_REORDER_LEVEL.getKey(), textFieldReorderLevel,
                DEFAULT_COLUMN_SPAN);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_VAT_APPLICABLE.getKey(), textFieldVatApplicable,
                DEFAULT_COLUMN_SPAN);

        return builder.getPanel();
    }

    /**
     * Fills the data table.
     * 
     * @param products
     *            the {@link Product} list
     */
    private void fillTable(final List<Product> products) {
        ProductTableModel model = new ProductTableModel();
        model.setData(products);
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

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        String name = textFieldLabel.getText();
        BigDecimal price = null;
        String priceString = textFieldPrice.getText();
        if (!StringUtils.isNullOrEmpty(priceString)) {
            BigDecimal bigDecimal = new BigDecimal(priceString);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            price = bigDecimal;
        }
        int reorderLevel = -1;
        if (!StringUtils.isNullOrEmpty(textFieldReorderLevel.getText())) {
            reorderLevel = Integer.parseInt(textFieldReorderLevel.getText());
        }
        int stockQuantity = -1;
        if (!StringUtils.isNullOrEmpty(textFieldStockQuantity.getText())) {
            stockQuantity = Integer.parseInt(textFieldStockQuantity.getText());
        }
        BigDecimal vatApplicable = null;
        String vatApplicableString = textFieldVatApplicable.getText();
        if (!StringUtils.isNullOrEmpty(vatApplicableString)) {
            BigDecimal bigDecimal = new BigDecimal(vatApplicableString);
            bigDecimal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            vatApplicable = bigDecimal;
        }
        ProductCategory category = (ProductCategory) comboBoxProductCategory.getSelectedItem();
        final Product criteria = new Product();
        criteria.setCategory(category);
        criteria.setName(name);
        criteria.setPrice(price);
        criteria.setQuantityStock(stockQuantity);
        criteria.setReorderLevel(reorderLevel);
        criteria.setVatApplicable(vatApplicable);
        
        List<Product> products = ((ProductService) super.getService()).findByCriteria(criteria);

        fillTable(products);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT, null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) resultTable.getValueAt(selectedRow, 0);
            Product selectedProduct = getService().findById(id);
            new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT, selectedProduct);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonRemove() {
        int selectedRow = resultTable.getSelectedRow();
        if (selectedRow > -1) {
            Long id = (Long) resultTable.getValueAt(selectedRow, 0);
            getService().delete(id);
        }
    }

}
