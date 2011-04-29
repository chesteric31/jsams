package be.jsams.client.validator;

import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.CommandBean;
import be.jsams.common.bean.model.sale.CommandDetailBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit command panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditCommandValidator implements Validator<CommandBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final CommandBean command) {
        PropertyValidationSupport support = new PropertyValidationSupport(command, "");

        List<CommandDetailBean> details = command.getDetails();

        if (details == null || details.isEmpty()) {
            support.addError(JsamsI18nLabelResource.LABEL_DETAILS.getTranslation(),
                    JsamsI18nResource.ERROR_DETAILS_ARE_EMPTY.getTranslation());
        }
        Date creationDate = command.getCreationDate();
        if (creationDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_CREATION_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        CustomerBean customer = command.getCustomer();
        if (ValidationUtils.isBlank(customer.getName())) {
            support.addError(JsamsI18nLabelResource.LABEL_CUSTOMER.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        AgentBean agent = command.getAgent();
        if (ValidationUtils.isBlank(agent.getName())) {
            support.addError(JsamsI18nLabelResource.LABEL_AGENT.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        
        Validator<AddressBean> billingAddressValidator = new EditAddressValidator();
        ValidationResult billingAddressResult = billingAddressValidator.validate(command.getBillingAddress());
        Validator<AddressBean> deliveryAddressValidator = new EditAddressValidator();
        ValidationResult deliveryAddressResult = deliveryAddressValidator.validate(command.getDeliveryAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(billingAddressResult);
        result.addAllFrom(deliveryAddressResult);
        return support.getResult();
    }

}
