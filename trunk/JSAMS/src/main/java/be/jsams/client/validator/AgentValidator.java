package be.jsams.client.validator;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.server.model.Address;
import be.jsams.server.model.Agent;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for Agent object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentValidator implements Validator<Agent> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(Agent agent) {
        PropertyValidationSupport support = new PropertyValidationSupport(agent, "");

        if (ValidationUtils.isBlank(agent.getName())) {
            support.addError(JsamsI18nLabelResource.LABEL_NAME.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        }

        if (ValidationUtils.isBlank(agent.getFunction())) {
            support.addError(JsamsI18nLabelResource.LABEL_FUNCTION.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        String phone = agent.getContactInformation().getPhone();
        if (ValidationUtils.isBlank(phone)) {
            support.addError(JsamsI18nLabelResource.LABEL_PHONE.getTranslation(), JsamsI18nResource.ERROR_IS_MANDATORY
                    .getTranslation());
        }

        Validator<Address> addressValidator = new AddressValidator();
        ValidationResult addressResult = addressValidator.validate(agent.getAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(addressResult);
        return result;
    }

}
