package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.model.panel.SearchCustomerPanel;
import be.jsams.client.model.table.CustomerTableModel;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.CustomerTableMouseListener;
import be.jsams.common.bean.model.management.CustomerBean;

/**
 * {@link AbstractAction} to launch {@link SearchCustomerPanel}.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class SearchCustomerAction extends AbstractAction {

    private final CustomerBean bean;
    private final JsamsDialog dialog;
    
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5028730923752573568L;

    /**
     * Constructor
     * 
     * @param name the name
     * @param icon the icon
     * @param bean the {@link CustomerBean}
     * @param dialog the {@link JsamsDialog}
     */
    public SearchCustomerAction(String name, Icon icon, CustomerBean bean, JsamsDialog dialog) {
        super(name, icon);
        this.bean = bean;
        this.dialog = dialog;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        CustomerTableMouseListener customListener = new CustomerTableMouseListener() {

            /**
             * {@inheritDoc}
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                JsamsTable table = (JsamsTable) e.getSource();
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow > -1) {
                        int selectedRowModel = table.convertRowIndexToModel(selectedRow);
                        CustomerTableModel model = (CustomerTableModel) table.getModel();
                        CustomerBean selectedBean = model.getRow(selectedRowModel);
                        bean.refresh(selectedBean);
//                        bean.setAgent(selectedBean.getAgent());
//                        bean.setBank1(selectedBean.getBank1());
//                        bean.setBank2(selectedBean.getBank2());
//                        bean.setBillingAddress(selectedBean.getBillingAddress());
//                        bean.setCivility(selectedBean.getCivility());
//                        bean.setContactInformation(selectedBean.getContactInformation());
//                        bean.setCreditLimit(selectedBean.getCreditLimit());
//                        bean.setDeliveryAddress(selectedBean.getDeliveryAddress());
//                        bean.setDescription(selectedBean.getDescription());
//                        bean.setId(selectedBean.getId());
//                        bean.setLegalForm(selectedBean.getLegalForm());
//                        bean.setListModel(selectedBean.getListModel());
//                        bean.setName(selectedBean.getName());
//                        bean.setPaymentMode(selectedBean.getPaymentMode());
//                        bean.setSelection(selectedBean.getSelection());
//                        bean.setSociety(selectedBean.getSociety());
//                        bean.setVatApplicable(selectedBean.getVatApplicable());
//                        bean.setVatNumber(selectedBean.getVatNumber());
                        dialog.dispose();
                    }
                }
            }
        };
        SearchCustomerPanel searchCustomerPanel = new SearchCustomerPanel(new CustomerBean(), customListener,
                JsamsApplicationContext.getCustomerService(), false);
        dialog.add(searchCustomerPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(((JsamsButton) e.getSource()).getRootPane());
        dialog.setVisible(true);
    }

}
