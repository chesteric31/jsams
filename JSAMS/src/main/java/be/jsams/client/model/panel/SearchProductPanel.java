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

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.table.ProductTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
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
public class SearchProductPanel extends JPanel {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 2222078506888522042L;

	private static final int DEFAULT_V_GAP = 10;

	private static final int MAX_CHARACTERS = 50;

	private static final int MAX_NUMBERS = 10;

	private JsamsTextField textFieldLabel = new JsamsTextField(MAX_CHARACTERS);
	private JsamsTextField textFieldPrice = new JsamsTextField(MAX_NUMBERS);
	private JsamsTextField textFieldStockQuantity = new JsamsTextField(
			MAX_NUMBERS);
	private JsamsTextField textFieldReorderLevel = new JsamsTextField(
			MAX_NUMBERS);
	private JsamsTextField textFieldVatApplicable = new JsamsTextField(
			MAX_NUMBERS);

	JsamsButton buttonOk = null;
	JsamsButton buttonCancel = null;
	JsamsButton buttonReset = null;

	JsamsTable resultTable = null;

	private JComboBox comboBoxProductCategory;

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
		builder.appendSeparator(JsamsI18nResource.SEARCH_CRITERIA
				.getTranslation());
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_LABEL.getKey(),
				textFieldLabel, 5);
		List<ProductCategory> allProductCategories = JsamsApplicationContext
				.getProductCategoryDao().findAll();
		ArrayList<ProductCategory> allWithNull = new ArrayList<ProductCategory>();
		allWithNull.add(null);
		allWithNull.addAll(allProductCategories);
		comboBoxProductCategory = new JComboBox(allWithNull.toArray());
		comboBoxProductCategory.setRenderer(new NamedComboBoxRenderer());
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_CATEGORY.getKey(),
				comboBoxProductCategory, 5);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_PRICE.getKey(),
				textFieldPrice, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_STOCK_QUANTITY
				.getKey(), textFieldStockQuantity, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_REORDER_LEVEL
				.getKey(), textFieldReorderLevel, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_VAT_APPLICABLE
				.getKey(), textFieldVatApplicable, 1);

		JPanel searchCriteriaPanel = new JPanel();
		GridLayout gridLayout = new GridLayout(2, 1);
		gridLayout.setVgap(DEFAULT_V_GAP);
		searchCriteriaPanel.setLayout(gridLayout);
		searchCriteriaPanel.add(builder.getPanel());

		buttonOk = buildButtonOk();
		buttonCancel = buildButtonCancel();
		buttonReset = buildButtonReset();

		JPanel buttonsPanel = new JPanel();
		BorderLayout buttonsLayout = new BorderLayout();
		buttonsLayout.setVgap(DEFAULT_V_GAP);
		buttonsPanel.setLayout(buttonsLayout);
		buttonsPanel.add(new JSeparator(), BorderLayout.NORTH);
		buttonsPanel.add(ButtonBarFactory.buildCenteredBar(new JButton[] {
				buttonOk, buttonCancel, buttonReset }));
		searchCriteriaPanel.add(buttonsPanel);

		this.add(searchCriteriaPanel, BorderLayout.NORTH);

		resultTable = new JsamsTable(true);
		JScrollPane scrollPane = new JScrollPane(resultTable);
		scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS
				.getTranslation()));
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private JsamsButton buildButtonOk() {
		JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textFieldLabel.getText();
				BigDecimal price = null;
				if (!StringUtils.isNullOrEmpty(textFieldPrice.getText())) {
					price = new BigDecimal(textFieldPrice.getText());
				}
				int reorderLevel = -1;
				if (!StringUtils.isNullOrEmpty(textFieldReorderLevel.getText())) {
					reorderLevel = Integer.parseInt(textFieldReorderLevel
							.getText());
				}
				int stockQuantity = -1;
				if (!StringUtils
						.isNullOrEmpty(textFieldStockQuantity.getText())) {
					stockQuantity = Integer.parseInt(textFieldStockQuantity
							.getText());
				}
				BigDecimal vatApplicable = null;
				if (!StringUtils
						.isNullOrEmpty(textFieldVatApplicable.getText())) {
					vatApplicable = new BigDecimal(textFieldVatApplicable
							.getText());
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
				List<Product> products = JsamsApplicationContext
						.getProductService().findByCriteria(criteria);
				
				fillTable(products);
			}
		});
		return buttonOk;
	}
	
	private void fillTable(final List<Product> products) {
		ProductTableModel model = new ProductTableModel();
		model.setData(products);
		resultTable.setModel(model);

		JTableHeader tableHeader = resultTable.getTableHeader();
		TableCellRenderer headerRenderer = tableHeader
				.getDefaultRenderer();

		((DefaultTableCellRenderer) headerRenderer)
				.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		resultTable.setAutoCreateRowSorter(true);
		JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
		resultTable.setDefaultRenderer(Long.class,
				defaultCellRenderer);
		resultTable.setDefaultRenderer(Integer.class,
				defaultCellRenderer);
		resultTable.setDefaultRenderer(Double.class,
				defaultCellRenderer);
		resultTable.setDefaultRenderer(BigDecimal.class,
				defaultCellRenderer);
		resultTable.setDefaultRenderer(String.class,
				defaultCellRenderer);

		resultTable.repaint();
	}

	private JsamsButton buildButtonCancel() {
		JsamsButton buttonCancel = new JsamsButton(
				JsamsI18nResource.BUTTON_CANCEL);
		buttonCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		return buttonCancel;
	}

	private JsamsButton buildButtonReset() {
		JsamsButton buttonReset = new JsamsButton(
				JsamsI18nResource.BUTTON_RESET);
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		return buttonReset;
	}

}
