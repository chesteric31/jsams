package be.jsams.client.model.panel.sale;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.sale.EditDeliveryOrderDialog;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.DeliveryOrderTableModel;
import be.jsams.client.swing.listener.DeliveryOrderTableMouseListener;
import be.jsams.client.validator.SearchDeliveryOrderValidator;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.server.service.sale.DeliveryOrderService;

/**
 * {@link AbstractSearchPanel} for {@link DeliveryOrderBean} objects. 
 *
 * @author ebinard
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchDeliveryOrderPanel
        extends AbstractSearchPanel<DeliveryOrderBean, DeliveryOrderTableMouseListener,
        DeliveryOrderService, SearchDeliveryOrderValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 6215987110609750527L;

    private static final Log LOGGER = LogFactory.getLog(SearchDeliveryOrderPanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model the {@link DeliveryOrderBean}
     * @param listener the {@link DeliveryOrderTableMouseListener}
     * @param service the {@link DeliveryOrderService}
     * @param validator the {@link SearchDeliveryOrderValidator}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     */
    public SearchDeliveryOrderPanel(DeliveryOrderBean model, DeliveryOrderTableMouseListener listener,
            DeliveryOrderService service, SearchDeliveryOrderValidator validator, boolean showButtons) {
        super(model, listener, service, validator, showButtons);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        new EditDeliveryOrderDialog(JsamsI18nResource.TITLE_EDIT_DELIVERY_ORDER, new DeliveryOrderBean());
        updateUI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonModify() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            DeliveryOrderTableModel model = (DeliveryOrderTableModel) getResultTable().getModel();
            DeliveryOrderBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The delivery order to modify: " + beanToModify);
            }
            new EditDeliveryOrderDialog(JsamsI18nResource.TITLE_EDIT_DELIVERY_ORDER, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonRemove() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            DeliveryOrderTableModel model = (DeliveryOrderTableModel) getResultTable().getModel();
            DeliveryOrderBean beanToDelete = model.getRow(selectedRowModel);
            getService().delete(beanToDelete);
            model.remove(selectedRowModel);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performOk() {
        final DeliveryOrderBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<DeliveryOrderBean> deliveryOrders = ((DeliveryOrderService) super.getService())
                    .findByCriteria(criteria);
            fillTable(deliveryOrders);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param deliveryOrders the {@link DeliveryOrderBean} list
     */
    private void fillTable(final List<DeliveryOrderBean> deliveryOrders) {
        DeliveryOrderTableModel model = new DeliveryOrderTableModel(deliveryOrders);
        getResultTable().setModel(model);
        getResultTable().repaint();
    }

}
