package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Address;
import be.jsams.server.model.Customer;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit customer panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class CustomerValidator implements Validator<Customer> {

	public ValidationResult validate(final Customer customer) {
		PropertyValidationSupport support = new PropertyValidationSupport(
				customer, "");

		if (ValidationUtils.isBlank(customer.getName())) {
			support.addError(
					JsamsI18nLabelResource.LABEL_NAME.getTranslation(),
					JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
		}
		
		if (customer.getPaymentMode() == null) {
			support.addError(JsamsI18nLabelResource.LABEL_PAYMENT_MODE
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}

		String phone = customer.getContactInformation().getPhone();
		if (ValidationUtils.isBlank(phone)) {
			support.addError(JsamsI18nLabelResource.LABEL_PHONE
					.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
					.getTranslation());
		}
		
		Validator<Address> addressValidator = new AddressValidator();
		ValidationResult billingAddressResult = addressValidator
				.validate(customer.getBillingAddress());
		ValidationResult deliveryAddressResult = addressValidator
				.validate(customer.getDeliveryAddress());
		
		ValidationResult result = support.getResult();
		result.addAllFrom(billingAddressResult);
		result.addAllFrom(deliveryAddressResult);
		return result;
	}

}
