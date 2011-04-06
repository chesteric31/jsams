package be.jsams.client.validator;

import be.jsams.server.model.Estimate;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for edit estimate panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EstimateValidator implements Validator<Estimate> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final Estimate estimate) {
        PropertyValidationSupport support = new PropertyValidationSupport(estimate, "");

//        if (ValidationUtils.isBlank(customer.getName())) {
//            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
//                    .getTranslation());
//        }
//
//        if (customer.getPaymentMode() == null) {
//            support.addError(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
//        }
//
//        String phone = customer.getContactInformation().getPhone();
//        if (ValidationUtils.isBlank(phone)) {
//            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
//                    .getTranslation());
//        }
//
//        BigDecimal vatApplicable = customer.getVatApplicable();
//        if (vatApplicable == null || ValidationUtils.isBlank(vatApplicable.toPlainString())) {
//            support.addError(JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
//        }
//
//        Validator<Address> billingAddressValidator = new AddressValidator();
//        ValidationResult billingAddressResult = billingAddressValidator.validate(customer.getBillingAddress());
//        Validator<Address> deliveryAddressValidator = new AddressValidator();
//        ValidationResult deliveryAddressResult = deliveryAddressValidator.validate(customer.getDeliveryAddress());
//
//        ValidationResult result = support.getResult();
//        result.addAllFrom(billingAddressResult);
//        result.addAllFrom(deliveryAddressResult);
        return support.getResult();

    }

}
