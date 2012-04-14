package be.jsams.client.validator.edit.management;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit product category panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditProductCategoryValidator implements Validator<ProductCategoryBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final ProductCategoryBean category) {
        PropertyValidationSupport support = new PropertyValidationSupport(category, "");

        String label = category.getLabel();
        String labelFr = category.getLabelFr();
        String labelNl = category.getLabelNl();

        if (ValidationUtils.isBlank(label)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(label) && !StringValidator.validate(label)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        if (ValidationUtils.isBlank(labelFr)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(labelFr) && !StringValidator.validate(labelFr)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        if (ValidationUtils.isBlank(labelNl)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(labelNl) && !StringValidator.validate(labelNl)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        return support.getResult();
    }

}
