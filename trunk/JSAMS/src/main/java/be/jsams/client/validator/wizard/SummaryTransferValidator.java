package be.jsams.client.validator.wizard;

import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for the step: summary in the transfer wizard dialog.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SummaryTransferValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        return support.getResult();
    }

}
