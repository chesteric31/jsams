package be.jsams.client.validator.search.sale;

import be.jsams.common.bean.model.sale.DeliveryOrderBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for search delivery order panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchDeliveryOrderValidator implements Validator<DeliveryOrderBean> {
    
    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final DeliveryOrderBean deliveryOrder) {
        PropertyValidationSupport support = new PropertyValidationSupport(deliveryOrder, "");
        return support.getResult();
    }

}
