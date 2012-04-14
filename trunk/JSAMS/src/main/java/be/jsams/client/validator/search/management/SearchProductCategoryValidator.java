package be.jsams.client.validator.search.management;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for search product category panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchProductCategoryValidator implements Validator<ProductCategoryBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final ProductCategoryBean category) {
        PropertyValidationSupport support = new PropertyValidationSupport(category, "");

        String label = category.getLabel();
        String labelFr = category.getLabelFr();
        String labelNl = category.getLabelNl();

        if (label != null && !ValidationUtils.isAlphanumericSpace(label) && !StringValidator.validate(label)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_EN.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        if (labelFr != null && !ValidationUtils.isAlphanumericSpace(labelFr) && !StringValidator.validate(labelFr)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_FR.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        if (labelNl != null && !ValidationUtils.isAlphanumericSpace(labelNl) && !StringValidator.validate(labelNl)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_CATEGORY_NL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        return support.getResult();
    }

}
