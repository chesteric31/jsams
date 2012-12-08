package be.jsams.client.validator.search.management;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.CustomerBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for search customer panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCustomerValidator implements Validator<CustomerBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final CustomerBean customer) {
        PropertyValidationSupport support = new PropertyValidationSupport(customer, "");

        String name = customer.getName();
        if (name != null && !ValidationUtils.isAlphanumericSpace(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        String phone = customer.getContactInformation().getPhone();
        if (phone != null && !ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        AddressBean billingAddress = customer.getBillingAddress();
        String zipCode = billingAddress.getZipCode();
        if (zipCode != null && !ValidationUtils.isNumeric(zipCode)) {
            support.addError(I18nLabelResource.LABEL_ZIP_CODE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        return support.getResult();
    }

}
