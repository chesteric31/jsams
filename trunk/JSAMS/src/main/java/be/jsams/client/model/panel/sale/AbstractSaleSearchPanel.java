package be.jsams.client.model.panel.sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import be.jsams.client.i18n.I18nString;
import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.utils.IconResource;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.server.service.Service;
import be.jsams.server.service.mail.MailSender;

import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Search generic panel for all documents.
 * 
 * @param <B> an extension of {@link AbstractDocumentBean}
 * @param <L> an extension of {@link MouseListener}
 * @param <S> an extension of {@link Service}
 * @param <V> an extension of {@link Validator}
 * @param <TM> an extension of {@link AbstractJsamsTableModel}
 * 
 * @author chesteric31
 * @version $$Revision$$ $$Date::                  $$ $$Author$$
 */
public abstract class AbstractSaleSearchPanel<B extends AbstractDocumentBean<?, ?>,
        L extends MouseListener, S extends Service<B>, V extends Validator<B>, TM extends AbstractJsamsTableModel<B>>
        extends AbstractSearchPanel<B, L, S, V, TM> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7834431441679608267L;

    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractDocumentBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link Service}
     * @param validator the {@link Validator}
     * @param tableModel the {@link AbstractJsamsTableModel}
     */
    public AbstractSaleSearchPanel(B bean, L listener, S service, V validator, TM tableModel) {
        super(bean, listener, service, validator, tableModel);
    }

    /**
     * Constructor.
     * 
     * @param bean the {@link AbstractDocumentBean}
     * @param listener the {@link MouseListener}
     * @param service the {@link Service}
     * @param validator the {@link Validator}
     * @param tableModel the {@link AbstractJsamsTableModel}
     * @param showButtons a boolean that indicates to show or not a management
     *            buttons (add, modify and remove)
     * @param selectionMode the selection mode for the result table
     */
    public AbstractSaleSearchPanel(B bean, L listener, S service, V validator,
            TM tableModel, boolean showButtons, int selectionMode) {
        super(bean, listener, service, validator, tableModel, showButtons, selectionMode);
    }

    /**
     * Builds the PDF generation button.
     * 
     * @return the PDF generation {@link JsamsButton}
     */
    private JsamsButton buildButtonPdf() {
        JsamsButton buttonPdf = new JsamsButton(IconUtil.MENU_ICON_PREFIX + IconResource.DO_PDF);
        //TODO
//        buttonPdf.setToolTipText(I18nResource.BUTTON_DO_PDF);
        buttonPdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                B bean = retrieveSelectedBean();
                performButtonPdf(true, bean);
            }
        });
        return buttonPdf;
    }

    /**
     * The action to perform when click onto PDF generation button.
     * 
     * @param viewReport true if we will to see the report, false otherwise
     * @param bean the selected bean
     * 
     * @return the filename where the PDF was created
     */
    protected abstract String performButtonPdf(boolean viewReport, B bean);

    /**
     * Builds the Email sending button.
     * 
     * @return the Email sending {@link JsamsButton}
     */
    private JsamsButton buildButtonEmail() {
        JsamsButton buttonEmail = new JsamsButton(IconUtil.MENU_ICON_PREFIX + IconResource.SEND_EMAIL);
        //TODO
//        buttonEmail.setToolTipText(I18nResource.BUTTON_SEND_EMAIL);
        buttonEmail.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                B bean = retrieveSelectedBean();
                String pdf = performButtonPdf(false, bean);
                MailSender<B> sender = new MailSender<B>(pdf, bean);
                boolean success = sender.send();
                if (success) {
                    postSend(bean.getCustomer().getContactInformation().getEmail());
                }
            }
            /**
             * Post treatment if the email was successfully sent.
             * 
             * @param email the recipient address of the email
             */
            private void postSend(String email) {
                I18nString success = new I18nString("label.email.success");
                success.setArguments(new Object[] {email});
                JsamsLabel label = new JsamsLabel(success);
                label.setIcon(ValidationResultViewFactory.getInfoIcon());
                getStatusBar().removeAll();
                getStatusBar().repaint();
                getStatusBar().addComponent(label);
                getStatusBar().validate();
            }
        });
        return buttonEmail;
    }
    
    /**
     * Retrieves the selected bean.
     * 
     * @return the selected bean
     */
    protected B retrieveSelectedBean() {
        int selectedRow = getResultTable().getSelectedRow();
        if (selectedRow > -1) {
            int selectedRowModel = getResultTable().convertRowIndexToModel(selectedRow);
            @SuppressWarnings("unchecked")
            TM model = (TM) getResultTable().getModel();
            return model.getRow(selectedRowModel);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected JPanel buildSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());

        if (isShowButtons()) {
            setButtonAdd(buildButtonAdd());
            setButtonRemove(buildButtonRemove());
            setButtonModify(buildButtonModify());
            JsamsButton[] buttons = new JsamsButton[5];
            buttons[0] = getButtonAdd();
            buttons[1] = getButtonRemove();
            buttons[2] = getButtonModify();
            buttons[3] = buildButtonPdf();
            buttons[4] = buildButtonEmail();
            southPanel.add(ButtonBarFactory.buildCenteredBar(buttons));
        }
        southPanel.add(getStatusBar());
        return southPanel;
    }
    
}
