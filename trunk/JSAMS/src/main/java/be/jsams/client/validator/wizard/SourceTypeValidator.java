package be.jsams.client.validator.wizard;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for the step: source chooser in the transfer wizard
 * dialog.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SourceTypeValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        int sourceType = bean.getSourceType();
        if (sourceType == 0) {
            support.addError(I18nLabelResource.LABEL_SOURCE_TYPE.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY.getTranslation());
        }
        return support.getResult();
    }

}
