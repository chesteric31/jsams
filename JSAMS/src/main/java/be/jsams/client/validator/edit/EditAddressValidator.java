package be.jsams.client.validator.edit;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for address edition.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditAddressValidator implements Validator<AddressBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final AddressBean address) {
        PropertyValidationSupport support = new PropertyValidationSupport(address, "");

        String city = address.getCity();
        if (ValidationUtils.isBlank(city)) {
            support.addError(I18nLabelResource.LABEL_CITY.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(city) && !StringValidator.validate(city)) {
            support.addError(I18nLabelResource.LABEL_CITY.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String country = address.getCountry();
        if (ValidationUtils.isBlank(country)) {
            support.addError(I18nLabelResource.LABEL_COUNTRY.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumeric(country)) {
            support.addError(I18nLabelResource.LABEL_COUNTRY.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String number = address.getNumber();
        if (ValidationUtils.isBlank(number)) {
            support.addError(I18nLabelResource.LABEL_NUMBER.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isNumeric(number)) {
            support.addError(I18nLabelResource.LABEL_NUMBER.getTranslation(), I18nResource.ERROR_IS_NUMERIC
                    .getTranslation());
        }
        String street = address.getStreet();
        if (ValidationUtils.isBlank(street)) {
            support.addError(I18nLabelResource.LABEL_STREET.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(street) && !StringValidator.validate(street)) {
            support.addError(I18nLabelResource.LABEL_STREET.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String zipCode = address.getZipCode();
        if (ValidationUtils.isBlank(zipCode)) {
            support.addError(I18nLabelResource.LABEL_ZIP_CODE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isNumeric(zipCode)) {
            support.addError(I18nLabelResource.LABEL_ZIP_CODE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        return support.getResult();
    }

}
