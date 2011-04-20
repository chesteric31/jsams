package be.jsams.client.model.dialog.sale;

import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.dialog.AbstractEditDialog;
import be.jsams.client.validator.EditEstimateValidator;
import be.jsams.common.bean.model.sale.EstimateBean;
import be.jsams.common.bean.view.sale.EstimateBeanView;
import be.jsams.server.service.EstimateService;

import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * Edit Estimate {@link AbstractEditDialog}, to create or update a {@link EstimateBean} object.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditEstimateDialog extends AbstractEditDialog<EstimateBean, EditEstimateValidator, EstimateService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 646774314391865523L;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link EstimateBean} model
     */
    public EditEstimateDialog(final I18nString title, EstimateBean model) {
        super(null, title);
        super.setModel(model);
        super.setValidator(new EditEstimateValidator());
        super.setService(JsamsApplicationContext.getEstimateService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initComponents() {
        EstimateBeanView view = getModel().getView();
        JPanel panel = view.createEditView();
        getContentPane().add(panel);
        ValidationComponentUtils.updateComponentTreeMandatoryBorder(this);
        pack();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        EstimateBean estimate = getModel();
        super.postPerformOk(estimate);
    }

}
