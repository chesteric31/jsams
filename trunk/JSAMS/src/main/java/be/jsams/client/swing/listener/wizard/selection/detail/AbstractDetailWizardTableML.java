package be.jsams.client.swing.listener.wizard.selection.detail;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import be.jsams.client.swing.component.JsamsTable;
import be.jsams.common.bean.model.transfer.TransferBean;

/**
 * Abstract detail wizard table mouse listener.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public abstract class AbstractDetailWizardTableML implements MouseListener {

    private TransferBean bean;

    /**
     * Constructor.
     * 
     * @param bean the {@link TransferBean} to use
     */
    public AbstractDetailWizardTableML(TransferBean bean) {
        this.bean = bean;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        JsamsTable table = (JsamsTable) e.getSource();
        if (e.getClickCount() == 1) {
            int[] selectedRows = table.getSelectedRows();
            if (selectedRows != null && selectedRows.length > 0) {
                handleSelection(table, selectedRows);
            }
        }
    }

    /**
     * Handling of single mouse click selection.
     * 
     * @param table the {@link JsamsTable} to use
     * @param selectedRows the selected rows
     */
    protected abstract void handleSelection(JsamsTable table, int[] selectedRows);

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
