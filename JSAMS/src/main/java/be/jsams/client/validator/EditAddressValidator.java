package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for Address object.
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
            support.addError(JsamsI18nLabelResource.LABEL_CITY.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(city)) {
            support.addError(JsamsI18nLabelResource.LABEL_CITY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String country = address.getCountry();
        if (ValidationUtils.isBlank(country)) {
            support.addError(JsamsI18nLabelResource.LABEL_COUNTRY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumeric(country)) {
            support.addError(JsamsI18nLabelResource.LABEL_COUNTRY.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String number = address.getNumber();
        if (ValidationUtils.isBlank(number)) {
            support.addError(JsamsI18nLabelResource.LABEL_NUMBER.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isNumeric(number)) {
            support.addError(JsamsI18nLabelResource.LABEL_NUMBER.getTranslation(), JsamsI18nResource.ERROR_IS_NUMERIC
                    .getTranslation());
        }
        String street = address.getStreet();
        if (ValidationUtils.isBlank(street)) {
            support.addError(JsamsI18nLabelResource.LABEL_STREET.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(street)) {
            support.addError(JsamsI18nLabelResource.LABEL_STREET.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String zipCode = address.getZipCode();
        if (ValidationUtils.isBlank(zipCode)) {
            support.addError(JsamsI18nLabelResource.LABEL_ZIP_CODE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isNumeric(zipCode)) {
            support.addError(JsamsI18nLabelResource.LABEL_ZIP_CODE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        return support.getResult();
    }

}
