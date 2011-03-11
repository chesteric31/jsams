package be.jsams.common.bean.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import be.jsams.client.formatter.DoubleFormatter;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.AbstractIdentityBean;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.common.format.EmptyNumberFormat;
import com.jgoodies.validation.view.ValidationComponentUtils;

/**
 * A View factory to create all the Swing components from a bean property.
 * 
 * @param <B>
 *            an extension of {@link AbstractIdentityBean}
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class ViewFactory<B extends AbstractIdentityBean<?, ?>> {

    /**
     * Creates {@link JsamsTextField} and binds this component to the bean parameter.
     * 
     * @param bean
     *            the bean
     * @param property
     *            the property to bind
     * @param mandatory
     *            mandatory boolean
     * @param readOnly
     *            read only boolean
     * @return the {@link JsamsTextField}
     */
    public JsamsTextField createBindingTextComponent(final B bean, final String property, final boolean mandatory,
            final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        // JsamsTextField textField = (JsamsTextField) BasicComponentFactory.createTextField(adapter);
        JsamsTextField textField = new JsamsTextField();
        Bindings.bind(textField, adapter, true);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Creates {@link JsamsComboBox} and binds this component to the bean parameter.
     * 
     * @param bean
     *            the Bean object to bind
     * @param mandatory
     *            mandatory boolean
     * @param readOnly
     *            read only boolean
     * @param renderer
     *            a {@link ListCellRenderer}
     * @return the {@link JsamsComboBox}
     */
    public JsamsComboBox createBindingComboComponent(B bean, final boolean mandatory, final boolean readOnly,
            final ListCellRenderer renderer) {
        AbstractView<?, ?, ?> view = bean.getView();
        // JComboBox comboBox = BasicComponentFactory.createComboBox(view.getSelectionInList(), renderer);
        JsamsComboBox comboBox = new JsamsComboBox();
        Bindings.bind(comboBox, view.getSelectionInList());
        if (renderer != null) {
            comboBox.setRenderer(renderer);
        }
        ValidationComponentUtils.setMandatory(comboBox, mandatory);
        comboBox.setEnabled(!readOnly);
        return comboBox;
    }

    /**
     * Creates {@link JsamsFormattedTextField} and binds this component to the bean parameter.
     * 
     * @param bean
     *            the bean
     * @param property
     *            the property to bind
     * @param mandatory
     *            mandatory boolean
     * @param readOnly
     *            read only boolean
     * @return the {@link JsamsFormattedTextField}
     */
    public JsamsFormattedTextField createBindingIntComponent(final B bean, final String property,
            final boolean mandatory, final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        // JsamsFormattedTextField textField = (JsamsFormattedTextField)
        // BasicComponentFactory.createIntegerField(adapter);
        NumberFormatter formatter = new NumberFormatter(new EmptyNumberFormat(NumberFormat.getIntegerInstance(), null));
        formatter.setValueClass(Integer.class);
        JsamsFormattedTextField textField = new JsamsFormattedTextField(formatter);
        Bindings.bind(textField, adapter);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Creates {@link JsamsFormattedTextField} and binds this component to the bean parameter.
     * 
     * @param bean
     *            the bean
     * @param property
     *            the property to bind
     * @param mandatory
     *            mandatory boolean
     * @param readOnly
     *            read only boolean
     * @return the {@link JsamsFormattedTextField}
     */
    public JsamsFormattedTextField createBindingDecimalComponent(final B bean, final String property,
            final boolean mandatory, final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        // Definition of the default/display formatter
        NumberFormat numberFormat = DecimalFormat.getInstance();
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        NumberFormatter defaultFormatter = new NumberFormatter(numberFormat);
        // Definition of the double customized formatter
        DoubleFormatter doubleFormatter = new DoubleFormatter();
        DefaultFormatterFactory factory = new DefaultFormatterFactory(defaultFormatter, defaultFormatter,
                doubleFormatter);
        // JsamsFormattedTextField textField = (JsamsFormattedTextField) BasicComponentFactory.createFormattedTextField(
        // adapter, factory);
        JsamsFormattedTextField textField = new JsamsFormattedTextField(factory);
        Bindings.bind(textField, adapter);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Creates a {@link JTextArea} and binds this component to the bean parameter.
     * 
     * @param bean
     *            the bean
     * @param property
     *            the property
     * @param mandatory
     *            mandatory boolean
     * @param readOnly
     *            read only boolean
     * @return the {@link JTextArea}
     */
    public JTextArea createBindingTextAreaComponent(final B bean, final String property, final boolean mandatory,
            final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        JTextArea textArea = BasicComponentFactory.createTextArea(adapter);
        ValidationComponentUtils.setMandatory(textArea, mandatory);
        textArea.setEnabled(!readOnly);
        return textArea;
    }
}