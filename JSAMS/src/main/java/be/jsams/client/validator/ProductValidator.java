package be.jsams.client.validator;

import java.math.BigDecimal;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Product;
import be.jsams.server.model.ProductCategory;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit product panel.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class ProductValidator implements Validator<Product> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final Product product) {
        PropertyValidationSupport support = new PropertyValidationSupport(product, "");

        ProductCategory category = product.getCategory();
        if (category == null) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        String name = product.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        }
        BigDecimal price = product.getPrice();
        if (price == null || ValidationUtils.isBlank(price.toPlainString())) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_PRICE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        int quantityStock = product.getQuantityStock();
        if (quantityStock == 0 || ValidationUtils.isBlank(Integer.toString(quantityStock))) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        BigDecimal vatApplicable = product.getVatApplicable();
        if (vatApplicable == null || ValidationUtils.isBlank(vatApplicable.toPlainString())) {
            support.addError(JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        return support.getResult();
    }

}
