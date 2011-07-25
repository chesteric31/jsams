package be.jsams.common.bean.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import be.jsams.client.formatter.DoubleFormatter;
import be.jsams.client.model.table.AbstractJsamsTableModel;
import be.jsams.client.swing.component.JsamsComboBox;
import be.jsams.client.swing.component.JsamsFormattedTextField;
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
public class ViewFactory<B extends AbstractIdentityBean<?, ?>> {

    /**
     * Create {@link JsamsTextField} and bind this component to the bean
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
     * Create {@link JsamsComboBox} and bind this component to the bean
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
     * Create {@link JsamsFormattedTextField} and bind this component to the
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
     * Create {@link JDateChooser} and bind this component to the bean
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
     * Create {@link JsamsFormattedTextField} and bind this component to the
     * bean parameter.
     * 
     * @param bean the bean
     * @param property the property to bind
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
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
        JsamsFormattedTextField textField = new JsamsFormattedTextField(factory);
        Bindings.bind(textField, adapter);
        ValidationComponentUtils.setMandatory(textField, mandatory);
        textField.setEnabled(!readOnly);
        return textField;
    }

    /**
     * Create a {@link JTextArea} and bind this component to the bean parameter.
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
     * Create a {@link JCheckBox} and bind this component to the bean parameter.
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
     * Create a {@link JsamsTable} and bind this component to the bean.
     * 
     * @param tableModel the {@link AbstractJsamsTableModel} to set
     * @param mandatory mandatory boolean
     * @param readOnly read only boolean
     * @return the {@link JsamsTable}
     */
    public JsamsTable createBindingTableComponent(final AbstractJsamsTableModel<?> tableModel, final boolean mandatory,
            final boolean readOnly) {
        JsamsTable table = new JsamsTable(true);
        table.setModel(tableModel);
        Bindings.bind(table, tableModel.getListModel(), table.getSelectionModel());
        ValidationComponentUtils.setMandatory(table, mandatory);
        table.setEnabled(!readOnly);
        return table;
    }

}
