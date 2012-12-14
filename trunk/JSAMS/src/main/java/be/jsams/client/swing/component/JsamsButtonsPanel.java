package be.jsams.client.swing.component;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import be.jsams.client.i18n.I18nResource;

import com.jgoodies.forms.factories.ButtonBarFactory;

/**
 * Custom {@link JPanel} with OK, Cancel and Reset buttons.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsButtonsPanel extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8542255661439665645L;

    private static final int DEFAULT_V_GAP = 10;

    private JsamsButtonsInterface parent;

    private JsamsButton buttonOk = null;
    private JsamsButton buttonCancel = null;
    private JsamsButton buttonReset = null;

    public static final String OK_ACTION_KEY = "OK_ACTION_KEY";
    public static final String CANCEL_ACTION_KEY = "CANCEL_ACTION_KEY";
    public static final String RESET_ACTION_KEY = "RESET_ACTION_KEY";
    private AbstractAction okAction = null;
    private AbstractAction cancelAction = null;
    private AbstractAction resetAction = null;

    /**
     * Constructor.
     * 
     * @param parent the parent frame
     * @param okToAdd boolean to add button ok or not
     * @param cancelToAdd boolean to add button cancel or not
     * @param resetToAdd boolean to add button reset or not
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
        List<JButton> buttons = new ArrayList<JButton>();
        if (buttonOk != null) {
            buttons.add(buttonOk);
        }
        if (buttonCancel != null) {
            buttons.add(buttonCancel);
        }
        if (buttonReset != null) {
            buttons.add(buttonReset);
        }
        JButton[] array = (JButton[]) buttons.toArray(new JButton[buttons.size()]);

        this.add(ButtonBarFactory.buildCenteredBar(array), BorderLayout.CENTER);
        // following line for more space
        this.add(new JLabel(), BorderLayout.SOUTH);
    }

    /**
     * Builds the OK button.
     * 
     * @return the OK {@link JsamsButton}
     */
    private JsamsButton buildButtonOk() {
        buttonOk = new JsamsButton(I18nResource.BUTTON_OK);
        okAction = new AbstractAction(buttonOk.getText()) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 8813678697157516871L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                parent.performOk();
            }
        };
        buttonOk.setAction(okAction);
        return buttonOk;
    }

    /**
     * Builds the cancel button.
     * 
     * @return the cancel {@link JsamsButton}
     */
    private JsamsButton buildButtonCancel() {
        buttonCancel = new JsamsButton(I18nResource.BUTTON_CANCEL);
        cancelAction = new AbstractAction(buttonCancel.getText()) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = 677942607120998764L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                parent.performCancel();
            }
        };
        buttonCancel.setAction(cancelAction);
        return buttonCancel;
    }

    /**
     * Builds the reset button.
     * 
     * @return the reset {@link JsamsButton}
     */
    private JsamsButton buildButtonReset() {
        buttonReset = new JsamsButton(I18nResource.BUTTON_RESET);
        resetAction = new AbstractAction(buttonReset.getText()) {

            /**
             * Serial Version UID
             */
            private static final long serialVersionUID = -638120084648685148L;

            @Override
            public void actionPerformed(ActionEvent arg0) {
                parent.performReset();

            }
        };
        buttonReset.setAction(resetAction);
        return buttonReset;
    }

    /**
     * @return the {@link JsamsButton} OK
     */
    public JsamsButton getButtonOk() {
        return buttonOk;
    }

    /**
     * @param buttonOk the {@link JsamsButton} OK to set
     */
    public void setButtonOk(JsamsButton buttonOk) {
        this.buttonOk = buttonOk;
    }

    /**
     * @return the {@link JsamsButton} cancel
     */
    public JsamsButton getButtonCancel() {
        return buttonCancel;
    }

    /**
     * @param buttonCancel the {@link JsamsButton} cancel to set
     */
    public void setButtonCancel(JsamsButton buttonCancel) {
        this.buttonCancel = buttonCancel;
    }

    /**
     * @return the {@link JsamsButton} reset
     */
    public JsamsButton getButtonReset() {
        return buttonReset;
    }

    /**
     * @param buttonReset the {@link JsamsButton} reset
     */
    public void setButtonReset(JsamsButton buttonReset) {
        this.buttonReset = buttonReset;
    }

}
