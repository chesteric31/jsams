package be.jsams.client.swing.component;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.swing.action.NewBillAction;
import be.jsams.client.swing.action.NewCustomerAction;
import be.jsams.client.swing.action.NewEstimateAction;
import be.jsams.client.swing.action.NewProductAction;

/**
 * A customized {@link JPanel} to have some shortcuts to more used functions.
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
        panel.setBorder(new TitledBorder(JsamsI18nResource.PANEL_SHORTCUT_TOOLBAR.getTranslation()));
        newCustomerButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_CUSTOMER);
        newCustomerButton.addActionListener(new NewCustomerAction());
        newEstimateButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_ESTIMATE);
        newEstimateButton.addActionListener(new NewEstimateAction());
        newBillButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_BILL);
        newBillButton.addActionListener(new NewBillAction());
        newProductButton = new JsamsButton(JsamsI18nResource.BUTTON_NEW_PRODUCT);
        newProductButton.addActionListener(new NewProductAction());
        statisticsButton = new JsamsButton(JsamsI18nResource.BUTTON_STATISTICS);

        panel.add(newCustomerButton);
        panel.add(newEstimateButton);
        panel.add(newBillButton);
        panel.add(newProductButton);
        panel.add(statisticsButton);
        this.add(panel);
    }
    
}
