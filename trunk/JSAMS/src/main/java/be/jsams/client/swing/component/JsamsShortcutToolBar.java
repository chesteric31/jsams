package be.jsams.client.swing.component;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import be.jsams.client.i18n.I18nResource;
import be.jsams.client.swing.action.NewBillAction;
import be.jsams.client.swing.action.NewCustomerAction;
import be.jsams.client.swing.action.NewEstimateAction;
import be.jsams.client.swing.action.NewProductAction;
import be.jsams.client.swing.action.StatisticsAction;

/**
 * A customized {@link JPanel} to have some shortcuts for more used functions.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsShortcutToolBar extends JPanel {

    /**
     * Serial Version
     */
    private static final long serialVersionUID = 8357027337848797984L;

    private JsamsButton newCustomerButton;
    private JsamsButton newEstimateButton;
    private JsamsButton newBillButton;
    private JsamsButton newProductButton;
    private JsamsButton statisticsButton;

    /**
     * Constructor.
     */
    public JsamsShortcutToolBar() {
        super();
        initComponents();
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(I18nResource.PANEL_SHORTCUT_TOOLBAR.getTranslation()));
        newCustomerButton = new JsamsButton(I18nResource.BUTTON_NEW_CUSTOMER);
        newCustomerButton.addActionListener(new NewCustomerAction());
        newCustomerButton.setEnabled(false);
        newEstimateButton = new JsamsButton(I18nResource.BUTTON_NEW_ESTIMATE);
        newEstimateButton.addActionListener(new NewEstimateAction());
        newEstimateButton.setEnabled(false);
        newBillButton = new JsamsButton(I18nResource.BUTTON_NEW_BILL);
        newBillButton.addActionListener(new NewBillAction());
        newBillButton.setEnabled(false);
        newProductButton = new JsamsButton(I18nResource.BUTTON_NEW_PRODUCT);
        newProductButton.addActionListener(new NewProductAction());
        newProductButton.setEnabled(false);
        statisticsButton = new JsamsButton(I18nResource.BUTTON_STATISTICS);
        statisticsButton.addActionListener(new StatisticsAction());
        statisticsButton.setEnabled(false);
        panel.add(newCustomerButton);
        panel.add(newEstimateButton);
        panel.add(newBillButton);
        panel.add(newProductButton);
        panel.add(statisticsButton);
        this.add(panel);
    }
    
    /**
     * Enables/disables the buttons.
     * 
     * @param value true to enable, false to disable
     */
    public void enableButtons(boolean value) {
        newCustomerButton.setEnabled(value);
        newEstimateButton.setEnabled(value);
        newBillButton.setEnabled(value);
        newProductButton.setEnabled(value);
        statisticsButton.setEnabled(value);
    }
    
}
