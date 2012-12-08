package be.jsams.client.validator.search.management;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.validator.StringValidator;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for search agent panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchAgentValidator implements Validator<AgentBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final AgentBean agent) {
        PropertyValidationSupport support = new PropertyValidationSupport(agent, "");

        String name = agent.getName();
        if (name != null && !ValidationUtils.isAlphanumeric(name) && !StringValidator.validate(name)) {
            support.addError(I18nLabelResource.LABEL_NAME.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        String function = agent.getFunction();
        if (function != null && !ValidationUtils.isAlphanumeric(function) && !StringValidator.validate(function)) {
            support.addError(I18nLabelResource.LABEL_FUNCTION.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        String phone = agent.getContactInformation().getPhone();
        if (phone != null && !ValidationUtils.isAlphanumericSpace(phone)) {
            support.addError(I18nLabelResource.LABEL_PHONE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        
        AddressBean billingAddress = agent.getAddress();
        String zipCode = billingAddress.getZipCode();
        if (zipCode != null && !ValidationUtils.isAlphanumeric(zipCode)) {
            support.addError(I18nLabelResource.LABEL_ZIP_CODE.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }
        String city = billingAddress.getCity();
        if (city != null && !ValidationUtils.isAlphanumericSpace(city) && !StringValidator.validate(city)) {
            support.addError(I18nLabelResource.LABEL_CITY.getTranslation(),
                    I18nResource.ERROR_IS_ALPHANUMERIC.getTranslation());
        }

        return support.getResult();
    }

}
