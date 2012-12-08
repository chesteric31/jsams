package be.jsams.client.validator.search.management;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for search product panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchProductValidator implements Validator<ProductBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final ProductBean product) {
        PropertyValidationSupport support = new PropertyValidationSupport(product, "");

        ProductCategoryBean category = product.getCategory();
        if (category == null) {
            support.addError(I18nLabelResource.LABEL_PRODUCT_CATEGORY.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        String name = product.getName();
        if (name != null && !ValidationUtils.isAlphanumericSpace(name) && !StringValidator.validate(name)) {
            support.addError(I18nLabelResource.LABEL_PRODUCT_LABEL.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        return support.getResult();
    }

}
