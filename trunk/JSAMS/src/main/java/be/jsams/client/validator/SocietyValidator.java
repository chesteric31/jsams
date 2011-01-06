package be.jsams.client.validator;

import java.math.BigDecimal;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Address;
import be.jsams.server.model.Society;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit product panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::   $Author$
 */
public class SocietyValidator implements Validator<Society> {

	/**
	 * {@inheritDoc}
	 */
	public ValidationResult validate(final Society society) {
		PropertyValidationSupport support = new PropertyValidationSupport(
				society, "");

		if (ValidationUtils.isBlank(society.getName())) {
			support.addError(
					JsamsI18nLabelResource.LABEL_NAME.getTranslation(),
					JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
		}
		if (ValidationUtils.isBlank(society.getActivity())) {
			support.addError(JsamsI18nLabelResource.LABEL_ACTIVITY
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		BigDecimal capital = society.getCapital();
		if (capital == null || ValidationUtils.isBlank(capital.toPlainString())) {
			support.addError(JsamsI18nLabelResource.LABEL_CAPITAL
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		String phone = society.getContactInformation().getPhone();
		if (ValidationUtils.isBlank(phone)) {
			support.addError(JsamsI18nLabelResource.LABEL_PHONE
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		
		Address address = society.getAddress();
		Validator<Address> addressValidator = new AddressValidator();
		ValidationResult addressResult = addressValidator.validate(address);
		
		ValidationResult result = support.getResult();
		result.addAllFrom(addressResult);
		return support.getResult();
	}

}
