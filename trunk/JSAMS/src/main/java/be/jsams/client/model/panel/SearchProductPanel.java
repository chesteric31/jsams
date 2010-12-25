package be.jsams.client.model.panel;

import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
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

	private static final int MAX_CHARACTERS = 50;

	private static final int MAX_NUMBERS = 10;

	private JsamsTextField textFieldLabel = new JsamsTextField(MAX_CHARACTERS,
			JsamsI18nResource.LABEL_PRODUCT_LABEL);
	private JsamsTextField textFieldPrice = new JsamsTextField(MAX_NUMBERS,
			JsamsI18nResource.LABEL_PRODUCT_PRICE);
	private JsamsTextField textFieldStockQuantity = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_PRODUCT_STOCK_QUANTITY);
	private JsamsTextField textFieldReorderLevel = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_PRODUCT_REORDER_LEVEL);
	private JsamsTextField textFieldVatApplicable = new JsamsTextField(
			MAX_NUMBERS, JsamsI18nResource.LABEL_PRODUCT_VAT_APPLICABLE);

	private JComboBox comboBoxProductCategory;

	public SearchProductPanel() {
		super();
		initComponents();
	}

	private void initComponents() {
		FormLayout layout = new FormLayout(
				"right:p, 3dlu, p, 3dlu, right:p, 3dlu, p", "p");
		DefaultFormBuilder builder = new DefaultFormBuilder(layout);
		builder.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_CRITERIA
				.getTranslation()));
		builder.append(textFieldLabel.getLabel(), textFieldLabel, 5);
		builder.nextLine();
		builder.append(textFieldPrice.getLabel(), textFieldPrice, 1);
		builder.append(textFieldStockQuantity.getLabel(),
				textFieldStockQuantity, 1);
		builder.nextLine();
		builder.append(textFieldReorderLevel.getLabel(), textFieldReorderLevel,
				1);
		builder.append(textFieldVatApplicable.getLabel(),
				textFieldVatApplicable, 1);

		List<ProductCategory> allProductCategories = JsamsApplicationContext
				.getProductCategoryDao().findAll();
		comboBoxProductCategory = new JComboBox(allProductCategories.toArray());
		comboBoxProductCategory.setRenderer(new NamedComboBoxRenderer());
		builder.append(JsamsI18nResource.LABEL_PRODUCT_CATEGORY
				.getTranslation(), comboBoxProductCategory, 5);

		add(builder.getPanel());
	}

}
