package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.TranslatableComboBoxRenderer;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.validator.ProductValidator;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;
import be.jsams.server.service.ProductService;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Product {@link JsamsDialog}, to create or update a Product object.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class EditProductDialog extends
		EditDialog<Product, ProductValidator, ProductService> {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = -5931469580616365674L;

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

	private JComboBox comboBoxProductCategory;

	/**
	 * Constructor
	 * 
	 * @param title
	 *            the {@link I18nString} title
	 * @param model
	 *            the {@link Product} model
	 */
	public EditProductDialog(final I18nString title, Product model) {
		super(null, title);
		super.setModel(model);
		super.setValidator(new ProductValidator());
		super.setService(JsamsApplicationContext.getProductService());
		initComponents();
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	/**
	 * Initializes all the components
	 */
	private void initComponents() {
		fillData();
		FormLayout layout = new FormLayout("right:p, 3dlu, p:grow, 3dlu, "
				+ "right:p, 3dlu, p:grow", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout,
				JsamsFrame.RESOURCE_BUNDLE);
		builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getKey(),
				textFieldLabel, 5);
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

		setMandatoryFields();

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(builder.getPanel(), BorderLayout.CENTER);
		ValidationComponentUtils
				.updateComponentTreeMandatoryBorder(this);
		add(mainPanel);

		pack();
	}

	public void performOk() {
		// TODO Auto-generated method stub

	}

	private void fillData() {
		List<ProductCategory> allProductCategories = JsamsApplicationContext
				.getProductCategoryDao().findAll();
		ArrayList<ProductCategory> allWithNull = new ArrayList<ProductCategory>();
		allWithNull.add(null);
		allWithNull.addAll(allProductCategories);
		comboBoxProductCategory = new JComboBox(allWithNull.toArray());
		if (getModel() != null) {
			Product product = JsamsApplicationContext.getProductService()
					.findById(getModel().getId());
			comboBoxProductCategory.setSelectedItem(product.getCategory());
			textFieldLabel.setText(product.getName());
			textFieldPrice.setText(product.getPrice().toPlainString());
			textFieldReorderLevel.setText(Integer.toString(product
					.getReorderLevel()));
			textFieldStockQuantity.setText(Integer.toString(product
					.getQuantityStock()));
			textFieldVatApplicable.setText(product.getVatApplicable()
					.toPlainString());
		}
		comboBoxProductCategory.setRenderer(new TranslatableComboBoxRenderer());
	}

	private void setMandatoryFields() {
		ValidationComponentUtils.setMandatory(textFieldLabel, true);
		ValidationComponentUtils.setMandatory(textFieldPrice, true);
		ValidationComponentUtils.setMandatory(textFieldVatApplicable, true);
		ValidationComponentUtils.setMandatory(textFieldStockQuantity, true);
		ValidationComponentUtils.setMandatory(comboBoxProductCategory, true);
		comboBoxProductCategory.setBorder(BorderFactory
				.createLineBorder(ValidationComponentUtils
						.getMandatoryForeground()));
	}

}
