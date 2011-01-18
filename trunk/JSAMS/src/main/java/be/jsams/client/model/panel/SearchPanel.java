package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.service.Service;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Search generic panel.
 * 
 * @param <M>
 *            an extension of {@link AbstractIdentity}
 * @param <L>
 *            an extension of {@link MouseListener}
 * @param <S>
 *            an extension of {@link Service}
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public abstract class SearchPanel<M extends AbstractIdentity, L extends MouseListener, S extends Service<M>> extends
        JPanel implements JsamsButtonsInterface {

  /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -4277242728022039298L;

    protected static final Log LOGGER = LogFactory.getLog(SearchPanel.class);
    
    private static final int DEFAULT_V_GAP = 10;

    private M model;

    private MouseListener mouseListener;
    
    private Service<M> service;
    
    private JsamsButtonsPanel buttonsPanel;

    protected JsamsTable resultTable = null;

    private JsamsButton buttonAdd = null;
    private JsamsButton buttonRemove = null;
    private JsamsButton buttonModify = null;

    /**
     * Constructor
     */
    public SearchPanel() {
        super();
        setLayout(new BorderLayout());
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
    public M getModel() {
        return model;
    }

    /**
     * 
     * @param model the model to set
     */
    public void setModel(M model) {
        this.model = model;
    }

    /**
     * 
     * @return the {@link MouseListener}
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }
    
    /**
     * 
     * @param mouseListener the {@link MouseListener} to set
     */
    public void setMouseListener(MouseListener mouseListener) {
        this.mouseListener = mouseListener;
    }

    /**
     * 
     * @return the service
     */
    public Service<M> getService() {
        return service;
    }

    /**
     * 
     * @param service the service to set
     */
    public void setService(Service<M> service) {
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
     * @param resultTable the result {@link JsamsTable} to set
     */
    public void setResultTable(JsamsTable resultTable) {
        this.resultTable = resultTable;
    }

    /**
     * {@inheritDoc}
     */
    public void performCancel() {
        // TODO Auto-generated method stub

    }

    /**
     * {@inheritDoc}
     */
    public abstract void performOk();

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        performReset(this);
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
     * Performs the reseting.
     * 
     * @param container
     *            the {@link Container}
     */
    public void performReset(final Container container) {
        for (Component component : container.getComponents()) {
            if (component instanceof Container) {
                this.performReset((Container) component);
            }
            // NOT ELSE IF cause that doesn't work
            if (component instanceof JsamsTextField) {
                ((JsamsTextField) component).setText(null);
            } else if (component instanceof JComboBox) {
                ((JComboBox) component).setSelectedIndex(0);
            } else if (component instanceof JTextArea) {
                ((JTextArea) component).setText(null);
            }
        }
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
        buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
        northPanel.add(buttonsPanel);
        searchCriteriaPanel.add(buttonsPanel);

        this.add(searchCriteriaPanel, BorderLayout.NORTH);

        resultTable = new JsamsTable(true);
        resultTable.addMouseListener(mouseListener);
        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder(JsamsI18nResource.SEARCH_RESULTS.getTranslation()));
        this.add(scrollPane, BorderLayout.CENTER);

        buttonAdd = buildButtonAdd();
        buttonRemove = buildButtonRemove();
        buttonModify = buildButtonModify();

        this.add(ButtonBarFactory.buildCenteredBar(new JButton[] { buttonAdd, buttonRemove, buttonModify }),
                BorderLayout.SOUTH);
    }

}
