package be.jsams.client.validator.edit;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.validator.StringValidator;

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
public class EditProductValidator implements Validator<ProductBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final ProductBean product) {
        PropertyValidationSupport support = new PropertyValidationSupport(product, "");

        ProductCategoryBean category = product.getCategory();
        if (category == null) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        String name = product.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(name) && !StringValidator.validate(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        Double price = product.getPrice();
        if (price == null || ValidationUtils.isBlank(price.toString())) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_PRICE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        int quantityStock = product.getQuantityStock();
        if (quantityStock == 0 || ValidationUtils.isBlank(Integer.toString(quantityStock))) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_STOCK_QUANTITY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        Double vatApplicable = product.getVatApplicable();
        if (vatApplicable == null || ValidationUtils.isBlank(vatApplicable.toString())) {
            support.addError(JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        return support.getResult();
    }

}
