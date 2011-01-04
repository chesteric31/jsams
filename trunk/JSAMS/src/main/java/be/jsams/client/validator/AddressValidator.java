package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Address;

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
public class AddressValidator implements Validator<Address> {

	public ValidationResult validate(final Address address) {
		PropertyValidationSupport support = new PropertyValidationSupport(
				address, "");

		String city = address.getCity();
		if (ValidationUtils.isBlank(city)) {
			support.addError(
					JsamsI18nLabelResource.LABEL_CITY.getTranslation(),
					JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
		}
		String country = address.getCountry();
		if (ValidationUtils.isBlank(country)) {
			support.addError(JsamsI18nLabelResource.LABEL_COUNTRY
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		String number = address.getNumber();
		if (ValidationUtils.isBlank(number)) {
			support.addError(JsamsI18nLabelResource.LABEL_NUMBER
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		String street = address.getStreet();
		if (ValidationUtils.isBlank(street)) {
			support.addError(JsamsI18nLabelResource.LABEL_STREET
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		if (ValidationUtils.isBlank(Integer.toString(address.getZipCode()))
				|| address.getZipCode() == 0) {
			support.addError(JsamsI18nLabelResource.LABEL_ZIP_CODE
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		return support.getResult();
	}

}
