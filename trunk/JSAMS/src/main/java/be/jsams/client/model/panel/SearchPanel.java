package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;

import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.server.service.Service;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Search generic panel.
 * 
 * @param <B>
 *            an extension of {@link AbstractIdentityBean}
 * @param <L>
 *            an extension of {@link MouseListener}
 * @param <S>
 *            an extension of {@link Service}
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public abstract class SearchPanel<B extends AbstractIdentityBean<?, ?>, L extends MouseListener, S extends Service<B>>
        extends JPanel implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4277242728022039298L;

    protected static final Log LOGGER = LogFactory.getLog(SearchPanel.class);

    private static final int DEFAULT_V_GAP = 10;

    private B model;

    private L mouseListener;

    private S service;

    private JsamsButtonsPanel buttonsPanel;

    private JsamsTable resultTable = null;

    private JsamsButton buttonAdd = null;
    private JsamsButton buttonRemove = null;
    private JsamsButton buttonModify = null;

    private boolean showButtons;

    /**
     * Constructor.
     * 
     * @param bean
     *            the {@link AbstractIdentityBean}
     * @param listener
     *            the {@link MouseListener}
     * @param service
     *            the {@link Service}
     * @param showButtons
     *            a boolean that indicates to show or not a management buttons (add, modify and remove)
     */
    public SearchPanel(B bean, L listener, S service, boolean showButtons) {
        super();
        this.model = bean;
        this.mouseListener = listener;
        this.service = service;
        this.showButtons = showButtons;
        setLayout(new BorderLayout());
        buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
        setDefaultKeyActions();
    }

    /**
     * Builds the search criteria panel.
     * 
     * @return the search criteria {@link JPanel}
     */
    protected abstract JPanel buildSearchCriteriaPanel();

    /**
     * 
     * @return the model
     */
    public B getModel() {
        return model;
    }

    /**
     * 
     * @param model
     *            the model to set
     */
    public void setModel(B model) {
        this.model = model;
    }

    /**
     * 
     * @return the {@link MouseListener}
     */
    public L getMouseListener() {
        return mouseListener;
    }

    /**
     * 
     * @param mouseListener
     *            the {@link MouseListener} to set
     */
    public void setMouseListener(L mouseListener) {
        this.mouseListener = mouseListener;
    }

    /**
     * 
     * @return the service
     */
    public S getService() {
        return service;
    }

    /**
     * 
     * @param service
     *            the service to set
     */
    public void setService(S service) {
        this.service = service;
    }

    /**
     * 
     * @return the result {@link JsamsTable}
     */
    public JsamsTable getResultTable() {
        return resultTable;
    }

    /**
     * 
     * @return true, if we have to display the management buttons panel, false otherwise
     */
    public boolean isShowButtons() {
        return showButtons;
    }

    /**
     * 
     * @param showButtons
     *            the boolean to set, if we have to display the management buttons panel or not
     */
    public void setShowButtons(boolean showButtons) {
        this.showButtons = showButtons;
    }

    /**
     * 
     * @param resultTable
     *            the result {@link JsamsTable} to set
     */
    public void setResultTable(JsamsTable resultTable) {
        this.resultTable = resultTable;
    }

    /**
     * {@inheritDoc}
     */
    public abstract void performCancel();

    /**
     * {@inheritDoc}
     */
    public abstract void performOk();

    /**
     * Refreshes the result table to press to OK.
     */
    public void refresh() {
        performOk();
    }

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        model.clear();
        ((JsamsTable) getResultTable()).clear();
        updateUI();
    }

    /**
     * Builds the adding button.
     * 
     * @return the adding {@link JsamsButton}
     */
    private JsamsButton buildButtonAdd() {
        JsamsButton buttonAdd = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-add.png");
        buttonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonAdd();
            }
        });
        return buttonAdd;
    }

    /**
     * The action to perform when click onto adding button.
     */
    protected abstract void performButtonAdd();

    /**
     * The action to perform when click onto modifying button.
     */
    protected abstract void performButtonModify();

    /**
     * The action to perform when click onto removing button.
     */
    protected abstract void performButtonRemove();

    /**
     * Builds the removing button.
     * 
     * @return the removing {@link JsamsButton}
     */
    private JsamsButton buildButtonRemove() {
        JsamsButton buttonRemove = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "actions/list-remove.png");
        buttonRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonRemove();
            }
        });
        return buttonRemove;
    }

    /**
     * Builds the modifying button.
     * 
     * @return the modifying {@link JsamsButton}
     */
    private JsamsButton buildButtonModify() {
        JsamsButton buttonModify = new JsamsButton(IconUtil.MENU_ICON_PREFIX + "apps/accessories-text-editor.png");
        buttonModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performButtonModify();
            }
        });
        return buttonModify;
    }

    /**
     * Builds the main panel contained all the components.
     * 
     * @param criteriaPanel
     *            the customized criteria panel from kid classes
     */
    protected void buildMainPanel(final JPanel criteriaPanel) {
        JPanel searchCriteriaPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(2, 1);
        gridLayout.setVgap(DEFAULT_V_GAP);
        searchCriteriaPanel.setLayout(gridLayout);
        searchCriteriaPanel.add(criteriaPanel);

        JPanel northPanel = new JPanel();
        BorderLayout buttonsLayout = new BorderLayout();
        buttonsLayout.setVgap(DEFAULT_V_GAP);
        northPanel.setLayout(buttonsLayout);
        northPanel.add(new JSeparator(), BorderLayout.NORTH);
        northPanel.add(buttonsPanel);
        searchCriteriaPanel.add(buttonsPanel);

        this.add(searchCriteriaPanel, BorderLayout.NORTH);

        resultTable = new JsamsTable(true);
        resultTable.addMouseListener(mouseListener);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
        this.add(scrollPane, BorderLayout.CENTER);

        if (showButtons) {
            buttonAdd = buildButtonAdd();
            buttonRemove = buildButtonRemove();
            buttonModify = buildButtonModify();
            JsamsButton[] buttons = new JsamsButton[3];
            buttons[0] = buttonAdd;
            buttons[1] = buttonRemove;
            buttons[2] = buttonModify;
            this.add(ButtonBarFactory.buildCenteredBar(buttons), BorderLayout.SOUTH);
        }
    }

    /**
     * Sets the default keys actions.
     */
    private void setDefaultKeyActions() {
        // Automatically choose OK when Enter Key is pressed
        int noModifiers = 0;
        KeyStroke okKey = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, noModifiers, false);
        InputMap inputMap = this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(okKey, JsamsButtonsPanel.OK_ACTION_KEY);
        this.getActionMap().put(JsamsButtonsPanel.OK_ACTION_KEY, buttonsPanel.getButtonOk().getAction());

        KeyStroke resetKey = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, noModifiers, false);
        inputMap.put(resetKey, JsamsButtonsPanel.RESET_ACTION_KEY);
        this.getActionMap().put(JsamsButtonsPanel.RESET_ACTION_KEY, buttonsPanel.getButtonReset().getAction());
    }

}
