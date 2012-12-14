package be.jsams.client.swing.listener.wizard.selection.single;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Abstract class for all single selection into table of wizard.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractWizardSingleSelectionTableML implements MouseListener {

    private TransferBean bean;

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean} to use
     */
    public AbstractWizardSingleSelectionTableML(TransferBean bean) {
        this.bean = bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 1) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow > -1) {
                int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                handleSingleClick(table, selectedRowModel);
            }
        }
    }

    /**
     * Handling of single mouse click.
     * 
     * @param table the {@link JsamsTable} to use
     * @param selectedRowModel the selected row into model
     */
    protected abstract void handleSingleClick(JsamsTable table, int selectedRowModel);

    /**
     * {@inheritDoc}
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(MouseEvent e) {
    }

    /**
     * @return the bean
     */
    public TransferBean getBean() {
        return bean;
    }

    /**
     * @param bean the bean to set
     */
    public void setBean(TransferBean bean) {
        this.bean = bean;
    }

}
