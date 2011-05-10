package be.jsams.client.validator;

import be.jsams.common.bean.model.sale.BillBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for search bill panel.
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchBillValidator implements Validator<BillBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final BillBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        return support.getResult();
    }

}
