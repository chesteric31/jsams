package be.jsams.client.swing.component;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

/**
 * Jsams generic status bar, for all messages (warning, error and infos).
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class JsamsStatusBar extends JPanel {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = -1280884825690055717L;

    /**
     * Message to display if there is no message to display. Default to a blank string.
     */
    private String messageWhenEmpty = " ";

    /** Label showing the message in the status bar. */
    private final JLabel labelText = new JLabel();

    private final JProgressBar progressBar = new JProgressBar();

    private final JPanel labelOrProgressPanel = new JPanel();

    /** Constraints used to add new controls to this status bar. */
    private final GridBagConstraints gribBagContraints = new GridBagConstraints();

    private Font font;

    /**
     * Default constructor.
     */
    public JsamsStatusBar() {
        super(new GridBagLayout());
        createGUI();
    }

    /**
     * Sets the font for controls in this status bar.
     * 
     * @param font
     *            The font to use.
     * 
     * @throws IllegalArgumentException
     *             Thrown if <TT>null</TT> <TT>Font</TT> passed.
     */
    public synchronized void setFont(Font font) {
        if (font == null) {
            throw new IllegalArgumentException("Font == null");
        }
        super.setFont(font);
        this.font = font;
        updateSubcomponentsFont(this);
    }

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
     * @param comp
     *            the {@link JComponent} to add
     */
    public synchronized void addJComponent(JComponent comp) {
        if (comp == null) {
            throw new IllegalArgumentException("JComponent == null");
        }
        comp.setBorder(createComponentBorder());
        if (font != null) {
            comp.setFont(font);
            updateSubcomponentsFont(comp);
        }
        super.add(comp, gribBagContraints);
    }

    /**
     * 
     * @return a {@link Border}
     */
    public static Border createComponentBorder() {
        return BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED), BorderFactory
                .createEmptyBorder(0, 4, 0, 4));
    }

    /**
     * Creates the GUI.
     */
    private void createGUI() {
        clearText();

        Dimension progSize = progressBar.getPreferredSize();
        progSize.height = labelText.getPreferredSize().height;
        progressBar.setPreferredSize(progSize);

        progressBar.setStringPainted(true);

        labelOrProgressPanel.setLayout(new GridLayout(1, 1));
        labelOrProgressPanel.add(labelText);

        // The message area is on the right of the status bar and takes
        // up all available space.
        gribBagContraints.anchor = GridBagConstraints.WEST;
        gribBagContraints.weightx = 1.0;
        gribBagContraints.fill = GridBagConstraints.HORIZONTAL;
        gribBagContraints.gridy = 0;
        gribBagContraints.gridx = 0;
        addJComponent(labelOrProgressPanel);

        // Any other components are on the right.
        gribBagContraints.weightx = 0.0;
        gribBagContraints.anchor = GridBagConstraints.CENTER;
        gribBagContraints.gridx = GridBagConstraints.RELATIVE;
    }

    /**
     * Updates the sub components font.
     * 
     * @param cont
     *            the {@link Container}
     */
    private void updateSubcomponentsFont(Container cont) {
        Component[] comps = cont.getComponents();
        for (int i = 0; i < comps.length; ++i) {
            comps[i].setFont(font);
            if (comps[i] instanceof Container) {
                updateSubcomponentsFont((Container) comps[i]);
            }
        }
    }

    /**
     * Sets status bar progress.
     * 
     * @param msg
     *            the message
     * @param minimum
     *            the minimum
     * @param maximum
     *            the maximum
     * @param value
     *            the value
     */
    public void setStatusBarProgress(String msg, int minimum, int maximum, int value) {
        Component firstComponent = labelOrProgressPanel.getComponent(0);
        if (!(firstComponent instanceof JProgressBar)) {
            labelOrProgressPanel.remove(0);
            labelOrProgressPanel.add(progressBar);
            validate();
        }

        progressBar.setMinimum(minimum);
        progressBar.setMaximum(maximum);
        progressBar.setValue(value);

        if (null != msg) {
            progressBar.setString(msg);
        } else {
            progressBar.setString("");
        }
    }

    /**
     * Sets status bar progress finish.
     */
    public void setStatusBarProgressFinished() {
        Component firstComponent = labelOrProgressPanel.getComponent(0);
        if (firstComponent instanceof JProgressBar) {
            labelOrProgressPanel.remove(0);
            labelOrProgressPanel.add(labelText);
            validate();
            repaint();
        }
    }

}
