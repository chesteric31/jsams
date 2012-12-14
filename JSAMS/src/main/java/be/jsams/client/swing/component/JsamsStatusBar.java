package be.jsams.client.swing.component;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 * JSAMS generic status bar, for all messages (warning, error and infos).
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsStatusBar extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1280884825690055717L;

    /** Constraints used to add new controls to this status bar. */
    private final GridBagConstraints gridBagContraints = new GridBagConstraints();

    /**
     * Message to display if there is no message to display. Default to a blank
     * string.
     */
    private String messageWhenEmpty = " ";

    /** Label showing the message in the status bar. */
    private final JsamsLabel labelText = new JsamsLabel();

    private final JPanel labelPanel = new JPanel();

    /**
     * Default constructor.
     */
    public JsamsStatusBar() {
        super(new GridBagLayout());
        initComponents();
    }

    /**
     * Sets the text to display in the message label.
     * 
     * @param text Text to display in the message label.
     */
    public synchronized void setText(String text) {
        String textToDisplay = null;
        if (text != null) {
            textToDisplay = text.trim();
        }
        if (textToDisplay != null && textToDisplay.length() > 0) {
            labelText.setText(textToDisplay);
        } else {
            clearText();
        }
    }

    /**
     * Returns the text label's current value
     * 
     * @return the text
     */
    public synchronized String getText() {
        return labelText.getText();
    }

    /**
     * Clears all text.
     */
    private synchronized void clearText() {
        labelText.setText(messageWhenEmpty);
    }

    /**
     * Clears all icon.
     */
    private synchronized void clearIcon() {
        labelText.setIcon(null);
    }

    /**
     * Sets text when empty.
     * 
     * @param value the value
     */
    public synchronized void setTextWhenEmpty(String value) {
        final boolean wasEmpty = labelText.getText().equals(messageWhenEmpty);
        if (value != null && value.length() > 0) {
            messageWhenEmpty = value;
        } else {
            messageWhenEmpty = " ";
        }
        if (wasEmpty) {
            clearText();
        }
    }

    /**
     * Adds {@link JComponent}.
     * 
     * @param component the {@link JComponent} to add
     */
    public synchronized void addComponent(JComponent component) {
        if (component == null) {
            throw new IllegalArgumentException("JComponent == null");
        }
        component.setBorder(createComponentBorder());
        super.add(component, gridBagContraints);
    }

    /**
     * Remove all the components.
     */
    public synchronized void clear() {
        initComponents();
    }

    /**
     * 
     * @return a {@link Border}
     */
    public static Border createComponentBorder() {
        return BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder());
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        clearText();
        clearIcon();
        removeAll();

        labelPanel.setLayout(new GridLayout(1, 1));
        labelPanel.add(labelText);

        gridBagContraints.anchor = GridBagConstraints.WEST;
        gridBagContraints.weightx = 1.0;
        gridBagContraints.fill = GridBagConstraints.BOTH;
        addComponent(labelPanel);
    }

    /**
     * Sets the text and the icon of the {@link JsamsLabel}.
     * 
     * @param label the {@link JsamsLabel} to use
     */
    public synchronized void setTextWithIcon(JsamsLabel label) {
        this.setText(label.getText());
        labelText.setIcon(label.getIcon());
    }

}
