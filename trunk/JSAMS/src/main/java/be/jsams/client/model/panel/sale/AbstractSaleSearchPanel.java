package be.jsams.client.model.panel.sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import be.jsams.client.model.panel.AbstractSearchPanel;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.utils.IconResource;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.server.service.Service;

import com.jgoodies.forms.factories.ButtonBarFactory;
import com.jgoodies.validation.Validator;

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

    private JsamsButton buttonPdf = null;
    
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
        buttonPdf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonPdf();
            }
        });
        return buttonPdf;
    }

    /**
     * The action to perform when click onto PDF generation button.
     */
    protected abstract void performButtonPdf();
    
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
            buttonPdf = buildButtonPdf();
            JsamsButton[] buttons = new JsamsButton[4];
            buttons[0] = getButtonAdd();
            buttons[1] = getButtonRemove();
            buttons[2] = getButtonModify();
            buttons[3] = buttonPdf;
            southPanel.add(ButtonBarFactory.buildCenteredBar(buttons));
        }
        southPanel.add(getStatusBar());
        return southPanel;
    }
    
}
