package be.jsams.client.validator.search.sale;

import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for search details panel.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class SearchEstimateDetailValidator implements Validator<EstimateDetailBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(EstimateDetailBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        return support.getResult();
    }

}
