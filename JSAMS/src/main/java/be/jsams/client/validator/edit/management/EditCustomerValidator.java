package be.jsams.client.validator.edit.management;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.edit.EditAddressValidator;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.validator.EmailValidator;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit customer dialog.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditCustomerValidator implements Validator<CustomerBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final CustomerBean customer) {
        PropertyValidationSupport support = new PropertyValidationSupport(customer, "");

        String name = customer.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(name) && !StringValidator.validate(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        if (customer.getPaymentMode().getLabel() == null) {
            support.addError(I18nLabelResource.LABEL_PAYMENT_MODE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        ContactInformationBean contactInformation = customer.getContactInformation();
        String phone = contactInformation.getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String email = contactInformation.getEmail();
        if (!ValidationUtils.isBlank(email)) {
            if (!EmailValidator.validate(email)) {
                support.addError(I18nLabelResource.LABEL_EMAIL.getTranslation(),
                        I18nResource.ERROR_IS_INVALID.getTranslation());
            }
        }

        Double vatApplicable = customer.getVatApplicable();
        if (vatApplicable == null || ValidationUtils.isBlank(vatApplicable.toString())) {
            support.addError(I18nLabelResource.LABEL_VAT_APPLICABLE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        AgentBean agent = customer.getAgent();
        if (agent == null || ValidationUtils.isBlank(agent.getName())) {
            support.addError(I18nLabelResource.LABEL_AGENT.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        Validator<AddressBean> billingAddressValidator = new EditAddressValidator();
        ValidationResult billingAddressResult = billingAddressValidator.validate(customer.getBillingAddress());
        Validator<AddressBean> deliveryAddressValidator = new EditAddressValidator();
        ValidationResult deliveryAddressResult = deliveryAddressValidator.validate(customer.getDeliveryAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(billingAddressResult);
        result.addAllFrom(deliveryAddressResult);
        return result;
    }

}
