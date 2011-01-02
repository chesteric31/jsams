package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.border.TitledBorder;
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
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.listener.TableMouseListener;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;
import com.mysql.jdbc.StringUtils;

/**
 * Search {@link JPanel} for Product objects.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchProductPanel extends JPanel implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2222078506888522042L;

    protected final Log LOGGER = LogFactory.getLog(this.getClass());

    private static final int DEFAULT_V_GAP = 10;

    private static final int MAX_CHARACTERS = 50;

    private static final int MAX_NUMBERS = 10;

    public JsamsTextField textFieldLabel = new JsamsTextField(MAX_CHARACTERS);
    public JsamsTextField textFieldPrice = new JsamsTextField(MAX_NUMBERS);
    public JsamsTextField textFieldStockQuantity = new JsamsTextField(
            MAX_NUMBERS);
    public JsamsTextField textFieldReorderLevel = new JsamsTextField(
            MAX_NUMBERS);
    public JsamsTextField textFieldVatApplicable = new JsamsTextField(
            MAX_NUMBERS);
    
    private JsamsButtonsPanel buttonsPanel;

    JsamsTable resultTable = null;

    public JComboBox comboBoxProductCategory;

    private JsamsButton buttonAdd = null;
    private JsamsButton buttonRemove = null;
    private JsamsButton buttonModify = null;

    /**
     * Constructor
     */
    public SearchProductPanel() {
        super();
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        setLayout(new BorderLayout());
        FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, "
                + "right:p, 3dlu, p:grow, 3dlu, "
                + "right:p, 3dlu, p:grow, 3dlu, " + "right:p, 3dlu, p:grow",
                "p");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout,
                JsamsFrame.RESOURCE_BUNDLE);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getKey(),
                textFieldLabel, 5);
        List<ProductCategory> allProductCategories = JsamsApplicationContext
                .getProductCategoryDao().findAll();
        ArrayList<ProductCategory> allWithNull = new ArrayList<ProductCategory>();
        allWithNull.add(null);
        allWithNull.addAll(allProductCategories);
        comboBoxProductCategory = new JComboBox(allWithNull.toArray());
        comboBoxProductCategory.setRenderer(new NamedComboBoxRenderer());
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY
                .getKey(), comboBoxProductCategory, 5);
        builder.nextLine();
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_PRICE.getKey(),
                textFieldPrice, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY
                .getKey(), textFieldStockQuantity, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_REORDER_LEVEL
                .getKey(), textFieldReorderLevel, 1);
        builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_VAT_APPLICABLE
                .getKey(), textFieldVatApplicable, 1);

        JPanel searchCriteriaPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(DEFAULT_V_GAP);
        searchCriteriaPanel.setLayout(gridLayout);
        searchCriteriaPanel.add(builder.getPanel());

        JPanel northPanel = new JPanel();
        BorderLayout buttonsLayout = new BorderLayout();
        buttonsLayout.setVgap(DEFAULT_V_GAP);
        northPanel.setLayout(buttonsLayout);
        northPanel.add(new JSeparator(), BorderLayout.NORTH);
        buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
        northPanel.add(buttonsPanel);
        searchCriteriaPanel.add(buttonsPanel);

        this.add(searchCriteriaPanel, BorderLayout.NORTH);

        resultTable = new JsamsTable(true);
        resultTable.addMouseListener(new TableMouseListener());
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS
                .getTranslation()));
        this.add(scrollPane, BorderLayout.CENTER);

        buttonAdd = buildButtonAdd();
        buttonRemove = buildButtonRemove();
        buttonModify = buildButtonModify();

        this.add(ButtonBarFactory.buildCenteredBar(new JButton[] { buttonAdd,
                buttonRemove, buttonModify }), BorderLayout.SOUTH);
    }

    private void fillTable(final List<Product> products) {
        ProductTableModel model = new ProductTableModel();
        model.setData(products);
        resultTable.setModel(model);

        JTableHeader tableHeader = resultTable.getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer)
                .setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        resultTable.setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        resultTable.setDefaultRenderer(Long.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Integer.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(Double.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(BigDecimal.class, defaultCellRenderer);
        resultTable.setDefaultRenderer(String.class, defaultCellRenderer);

        resultTable.repaint();
    }

    private JsamsButton buildButtonAdd() {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX
                + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT,
                        null);
            }
        });
        return buttonAdd;
    }

    private JsamsButton buildButtonRemove() {
        JsamsButton buttonRemove = new JsamsButton(IconUtil.MENU_ICON_PREFIX
                + "actions/list-remove.png");
        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        return buttonRemove;
    }

    private JsamsButton buildButtonModify() {
        JsamsButton buttonModify = new JsamsButton(IconUtil.MENU_ICON_PREFIX
                + "apps/accessories-text-editor.png");
        buttonModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = resultTable.getSelectedRow();
                if (selectedRow > -1) {
                    Long id = (Long) resultTable.getValueAt(selectedRow, 0);
                    Product selectedProduct = JsamsApplicationContext
                            .getProductService().findById(id);
                    new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT,
                            selectedProduct);
                }
            }
        });
        return buttonModify;
    }

    public void performCancel() {
        // TODO Auto-generated method stub

    }

    public void performOk() {
        String name = textFieldLabel.getText();
        BigDecimal price = null;
        if (!StringUtils.isNullOrEmpty(textFieldPrice.getText())) {
            price = new BigDecimal(textFieldPrice.getText());
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
        if (!StringUtils.isNullOrEmpty(textFieldVatApplicable.getText())) {
            vatApplicable = new BigDecimal(textFieldVatApplicable.getText());
        }
        ProductCategory category = (ProductCategory) comboBoxProductCategory
                .getSelectedItem();
        final Product criteria = new Product();
        criteria.setCategory(category);
        criteria.setName(name);
        criteria.setPrice(price);
        criteria.setQuantityStock(stockQuantity);
        criteria.setReorderLevel(reorderLevel);
        criteria.setVatApplicable(vatApplicable);
        List<Product> products = JsamsApplicationContext.getProductService()
                .findByCriteria(criteria);

        fillTable(products);
    }

    public void performReset() {
        Class<?> clazz = this.getClass();
        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            try {
                Object value = field.get(this);
                if (value instanceof JsamsTextField) {
                    ((JsamsTextField) value).setText(null);
                } else if (value instanceof JComboBox) {
                    ((JComboBox) value).setSelectedIndex(0);
                }
            } catch (IllegalArgumentException e1) {
                LOGGER.error(e1);
            } catch (IllegalAccessException e1) {
                LOGGER.error(e1);
            }
        }
    }

}
