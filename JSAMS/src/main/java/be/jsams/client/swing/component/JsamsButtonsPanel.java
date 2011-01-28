package be.jsams.client.swing.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import be.jsams.client.i18n.JsamsI18nResource;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Custom {@link JPanel} with OK, Cancel and Reset buttons.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsButtonsPanel extends JPanel {

    private static final int DEFAULT_V_GAP = 10;

    private JsamsButtonsInterface parent;

    private JsamsButton buttonOk = null;
    private JsamsButton buttonCancel = null;
    private JsamsButton buttonReset = null;

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8542255661439665645L;

    /**
     * Constructor.
     * 
     * @param parent
     *            the parent frame
     * @param okToAdd
     *            boolean to add button ok or not
     * @param cancelToAdd
     *            boolean to add button cancel or not
     * @param resetToAdd
     *            boolean to add button reset or not
     */
    public JsamsButtonsPanel(final JsamsButtonsInterface parent, final boolean okToAdd, final boolean cancelToAdd,
            final boolean resetToAdd) {
        super();
        this.parent = parent;
        if (okToAdd) {
            buttonOk = buildButtonOk();
        }
        if (cancelToAdd) {
            buttonCancel = buildButtonCancel();
        }
        if (resetToAdd) {
            buttonReset = buildButtonReset();
        }
        initComponents();
    }

    /**
     * Initializes the components.
     */
    private void initComponents() {
        BorderLayout buttonsLayout = new BorderLayout();
        buttonsLayout.setVgap(DEFAULT_V_GAP);
        this.setLayout(buttonsLayout);
        this.add(new JSeparator(), BorderLayout.NORTH);
        this.add(ButtonBarFactory.buildCenteredBar(new JButton[] { buttonOk, buttonCancel, buttonReset }),
                BorderLayout.CENTER);
        // following line for more space
        this.add(new JLabel(), BorderLayout.SOUTH);
    }

    /**
     * Builds the OK button.
     * 
     * @return the OK {@link JsamsButton}
     */
    private JsamsButton buildButtonOk() {
        JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.performOk();
            }
        });
        return buttonOk;
    }

    /**
     * Builds the cancel button.
     * 
     * @return the cancel {@link JsamsButton}
     */
    private JsamsButton buildButtonCancel() {
        JsamsButton buttonCancel = new JsamsButton(JsamsI18nResource.BUTTON_CANCEL);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.performCancel();
            }
        });
        return buttonCancel;
    }

    /**
     * Builds the reset button.
     * 
     * @return the reset {@link JsamsButton}
     */
    private JsamsButton buildButtonReset() {
        JsamsButton buttonReset = new JsamsButton(JsamsI18nResource.BUTTON_RESET);
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.performReset();
            }
        });
        return buttonReset;
    }

    /**
     * 
     * @return the {@link JsamsButton} OK
     */
    public JsamsButton getButtonOk() {
        return buttonOk;
    }

    /**
     * 
     * @param buttonOk
     *            the {@link JsamsButton} OK to set
     */
    public void setButtonOk(JsamsButton buttonOk) {
        this.buttonOk = buttonOk;
    }

    /**
     * 
     * @return the {@link JsamsButton} cancel
     */
    public JsamsButton getButtonCancel() {
        return buttonCancel;
    }

    /**
     * 
     * @param buttonCancel the {@link JsamsButton} cancel to set
     */
    public void setButtonCancel(JsamsButton buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    /**
     * 
     * @return the {@link JsamsButton} reset
     */
    public JsamsButton getButtonReset() {
        return buttonReset;
    }

    /**
     * 
     * @param buttonReset the {@link JsamsButton} reset
     */
    public void setButtonReset(JsamsButton buttonReset) {
        this.buttonReset = buttonReset;
    }

}
