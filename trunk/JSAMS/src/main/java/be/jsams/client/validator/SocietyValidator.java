package be.jsams.client.validator;

import java.math.BigDecimal;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Society;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * Validator for edit product panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date:: $ $Author$
 */
public class SocietyValidator implements Validator<Society> {

	public ValidationResult validate(Society society) {
		PropertyValidationSupport support = new PropertyValidationSupport(
				society, "");

		if (ValidationUtils.isBlank(society.getName())) {
			support.addError(JsamsI18nResource.LABEL_NAME.getTranslation(),
					JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
		}
		if (ValidationUtils.isBlank(society.getActivity())) {
			support.addError(JsamsI18nResource.LABEL_ACTIVITY.getTranslation(),
					JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
		}
		if (ValidationUtils.isBlank(society.getVatNumber())) {
			support.addError(JsamsI18nResource.LABEL_VAT_NUMBER
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		BigDecimal capital = society.getCapital();
		if (capital == null
				|| !ValidationUtils.isBlank(capital.toPlainString())) {
			support.addError(JsamsI18nResource.LABEL_CAPITAL.getTranslation(),
					JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
		}

		return support.getResult();
	}

}
