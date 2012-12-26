package be.jsams.common.bean.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import be.jsams.client.formatter.DoubleFormatter;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.client.swing.component.JsamsFormattedTextField;
import be.jsams.client.swing.component.JsamsLabel;
import be.jsams.client.swing.component.JsamsTable;
import be.jsams.client.swing.component.JsamsTextField;
import be.jsams.common.bean.model.AbstractIdentityBean;

import com.jgoodies.binding.adapter.BasicComponentFactory;
import com.jgoodies.binding.adapter.Bindings;
import com.jgoodies.binding.beans.PropertyAdapter;
import com.jgoodies.common.format.EmptyNumberFormat;
import com.jgoodies.validation.view.ValidationComponentUtils;
import com.toedter.calendar.JDateChooser;

/**
 * A View factory to create all the Swing components from a bean property.
 * 
 * @param <B> an extension of {@link AbstractIdentityBean}
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class ViewFactory<B extends AbstractIdentityBean<?, ?>> {

    /**
     * Creates {@link JsamsTextField} and binds this component to the bean
     * parameter.
     * 
     * @param bean the bean
     * @param property the property to bind
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsTextField}
     */
    public JsamsTextField createBindingTextComponent(final B bean, final String property, final boolean mandatory,
            final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        JsamsTextField textField = new JsamsTextField();
        Bindings.bind(textField, adapter, false);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Creates {@link JsamsComboBox} and binds this component to the bean
     * parameter.
     * 
     * @param bean the Bean object to bind
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @param renderer a {@link ListCellRenderer}
     * @return the {@link JsamsComboBox}
     */
    public JsamsComboBox createBindingComboComponent(B bean, final boolean mandatory, final boolean readOnly,
            final ListCellRenderer renderer) {
        AbstractBeanView<?> view = bean.getView();
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
     * Creates {@link JsamsFormattedTextField} and binds this component to the
     * bean parameter.
     * 
     * @param bean the bean
     * @param property the property to bind
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsFormattedTextField}
     */
    public JsamsFormattedTextField createBindingIntComponent(final B bean, final String property,
            final boolean mandatory, final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        NumberFormatter formatter = new NumberFormatter(new EmptyNumberFormat(NumberFormat.getIntegerInstance(), null));
        formatter.setValueClass(Integer.class);
        JsamsFormattedTextField textField = new JsamsFormattedTextField(formatter);
        Bindings.bind(textField, adapter);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Creates {@link JDateChooser} and binds this component to the bean
     * parameter.
     * 
     * @param bean the bean
     * @param property the property
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JDateChooser}
     */
    public JDateChooser createBindingDateComponent(final B bean, final String property, final boolean mandatory,
            final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        JDateChooser dateField = new JDateChooser();
        Bindings.bind((JComponent) dateField, "date", adapter);
        ValidationComponentUtils.setMandatory(dateField, mandatory);
        dateField.setEnabled(!readOnly);
        return dateField;
    }

    /**
     * Creates {@link JsamsFormattedTextField} and binds this component to the
     * bean parameter.
     * 
     * @param bean the bean
     * @param property the property to bind
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsFormattedTextField}
     */
    public JsamsFormattedTextField createBindingCurrencyComponent(final B bean, final String property,
            final boolean mandatory, final boolean readOnly) {
        // Definition of the default/display formatter
        NumberFormat numberFormat = DecimalFormat.getCurrencyInstance();
        JsamsFormattedTextField textField = buildFormattedTextField(bean, property, mandatory, readOnly, numberFormat,
                null);
        return textField;
    }

    /**
     * Builds the {@link JsamsFormattedTextField} from the parameters
     * 
     * @param bean the bean
     * @param property the property
     * @param mandatory the mandatory boolean
     * @param readOnly the read only boolean
     * @param numberFormat the number format
     * @param displayFormatter the display formatter
     * @return the built {@link JsamsFormattedTextField}
     */
    private JsamsFormattedTextField buildFormattedTextField(final B bean, final String property,
            final boolean mandatory, final boolean readOnly, NumberFormat numberFormat,
            NumberFormatter displayFormatter) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
        NumberFormatter defaultFormatter = new NumberFormatter(numberFormat);
        NumberFormatter doubleFormatter = new DoubleFormatter();
        if (displayFormatter == null) {
            displayFormatter = defaultFormatter;
        }
        DefaultFormatterFactory factory = new DefaultFormatterFactory(defaultFormatter, displayFormatter,
                doubleFormatter);
        JsamsFormattedTextField textField = new JsamsFormattedTextField(factory);
        Bindings.bind(textField, adapter);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Creates {@link JsamsFormattedTextField} and binds this component to the
     * bean parameter.
     * 
     * @param bean the bean
     * @param property the property to bind
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsFormattedTextField}
     */
    public JsamsFormattedTextField createBindingPercentageComponent(final B bean, final String property,
            final boolean mandatory, final boolean readOnly) {
        NumberFormat numberFormat = DecimalFormat.getPercentInstance();
        NumberFormatter displayFormatter = new NumberFormatter(numberFormat) {
            /**
             * Serial
             */
            private static final long serialVersionUID = -6935064680616634906L;

            public String valueToString(Object o) throws ParseException {
                Number number = (Number) o;
                if (number != null) {
                    double d = number.doubleValue() / 100.0;
                    number = new Double(d);
                }
                return super.valueToString(number);
            }

            public Object stringToValue(String s) throws ParseException {
                Number number = (Number) super.stringToValue(s);
                if (number != null) {
                    double d = number.doubleValue() * 100.0;
                    number = new Double(d);
                }
                return number;
            }
        };
        JsamsFormattedTextField textField = buildFormattedTextField(bean, property, mandatory, readOnly, numberFormat,
                displayFormatter);
        return textField;
    }

    /**
     * Creates a {@link JTextArea} and binds this component to the bean parameter.
     * 
     * @param bean the bean
     * @param property the property
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
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

    /**
     * Creates a {@link JCheckBox} and binds this component to the bean parameter.
     * 
     * @param bean the bean
     * @param property the property
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JCheckBox}
     */
    public JCheckBox createBindingBooleanComponent(final B bean, String property, final boolean mandatory,
            final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        JCheckBox checkBox = BasicComponentFactory.createCheckBox(adapter, "");
        ValidationComponentUtils.setMandatory(checkBox, mandatory);
        checkBox.setEnabled(!readOnly);
        return checkBox;
    }

    /**
     * Creates a {@link JsamsTable} and binds this component to the bean.
     * 
     * @param tableModel the {@link AbstractJsamsTableModel} to set
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsTable}
     */
    public JsamsTable createBindingTableComponent(final AbstractJsamsTableModel<?> tableModel, final boolean mandatory,
            final boolean readOnly) {
        JsamsTable table = new JsamsTable();
        table.setModel(tableModel);
        Bindings.bind(table, tableModel.getListModel(), table.getSelectionModel());
        ValidationComponentUtils.setMandatory(table, mandatory);
        table.setEnabled(!readOnly);
        return table;
    }

    /**
     * Creates a {@link JRadioButton} and binds this component to the bean
     * parameter.
     * 
     * @param bean the bean
     * @param property the property
     * @param choice the choice
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JRadioButton}
     */
    public JRadioButton createBindingRadioComponent(final B bean, final String property, final int choice,
            final boolean mandatory, final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        JRadioButton radioButton = BasicComponentFactory.createRadioButton(adapter, choice, "");
        ValidationComponentUtils.setMandatory(radioButton, mandatory);
        radioButton.setEnabled(!readOnly);
        return radioButton;
    }

    /**
     * Creates a {@link JsamsLabel} and binds this component to the bean
     * parameter.
     * 
     * @param bean the bean
     * @param property the property
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsLabel}
     */
    public JsamsLabel createBindingLabelComponent(final B bean, final String property,
            final boolean mandatory, final boolean readOnly) {
        PropertyAdapter<B> adapter = new PropertyAdapter<B>(bean, property, true);
        JsamsLabel label = new JsamsLabel();
        Bindings.bind(label, adapter);
        ValidationComponentUtils.setMandatory(label, mandatory);
        label.setEnabled(!readOnly);
        return label;
    }

}
