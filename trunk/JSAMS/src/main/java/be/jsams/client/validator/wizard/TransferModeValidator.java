package be.jsams.client.validator.wizard;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for the step: transfer mode chooser in the transfer wizard
 * dialog.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferModeValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        int transferMode = bean.getTransferMode();
        if (transferMode == 0) {
            support.addError(I18nLabelResource.LABEL_TRANSFER_MODE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        return support.getResult();
    }

}
