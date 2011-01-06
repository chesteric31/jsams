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

    JsamsButton buttonOk = null;
    JsamsButton buttonCancel = null;
    JsamsButton buttonReset = null;

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 8542255661439665645L;

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

    private JsamsButton buildButtonOk() {
        JsamsButton buttonOk = new JsamsButton(JsamsI18nResource.BUTTON_OK);
        buttonOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.performOk();
            }
        });
        return buttonOk;
    }

    private JsamsButton buildButtonCancel() {
        JsamsButton buttonCancel = new JsamsButton(JsamsI18nResource.BUTTON_CANCEL);
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.performCancel();
            }
        });
        return buttonCancel;
    }

    private JsamsButton buildButtonReset() {
        JsamsButton buttonReset = new JsamsButton(JsamsI18nResource.BUTTON_RESET);
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parent.performReset();
            }
        });
        return buttonReset;
    }

}
