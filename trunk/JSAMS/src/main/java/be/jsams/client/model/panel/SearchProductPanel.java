package be.jsams.client.model.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditProductDialog;
import be.jsams.client.model.table.ProductTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.listener.ProductTableMouseListener;
import be.jsams.client.validator.EditProductValidator;
import be.jsams.client.validator.SearchProductValidator;
import be.jsams.common.bean.builder.ProductBeanBuilder;
import be.jsams.common.bean.model.management.ProductBean;
import be.jsams.server.service.ProductService;

/**
 * Search Product panel.
 * 
 * @author chesteric31
 * @version $Rev: 710 $ $Date::                  $ $Author$
 */
public class SearchProductPanel extends
        AbstractSearchPanel<ProductBean, ProductTableMouseListener, ProductService, EditProductValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 2222078506888522042L;

    private static final Log LOGGER = LogFactory.getLog(SearchProductPanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model
     *            the {@link ProductBean}
     * @param listener
     *            the {@link ProductTableMouseListener}
     * @param service
     *            the {@link ProductService}
     * @param showButtons
     *            the boolean to show or not the management buttons panel
     */
    public SearchProductPanel(ProductBean model, ProductTableMouseListener listener, ProductService service,
            final boolean showButtons) {
        super(model, listener, service, showButtons);
        super.setValidator(new SearchProductValidator());
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    /**
     * {@inheritDoc}
     */
    protected JPanel buildSearchCriteriaPanel() {
        return getModel().getView().createSearchView();
    }

    /**
     * Fills the data table.
     * 
     * @param products
     *            the {@link ProductBean} list
     */
    private void fillTable(final List<ProductBean> products) {
        ProductTableModel model = new ProductTableModel(products);
        getResultTable().setModel(model);

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Integer.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(Double.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);

        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        final ProductBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<ProductBean> products = ((ProductService) super.getService()).findByCriteria(criteria);
            fillTable(products);
            super.postPerformOk();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        ProductBeanBuilder builder = new ProductBeanBuilder();
        new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT, builder.build(true, false));
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
            ProductTableModel model = (ProductTableModel) getResultTable().getModel();
            ProductBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The product to modify: " + beanToModify);
            }
            new EditProductDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT, beanToModify);
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
            ProductTableModel model = (ProductTableModel) getResultTable().getModel();
            ProductBean beanToDelete = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The product to delete: " + beanToDelete);
            }
            getService().delete(beanToDelete);
            model.remove(selectedRowModel);
            updateUI();
        }
    }

    @Override
    public void performCancel() {
        // TODO Auto-generated method stub

    }

}
