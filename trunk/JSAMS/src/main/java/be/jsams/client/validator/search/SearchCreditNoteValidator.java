package be.jsams.client.validator.search;

import be.jsams.common.bean.model.sale.CreditNoteBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for search credit note panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCreditNoteValidator implements Validator<CreditNoteBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final CreditNoteBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        return support.getResult();
    }
    
}
