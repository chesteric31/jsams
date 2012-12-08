package be.jsams.client.validator.edit.management;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.edit.EditAddressValidator;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.ContactInformationBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.validator.StringValidator;

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
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(name) && !StringValidator.validate(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        String function = agent.getFunction();
        if (ValidationUtils.isBlank(function)) {
            support.addError(I18nLabelResource.LABEL_FUNCTION.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(function) && !StringValidator.validate(function)) {
            support.addError(I18nLabelResource.LABEL_FUNCTION.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        ContactInformationBean contactInformation = agent.getContactInformation();
        String phone = contactInformation.getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(), I18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        } else if (!ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String email = contactInformation.getEmail();
        if (!ValidationUtils.isBlank(email)) {
            if (!be.jsams.common.bean.validator.EmailValidator.validate(email)) {
                support.addError(I18nLabelResource.LABEL_EMAIL.getTranslation(),
                        I18nResource.ERROR_IS_INVALID.getTranslation());
            }
        }

        Validator<AddressBean> addressValidator = new EditAddressValidator();
        ValidationResult addressResult = addressValidator.validate(agent.getAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(addressResult);
        return result;
    }

}
