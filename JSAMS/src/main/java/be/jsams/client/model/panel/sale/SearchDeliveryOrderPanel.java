package be.jsams.client.model.panel.sale;

import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ListSelectionModel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.ApplicationContext;
import be.jsams.client.desktop.Desktop;
import be.jsams.client.i18n.I18nResource;
import be.jsams.client.model.dialog.sale.EditDeliveryOrderDialog;
import be.jsams.client.model.table.sale.DeliveryOrderTableModel;
import be.jsams.client.validator.search.sale.SearchDeliveryOrderValidator;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.CustomerBean;
import be.jsams.common.bean.model.sale.DeliveryOrderBean;
import be.jsams.common.bean.model.sale.DeliveryOrderMediator;
import be.jsams.server.service.pdf.impl.PdfDeliveryOrderServiceImpl;
import be.jsams.server.service.sale.DeliveryOrderService;

/**
 * {@link AbstractSaleSearchPanel} for {@link DeliveryOrderBean} objects.
 * 
 * @param <L> a customized {@link MouseListener}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchDeliveryOrderPanel<L extends MouseListener> 
        extends AbstractSaleSearchPanel<DeliveryOrderBean, L,
        DeliveryOrderService, SearchDeliveryOrderValidator, DeliveryOrderTableModel> {

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
     * @param listener the {@link MouseListener}
     * @param service the {@link DeliveryOrderService}
     * @param validator the {@link SearchDeliveryOrderValidator}
     * @param deliveryOrderTableModel the {@link DeliveryOrderTableModel}
     * @param showButtons a boolean that indicates if we have to display the
     *            buttons to manage the content: add, remove and modify
     * @param selectionMode the selection mode to use
     */
    public SearchDeliveryOrderPanel(DeliveryOrderBean model, L listener, DeliveryOrderService service,
            SearchDeliveryOrderValidator validator, DeliveryOrderTableModel deliveryOrderTableModel,
            boolean showButtons, int selectionMode) {
        super(model, listener, service, validator, deliveryOrderTableModel, showButtons, selectionMode);
    }

    /**
     * Constructor.
     * 
     * @param model the {@link DeliveryOrderBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link DeliveryOrderService}
     * @param validator the {@link SearchDeliveryOrderValidator}
     * @param deliveryOrderTableModel the {@link DeliveryOrderTableModel}
     */
    public SearchDeliveryOrderPanel(DeliveryOrderBean model, L listener, DeliveryOrderService service,
            SearchDeliveryOrderValidator validator, DeliveryOrderTableModel deliveryOrderTableModel) {
        this(model, listener, service, validator, deliveryOrderTableModel, true, ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = Desktop.getInstance().getCurrentSociety();
        CustomerBean customerBean = ApplicationContext.getCustomerBeanBuilder().build(null, currentSociety);
        DeliveryOrderBean bean = new DeliveryOrderBean(currentSociety, customerBean);
        DeliveryOrderMediator mediator = new DeliveryOrderMediator();
        mediator.setDeliveryOrderBean(bean);
        bean.setMediator(mediator);
        new EditDeliveryOrderDialog(I18nResource.TITLE_EDIT_DELIVERY_ORDER, bean);
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
            DeliveryOrderMediator mediator = new DeliveryOrderMediator();
            mediator.setDeliveryOrderBean(beanToModify);
            beanToModify.setMediator(mediator);
            if (debug) {
                LOGGER.debug("The delivery order to modify: " + beanToModify);
            }
            new EditDeliveryOrderDialog(I18nResource.TITLE_EDIT_DELIVERY_ORDER, beanToModify);
            updateUI();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void performCancel() {
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
            if (deliveryOrders != null && !deliveryOrders.isEmpty()) {
                for (DeliveryOrderBean bean : deliveryOrders) {
                    DeliveryOrderMediator mediator = new DeliveryOrderMediator();
                    mediator.setDeliveryOrderBean(bean);
                    bean.setMediator(mediator);
                }
            }
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
        super.setTableModel(model);
        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonPdf() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            DeliveryOrderTableModel model = (DeliveryOrderTableModel) getResultTable().getModel();
            DeliveryOrderBean beanToPdf = model.getRow(selectedRowModel);
            PdfDeliveryOrderServiceImpl pdfService = ApplicationContext.getPdfDeliveryOrderService();
            pdfService.generatePdf(beanToPdf);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonEmail() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException();
    }

}
