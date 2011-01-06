package be.jsams.client.model.dialog;

import java.awt.BorderLayout;
import java.lang.reflect.Field;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import be.jsams.client.desktop.JsamsMainFrame;
import be.jsams.client.i18n.I18nString;
import be.jsams.client.swing.component.JsamsButtonsInterface;
import be.jsams.client.swing.component.JsamsButtonsPanel;
import be.jsams.client.swing.component.JsamsDialog;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsStatusBar;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.server.model.AbstractIdentity;
import be.jsams.server.service.Service;

import com.jgoodies.validation.Severity;
import com.jgoodies.validation.ValidationMessage;
import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.ValidationResultModel;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.DefaultValidationResultModel;
import com.jgoodies.validation.view.ValidationResultViewFactory;

/**
 * Edit generic panel.
 * 
 * @param <M>
 *            a extension of {@link AbstractIdentity}
 * @param <V>
 *            a extension of {@link Validator}
 * @param <S>
 *            an extension of {@link Service}
 * @author chesteric31
 * @version $$Rev$$ $$Date::                  $$ $$Author$$
 */
public abstract class EditDialog<M extends AbstractIdentity, V extends Validator<M>, S extends Service<M>>
		extends JsamsDialog implements JsamsButtonsInterface {

	/**
	 * Serial Version UID
	 */
	private static final long serialVersionUID = 5146784638798425733L;

	private static final Log LOGGER = LogFactory.getLog(EditDialog.class);

	private M model;

	private JsamsButtonsPanel buttonsPanel;

	private ValidationResultModel validationResultModel = new DefaultValidationResultModel();

	private JsamsStatusBar statusBar;

	private JPanel southPanel;

	private Validator<M> validator;

	private Service<M> service;

	public EditDialog(final JsamsMainFrame parent, final I18nString title,
			final String iconFileName) {
		super(parent, title, iconFileName);
		add(buildSouthPanel(), BorderLayout.SOUTH);
	}

	public EditDialog(final JsamsMainFrame parent, final I18nString title) {
		this(parent, title, null);
	}

	public Service<M> getService() {
		return service;
	}

	public void setService(Service<M> service) {
		this.service = service;
	}

	public M getModel() {
		return model;
	}

	public void setModel(M model) {
		this.model = model;
	}

	public ValidationResultModel getValidationResultModel() {
		return validationResultModel;
	}

	public void setValidationResultModel(
			ValidationResultModel validationResultModel) {
		this.validationResultModel = validationResultModel;
	}

	public Validator<M> getValidator() {
		return validator;
	}

	public void setValidator(Validator<M> validator) {
		this.validator = validator;
	}

	private JPanel buildSouthPanel() {
		statusBar = new JsamsStatusBar();
		southPanel = new JPanel();
		southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
		buttonsPanel = new JsamsButtonsPanel(this, true, true, true);
		southPanel.add(buttonsPanel);
		southPanel.add(statusBar);
		return southPanel;
	}

	public void performCancel() {
		this.dispose();
	}

	public void performReset() {
		Class<?> clazz = this.getClass();
		Field[] fields = clazz.getFields();
		for (Field field : fields) {
			try {
				Object value = field.get(this);
				if (value instanceof JsamsTextField) {
					((JsamsTextField) value).setText(null);
				} else if (value instanceof JComboBox) {
					((JComboBox) value).setSelectedIndex(0);
				} else if (value instanceof JTextArea) {
					((JTextArea) value).setText(null);
				}
			} catch (IllegalArgumentException e1) {
				LOGGER.error(e1);
			} catch (IllegalAccessException e1) {
				LOGGER.error(e1);
			}
		}
	}

	public abstract void performOk();

	public boolean postPerformOk(final M object) {
		ValidationResult result = validator.validate(object);
		validationResultModel.setResult(result);
		boolean success = false;
		if (result.hasMessages()) {
			statusBar.removeAll();
			statusBar.repaint();
			List<ValidationMessage> messages = validationResultModel
					.getResult().getMessages();
			for (ValidationMessage message : messages) {
				JsamsLabel label = new JsamsLabel(message.formattedText()
						.replace(".", ""));
				if (message.severity() == Severity.ERROR) {
					label.setIcon(ValidationResultViewFactory.getErrorIcon());
				} else if (message.severity() == Severity.WARNING) {
					label.setIcon(ValidationResultViewFactory.getWarningIcon());
				}
				statusBar.addJComponent(label);
			}
			statusBar.revalidate();
		} else {
			if (getModel() == null) {
				service.create(object);
			} else {
				object.setId(getModel().getId());
				service.update(object);
			}
			success = true;
			dispose();
		}
		return success;
	}

	public JsamsStatusBar getStatusBar() {
		return statusBar;
	}

	public void setStatusBar(JsamsStatusBar statusBar) {
		this.statusBar = statusBar;
	}

}
