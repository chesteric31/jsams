package be.jsams.client.validator.wizard;

import java.util.List;

import be.jsams.client.i18n.I18nLabelResource;
import be.jsams.client.i18n.I18nResource;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for the step: document chooser in the transfer wizard
 * dialog.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DocumentValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        List<? extends AbstractDocumentBean<?, ?>> documents = bean.getDocuments();
        if (documents == null || documents.isEmpty()) {
            support.addError(I18nLabelResource.LABEL_DOCUMENT.getTranslation(),
                    I18nResource.ERROR_IS_MANDATORY_TO_SELECT.getTranslation());
        }
        return support.getResult();
    }

}
