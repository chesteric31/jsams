package be.jsams.client.swing.action;

import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Icon;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.model.panel.SearchAgentPanel;
import be.jsams.client.model.table.AgentTableModel;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.listener.AgentTableMouseListener;
import be.jsams.common.bean.model.management.AgentBean;

/**
 * {@link AbstractAction} to launch {@link SearchAgentPanel}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class SearchAgentAction extends AbstractAction {

    private final AgentBean bean;
    private final JsamsDialog dialog;
    
    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4903401985131831848L;

    /**
     * Constructor
     * 
     * @param name the name
     * @param icon the icon
     * @param bean the {@link AgentBean}
     * @param dialog the {@link JsamsDialog}
     */
    public SearchAgentAction(String name, Icon icon, AgentBean bean, JsamsDialog dialog) {
        super(name, icon);
        this.bean = bean;
        this.dialog = dialog;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        AgentTableMouseListener customListener = new AgentTableMouseListener() {

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
                        AgentTableModel model = (AgentTableModel) table.getModel();
                        AgentBean selectedBean = model.getRow(selectedRowModel);
                        bean.refresh(selectedBean);
//                        bean.setAddress(selectedBean.getAddress());
//                        bean.setCivility(selectedBean.getCivility());
//                        bean.setContactInformation(selectedBean.getContactInformation());
//                        bean.setCustomers(selectedBean.getCustomers());
//                        bean.setFunction(selectedBean.getFunction());
//                        bean.setId(selectedBean.getId());
//                        bean.setListModel(selectedBean.getListModel());
//                        bean.setName(selectedBean.getName());
//                        bean.setSelection(selectedBean.getSelection());
                        dialog.dispose();
                    }
                }
            }
        };
        SearchAgentPanel searchAgentPanel = new SearchAgentPanel(new AgentBean(), customListener,
                JsamsApplicationContext.getAgentService(), false);
        dialog.add(searchAgentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(((JsamsButton) e.getSource()).getRootPane());
        dialog.setVisible(true);
    }

}