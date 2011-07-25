package be.jsams.client.validator;

import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for transfer wizard dialog.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");

//        String name = customer.getName();
//        if (ValidationUtils.isBlank(name)) {
//            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
//                    .getTranslation());
//        } else if (!ValidationUtils.isAlphanumericSpace(name)) {
//            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
//        }
//
//        if (customer.getPaymentMode().getLabel() == null) {
//            support.addError(JsamsI18nLabelResource.LABEL_PAYMENT_MODE.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
//        }
//
//        String phone = customer.getContactInformation().getPhone();
//        if (ValidationUtils.isBlank(phone)) {
//            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
//                    .getTranslation());
//        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
//            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
//        }
//
//        Double vatApplicable = customer.getVatApplicable();
//        if (vatApplicable == null || ValidationUtils.isBlank(vatApplicable.toString())) {
//            support.addError(JsamsI18nLabelResource.LABEL_VAT_APPLICABLE.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
//        }
//
//        AgentBean agent = customer.getAgent();
//        if (agent == null || ValidationUtils.isBlank(agent.getName())) {
//            support.addError(JsamsI18nLabelResource.LABEL_AGENT.getTranslation(),
//                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
//        }
//
//        Validator<AddressBean> billingAddressValidator = new EditAddressValidator();
//        ValidationResult billingAddressResult = billingAddressValidator.validate(customer.getBillingAddress());
//        Validator<AddressBean> deliveryAddressValidator = new EditAddressValidator();
//        ValidationResult deliveryAddressResult = deliveryAddressValidator.validate(customer.getDeliveryAddress());
//
        ValidationResult result = support.getResult();
//        result.addAllFrom(billingAddressResult);
//        result.addAllFrom(deliveryAddressResult);
        return result;
    }

}
