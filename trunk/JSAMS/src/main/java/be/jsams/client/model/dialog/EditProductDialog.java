package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Product {@link JsamsDialog}, to create or update a Product object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
public class EditProductDialog extends JsamsDialog implements
		JsamsButtonsInterface {

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
	
	private Product model;

	private JsamsButtonsPanel buttonsPanel;
	
	private JComboBox comboBoxProductCategory;

	private ValidationResultModel validationResultModel = new DefaultValidationResultModel();

	private JsamsStatusBar statusBar;

	private JPanel southPanel;

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
		this.model = model;
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		add(buttonsPanel, BorderLayout.SOUTH);
		initComponents();
	}

	public ValidationResultModel getValidationResultModel() {
		return validationResultModel;
	}

	public void setValidationResultModel(
			ValidationResultModel validationResultModel) {
		this.validationResultModel = validationResultModel;
	}

	/**
	 * 
	 * @return the {@link Product}
	 */
	public Product getModel() {
		return model;
	}

	/**
	 * 
	 * @param model
	 *            the {@link Product} to set
	 */
	public void setModel(Product model) {
		this.model = model;
	}

	/**
	 * Initializes all the components
	 */
	private void initComponents() {
		fillData();
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
		builder.appendI15d(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY.getKey(),
				comboBoxProductCategory, 5);
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
				.updateComponentTreeMandatoryBackground(mainPanel);
		add(mainPanel);
		
		buildSouthPanel();
		add(southPanel, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);

	}
	
	private void buildSouthPanel() {
		statusBar = new JsamsStatusBar();
		southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		southPanel.add(buttonsPanel);
		southPanel.add(statusBar);
	}

	public void performCancel() {
		// TODO Auto-generated method stub

	}

	public void performOk() {
		// TODO Auto-generated method stub

	}

	public void performReset() {
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
		comboBoxProductCategory.setRenderer(new NamedComboBoxRenderer());
	}

	private void setMandatoryFields() {
		ValidationComponentUtils.setMandatory(textFieldLabel, true);
		ValidationComponentUtils.setMandatory(textFieldPrice, true);
		ValidationComponentUtils.setMandatory(textFieldVatApplicable, true);
	}

}
