package be.jsams.client.validator.edit.sale;

import java.util.Date;
import java.util.List;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.validator.edit.EditAddressValidator;
import be.jsams.common.bean.model.AddressBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;
import com.jgoodies.validation.util.ValidationUtils;

/**
 * {@link Validator} for edit delivery order panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditDeliveryOrderValidator implements Validator<DeliveryOrderBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final DeliveryOrderBean deliveryOrder) {
        PropertyValidationSupport support = new PropertyValidationSupport(deliveryOrder, "");

        List<DeliveryOrderDetailBean> details = deliveryOrder.getDetails();

        if (details == null || details.isEmpty()) {
            support.addError(I18nLabelResource.LABEL_DETAILS.getTranslation(),
                    I18nResource.ERROR_DETAILS_ARE_EMPTY.getTranslation());
        }
        Date creationDate = deliveryOrder.getCreationDate();
        if (creationDate == null) {
            support.addError(I18nLabelResource.LABEL_CREATION_DATE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        CustomerBean customer = deliveryOrder.getCustomer();
        if (ValidationUtils.isBlank(customer.getName())) {
            support.addError(I18nLabelResource.LABEL_CUSTOMER.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        
        Validator<AddressBean> deliveryAddressValidator = new EditAddressValidator();
        ValidationResult deliveryAddressResult = deliveryAddressValidator.validate(deliveryOrder.getDeliveryAddress());

        ValidationResult result = support.getResult();
        result.addAllFrom(deliveryAddressResult);
        return support.getResult();
    }
}
