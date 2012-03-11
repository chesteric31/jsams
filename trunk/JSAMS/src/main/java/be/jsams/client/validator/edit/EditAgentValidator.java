package be.jsams.client.validator.edit;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.validator.EmailValidator;
import be.jsams.common.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for agent edition.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditAgentValidator implements Validator<AgentBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(AgentBean agent) {
        PropertyValidationSupport support = new PropertyValidationSupport(agent, "");

        String name = agent.getName();
        if (ValidationUtils.isBlank(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(name) && !StringValidator.validate(name)) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        String function = agent.getFunction();
        if (ValidationUtils.isBlank(function)) {
            support.addError(JsamsI18nLabelResource.LABEL_FUNCTION.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(function) && !StringValidator.validate(function)) {
            support.addError(JsamsI18nLabelResource.LABEL_FUNCTION.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        ContactInformationBean contactInformation = agent.getContactInformation();
        String phone = contactInformation.getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String email = contactInformation.getEmail();
        if (!ValidationUtils.isBlank(email)) {
            if (!EmailValidator.validate(email)) {
                support.addError(JsamsI18nLabelResource.LABEL_EMAIL.getTranslation(),
                        JsamsI18nResource.ERROR_IS_INVALID.getTranslation());
            }
        }

        Validator<AddressBean> addressValidator = new EditAddressValidator();
        ValidationResult addressResult = addressValidator.validate(agent.getAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(addressResult);
        return result;
    }

}
