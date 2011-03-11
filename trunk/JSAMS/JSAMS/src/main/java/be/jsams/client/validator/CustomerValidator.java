package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.CustomerBean;

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
public class CustomerValidator implements Validator<CustomerBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final CustomerBean customer) {
        PropertyValidationSupport support = new PropertyValidationSupport(customer, "");

        String name = customer.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumeric(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        if (customer.getPaymentMode().getSelection() == null) {
            support.addError(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        String phone = customer.getContactInformation().getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        Double vatApplicable = customer.getVatApplicable();
        if (vatApplicable == null || ValidationUtils.isBlank(vatApplicable.toString())) {
            support.addError(JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        Validator<AddressBean> billingAddressValidator = new AddressValidator();
        ValidationResult billingAddressResult = billingAddressValidator.validate(customer.getBillingAddress());
        Validator<AddressBean> deliveryAddressValidator = new AddressValidator();
        ValidationResult deliveryAddressResult = deliveryAddressValidator.validate(customer.getDeliveryAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(billingAddressResult);
        result.addAllFrom(deliveryAddressResult);
        return result;
    }

}
