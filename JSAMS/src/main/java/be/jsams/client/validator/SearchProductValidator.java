package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for search product panel.
 *
 * @author chesteric31
 * @version $Rev: 689 $ $Date::                  $ $Author$
 */
public class SearchProductValidator implements Validator<ProductBean> {

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
        if (name != null && !ValidationUtils.isAlphanumericSpace(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_PRODUCT_LABEL.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        return support.getResult();
    }

}