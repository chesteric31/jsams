package be.jsams.client.validator.wizard;

import java.util.List;

import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.common.bean.model.sale.detail.AbstractDetailBean;
import be.jsams.common.bean.model.transfer.TransferBean;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class DocumentDetailsValidator implements Validator<TransferBean> {

    /**
     * {@inheritDoc}
     */
    @Override
    public ValidationResult validate(TransferBean bean) {
        PropertyValidationSupport support = new PropertyValidationSupport(bean, "");
        List<? extends AbstractDetailBean<?, ?, ?>> documents = bean.getDetails();
        if (documents == null || documents.isEmpty()) {
            support.addError(JsamsI18nLabelResource.LABEL_DOCUMENT_DETAILS.getTranslation(),
                    JsamsI18nResource.ERROR_IS_MANDATORY_TO_SELECT.getTranslation());
        }
        ValidationResult result = support.getResult();
        return result;
    }

}
