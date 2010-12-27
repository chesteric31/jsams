package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.table.ProductTableModel;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.forms.layout.FormLayout;

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

	JTable resultTable = null;

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
//		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setLayout(new BorderLayout());
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, p, 3dlu, right:p, 3dlu, p", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.appendSeparator(JsamsI18nResource.SEARCH_CRITERIA
				.getTranslation());
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_LABEL.getKey(),
				textFieldLabel, 5);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_PRICE.getKey(),
				textFieldPrice, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_STOCK_QUANTITY
				.getKey(), textFieldStockQuantity, 1);
		builder.nextLine();
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_REORDER_LEVEL
				.getKey(), textFieldReorderLevel, 1);
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_VAT_APPLICABLE
				.getKey(), textFieldVatApplicable, 1);

		List<ProductCategory> allProductCategories = JsamsApplicationContext
				.getProductCategoryDao().findAll();
		comboBoxProductCategory = new JComboBox(allProductCategories.toArray());
		comboBoxProductCategory.setRenderer(new NamedComboBoxRenderer());
		builder.appendI15d(JsamsI18nResource.LABEL_PRODUCT_CATEGORY.getKey(),
				comboBoxProductCategory, 5);
		builder.nextLine();

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
				buttonOk, buttonCancel, buttonReset }), BorderLayout.CENTER);
		searchCriteriaPanel.add(buttonsPanel);
		
		this.add(searchCriteriaPanel, BorderLayout.NORTH);

		ProductTableModel dataModel = new ProductTableModel();
		Product p = new Product();
		p.setId(1L);
		p.setCategory(new ProductCategory());
		p.setName("BLA");
		p.setPrice(new BigDecimal(123.00));
		p.setQuantityStock(5);
		p.setReorderLevel(1);
		p.setVatApplicable(new BigDecimal(21.00));
		
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(p);
		dataModel.setData(products);
		resultTable = new JTable(dataModel);
		JScrollPane scrollPane = new JScrollPane(resultTable);
		scrollPane.setBorder(new TitledBorder("RESULT"));
		this.add(scrollPane, BorderLayout.CENTER);
	}

	private JsamsButton buildButtonOk() {
		JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		return buttonOk;
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
			}
		});
		return buttonReset;
	}

}
