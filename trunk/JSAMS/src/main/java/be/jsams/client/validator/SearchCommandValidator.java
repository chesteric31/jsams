package be.jsams.client.validator;

import be.jsams.common.bean.model.sale.CommandBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for search command panel.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCommandValidator implements Validator<CommandBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(final CommandBean command) {
        PropertyValidationSupport support = new PropertyValidationSupport(command, "");
        return support.getResult();
    }

}
