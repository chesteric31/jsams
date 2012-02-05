package be.jsams.client.validator.search;

import be.jsams.common.bean.model.sale.EstimateBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for search estimate panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchEstimateValidator implements Validator<EstimateBean> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(final EstimateBean estimate) {
        PropertyValidationSupport support = new PropertyValidationSupport(estimate, "");
        return support.getResult();
    }

}
