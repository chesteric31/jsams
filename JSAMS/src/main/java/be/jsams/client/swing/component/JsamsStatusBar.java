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
     * Default constructor.
     */
    public JsamsStatusBar() {
        super(new GridBagLayout());
        initComponents();
    }

    /**
     * Message to display if there is no message to display. Default to a blank string.
     */
    private String messageWhenEmpty = " ";

    /** Label showing the message in the status bar. */
    private final JsamsLabel labelText = new JsamsLabel();

    private final JPanel labelPanel = new JPanel();

    /**
     * Sets the text to display in the message label.
     * 
     * @param text
     *            Text to display in the message label.
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
    public synchronized void clearText() {
        labelText.setText(messageWhenEmpty);
    }

    /**
     * Sets text when empty.
     * 
     * @param value
     *            the value
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
     * @param component
     *            the {@link JComponent} to add
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
        return BorderFactory.createCompoundBorder(BorderFactory.createRaisedBevelBorder(), BorderFactory
                .createLoweredBevelBorder());
    }

    /**
     * Initializes all the components.
     */
    private void initComponents() {
        clearText();
        removeAll();

        labelPanel.setLayout(new GridLayout(1, 1));
        labelPanel.add(labelText);

        // The message area is on the right of the status bar and takes
        // up all available space.
        // gribBagContraints.anchor = GridBagConstraints.WEST;
        // gribBagContraints.weightx = 1.0;
        // gribBagContraints.fill = GridBagConstraints.HORIZONTAL;
        // gribBagContraints.gridy = 0;
        // gribBagContraints.gridx = 0;
        // addComponent(labelPanel);
        // // Any other components are on the right.
        // gribBagContraints.weightx = 0.0;
        // gribBagContraints.anchor = GridBagConstraints.CENTER;
        // gribBagContraints.gridx = GridBagConstraints.RELATIVE;
        gridBagContraints.anchor = GridBagConstraints.WEST;
        gridBagContraints.weightx = 1.0;
        gridBagContraints.fill = GridBagConstraints.BOTH;
        addComponent(labelPanel);
    }

}
