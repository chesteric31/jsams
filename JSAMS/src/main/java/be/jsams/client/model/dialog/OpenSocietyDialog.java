package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import be.jsams.client.desktop.JsamsDesktop;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.i18n.JsamsI18nLabelResource;
import be.jsams.client.i18n.JsamsI18nResource;
import be.jsams.client.renderer.NamedComboBoxRenderer;
import be.jsams.client.swing.component.JsamsButton;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsFrame;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.utils.IconUtil;
import be.jsams.common.bean.model.SocietyBean;
import be.jsams.common.bean.view.ViewFactory;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Open society {@link JsamsDialog}.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class OpenSocietyDialog extends JsamsDialog implements JsamsButtonsInterface {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 237617341189579756L;

    private JsamsComboBox comboBox = null;

    private JsamsButtonsPanel buttonsPanel;

    private SocietyBean bean;

    private JsamsStatusBar statusBar;

    /**
     * Constructor
     * 
     * @param title
     *            the {@link I18nString} title
     */
    public OpenSocietyDialog(final I18nString title) {
        super(null, title);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        buttonsPanel = new JsamsButtonsPanel(this, true, false, false);
        add(buttonsPanel, BorderLayout.SOUTH);
        initComponents();
    }

    /**
     * Initializes all the components
     */
    private void initComponents() {
        FormLayout layout = new FormLayout("right:pref, 3dlu, pref, 3dlu, pref", "pref, 5dlu");
        DefaultFormBuilder builder = new DefaultFormBuilder(layout, JsamsFrame.RESOURCE_BUNDLE);
        builder.setDefaultDialogBorder();
        builder.append(JsamsI18nLabelResource.LABEL_OPEN_SOCIETY.getTranslation());
        builder.nextLine();
        builder.appendSeparator();
        ViewFactory<SocietyBean> helper = new ViewFactory<SocietyBean>();
        bean = new SocietyBean();
        comboBox = helper.createBindingComboComponent(bean, true, false, new NamedComboBoxRenderer());
        comboBox.setRenderer(new NamedComboBoxRenderer());
        builder.append(JsamsI18nLabelResource.LABEL_AVAILABLES_SOCIETIES.getTranslation(), comboBox);
        JsamsButton buttonNewSociety = buildButtonNewSociety();
        builder.append(buttonNewSociety);
        builder.nextLine();
        JPanel panel = builder.getPanel();
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(panel, BorderLayout.CENTER);

        statusBar = new JsamsStatusBar();
        mainPanel.add(statusBar, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    /**
     * {@inheritDoc}
     */
    public void performOk() {
        SocietyBean selectedSociety = (SocietyBean) bean.getSelection();
        if (selectedSociety != null) {
            JsamsDesktop.getInstance().setCurrentSociety(selectedSociety);
            dispose();
        } else {
            statusBar.removeAll();
            statusBar.repaint();
            JsamsLabel label = new JsamsLabel(JsamsI18nLabelResource.LABEL_ERROR_MANDATORY_SOCIETY,
                    ValidationResultViewFactory.getErrorIcon());
            statusBar.addComponent(label);
            statusBar.validate();
        }
    }

    /**
     * {@inheritDoc}
     */
    public void performCancel() {
        dispose();
    }

    /**
     * {@inheritDoc}
     */
    public void performReset() {
        comboBox.setSelectedIndex(0);
    }

    /**
     * Builds the new society button.
     * 
     * @return the new society button
     */
    private JsamsButton buildButtonNewSociety() {
        JsamsButton buttonNewSociety = new JsamsButton(JsamsI18nResource.BUTTON_OPEN_SOCIETIES_NEW,
                IconUtil.MENU_ICON_PREFIX + "actions/folder-new.png");
        buttonNewSociety.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                EditSocietyDialog dialog = new EditSocietyDialog(JsamsI18nResource.TITLE_EDIT_SOCIETY,
                        new SocietyBean());
                if (dialog.isSuccess()) {
                    dispose();
                }
            }
        });
        return buttonNewSociety;
    }
}
