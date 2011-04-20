package be.jsams.client.model.panel;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.model.dialog.management.EditProductCategoryDialog;
import be.jsams.client.model.table.ProductCategoryTableModel;
import be.jsams.client.renderer.JsamsTableCellRenderer;
import be.jsams.client.swing.listener.ProductCategoryTableMouseListener;
import be.jsams.client.validator.EditProductCategoryValidator;
import be.jsams.client.validator.SearchProductCategoryValidator;
import be.jsams.common.bean.builder.ProductCategoryBeanBuilder;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.model.management.ProductCategoryBean;
import be.jsams.server.service.ProductCategoryService;

/**
 * Search Product category panel.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class SearchProductCategoryPanel extends AbstractSearchPanel<ProductCategoryBean,
        ProductCategoryTableMouseListener, ProductCategoryService, EditProductCategoryValidator> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -2569005952852992437L;

    private static final Log LOGGER = LogFactory.getLog(SearchProductCategoryPanel.class);

    private final boolean debug = LOGGER.isDebugEnabled();

    /**
     * Constructor.
     * 
     * @param model
     *            the {@link ProductCategoryBean}
     * @param listener
     *            the {@link ProductCategoryTableMouseListener}
     * @param service
     *            the {@link ProductCategoryService}
     * @param showButtons
     *            the boolean to show or not the management buttons panel
     */
    public SearchProductCategoryPanel(ProductCategoryBean model, ProductCategoryTableMouseListener listener,
            ProductCategoryService service, final boolean showButtons) {
        super(model, listener, service, showButtons);
        super.setValidator(new SearchProductCategoryValidator());
        super.buildMainPanel(buildSearchCriteriaPanel());
    }

    @Override
    protected JPanel buildSearchCriteriaPanel() {
        return getModel().getView().createSearchView();
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        final ProductCategoryBean criteria = getModel();
        if (super.prePerformOk(criteria)) {
            List<ProductCategoryBean> categories = ((ProductCategoryService) super.getService())
                    .findByCriteria(criteria);
            fillTable(categories);
            super.postPerformOk();
        }
    }

    /**
     * Fills the data table.
     * 
     * @param categories
     *            the {@link ProductCategoryBean} list
     */
    private void fillTable(final List<ProductCategoryBean> categories) {
        ProductCategoryTableModel model = new ProductCategoryTableModel(categories);
        getResultTable().setModel(model);

        JTableHeader tableHeader = getResultTable().getTableHeader();
        TableCellRenderer headerRenderer = tableHeader.getDefaultRenderer();

        ((DefaultTableCellRenderer) headerRenderer).setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        getResultTable().setAutoCreateRowSorter(true);
        JsamsTableCellRenderer defaultCellRenderer = new JsamsTableCellRenderer();
        getResultTable().setDefaultRenderer(Long.class, defaultCellRenderer);
        getResultTable().setDefaultRenderer(String.class, defaultCellRenderer);

        getResultTable().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void performButtonAdd() {
        SocietyBean currentSociety = JsamsDesktop.getInstance().getCurrentSociety();
        ProductCategoryBeanBuilder builder = new ProductCategoryBeanBuilder(false, currentSociety);
        builder.setDao(JsamsApplicationContext.getProductCategoryDao());
        ProductCategoryBean categoryBean = builder.build();
        new EditProductCategoryDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT_CATEGORY, categoryBean);
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
            ProductCategoryTableModel model = (ProductCategoryTableModel) getResultTable().getModel();
            ProductCategoryBean beanToModify = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The product category to modify: " + beanToModify);
            }
            new EditProductCategoryDialog(JsamsI18nResource.TITLE_EDIT_PRODUCT_CATEGORY, beanToModify);
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
            ProductCategoryTableModel model = (ProductCategoryTableModel) getResultTable().getModel();
            ProductCategoryBean beanToDelete = model.getRow(selectedRowModel);
            if (debug) {
                LOGGER.debug("The product category to delete: " + beanToDelete);
            }
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
}
