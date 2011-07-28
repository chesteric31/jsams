package be.jsams.client.validator.edit;

import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.AgentBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit estimate panel.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditEstimateValidator implements Validator<EstimateBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final EstimateBean estimate) {
        PropertyValidationSupport support = new PropertyValidationSupport(estimate, "");

        List<EstimateDetailBean> details = estimate.getDetails();

        if (details == null || details.isEmpty()) {
            support.addError(JsamsI18nLabelResource.LABEL_DETAILS.getTranslation(),
                    JsamsI18nResource.ERROR_DETAILS_ARE_EMPTY.getTranslation());
        }
        Date creationDate = estimate.getCreationDate();
        if (creationDate == null) {
            support.addError(JsamsI18nLabelResource.LABEL_CREATION_DATE.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        CustomerBean customer = estimate.getCustomer();
        if (ValidationUtils.isBlank(customer.getName())) {
            support.addError(JsamsI18nLabelResource.LABEL_CUSTOMER.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        AgentBean agent = estimate.getAgent();
        if (ValidationUtils.isBlank(agent.getName())) {
            support.addError(JsamsI18nLabelResource.LABEL_AGENT.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY.getTranslation());
        }

        Validator<AddressBean> billingAddressValidator = new EditAddressValidator();
        ValidationResult billingAddressResult = billingAddressValidator.validate(estimate.getBillingAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(billingAddressResult);
        return support.getResult();
    }

}
