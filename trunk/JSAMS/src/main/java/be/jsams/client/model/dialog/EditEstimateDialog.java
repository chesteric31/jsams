package be.jsams.client.model.dialog;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.validator.EstimateValidator;
import be.jsams.server.model.Estimate;
import be.jsams.server.service.EstimateService;

/**
 * Edit Estimate {@link EditDialog}, to create or update a {@link Estimate} object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class EditEstimateDialog extends EditDialog<Estimate, EstimateValidator, EstimateService> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 646774314391865523L;
    
    protected static final Log LOGGER = LogFactory.getLog(EditEstimateDialog.class);

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     * @param model
     *            the {@link Estimate} model
     */
    public EditEstimateDialog(final I18nString title, Estimate model) {
        super(null, title);
        super.setModel(model);
        super.setValidator(new EstimateValidator());
        super.setService(JsamsApplicationContext.getEstimateService());
        initComponents();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    @Override
    protected void initComponents() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void performOk() {
        // TODO Auto-generated method stub
        
    }

}
