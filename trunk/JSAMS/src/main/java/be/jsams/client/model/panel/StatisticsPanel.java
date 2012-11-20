package be.jsams.client.model.panel;

import java.awt.BorderLayout;
import java.math.BigDecimal;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import be.jsams.client.context.JsamsApplicationContext;
import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;

/**
 * Statistics panel.
 * 
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public class StatisticsPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 5941212055654534097L;

    private JsamsStatusBar statusBar = new JsamsStatusBar();

    /**
     * Constructor.
     */
    public StatisticsPanel() {
        super();
        setLayout(new BorderLayout());
        buildMainPanel();
    }

    /**
     * @return the statusBar
     */
    public JsamsStatusBar getStatusBar() {
        return statusBar;
    }

    /**
     * @param statusBar the statusBar to set
     */
    public void setStatusBar(JsamsStatusBar statusBar) {
        this.statusBar = statusBar;
    }

    /**
     * Builds the main panel contained all the components.
     */
    protected void buildMainPanel() {
        BigDecimal globalTurnover = JsamsApplicationContext.getBillService().findGlobalTurnover(
                JsamsDesktop.getInstance().getCurrentSociety());
        this.add(new JsamsLabel(globalTurnover.toPlainString()));
        // JPanel searchCriteriaPanel = buildCriteriaPanel();
        // this.add(searchCriteriaPanel, BorderLayout.NORTH);
        // JScrollPane scrollPane = buildResultPanel();
        // this.add(scrollPane, BorderLayout.CENTER);
        // JPanel southPanel = buildSouthPanel();
        // this.add(southPanel, BorderLayout.SOUTH);
    }

    /**
     * Builds the south panel with buttons and status bar.
     * 
     * @return the south panel with buttons and status bar.
     */
    protected JPanel buildSouthPanel() {
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        southPanel.setBorder(BorderFactory.createEtchedBorder());
        southPanel.add(statusBar);
        return southPanel;
    }

}
