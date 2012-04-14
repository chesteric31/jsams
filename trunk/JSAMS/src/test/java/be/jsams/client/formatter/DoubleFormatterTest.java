package be.jsams.client.formatter;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link DoubleFormatter}.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class DoubleFormatterTest {
    
    private DoubleFormatter formatter;

    /**
     * Setup method called before all test method
     */
    @Before
    public void setUp() {
        formatter = new DoubleFormatter();
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     */
    @Test
    public void testValueToStringObjectForNull() {
        try {
            String string = formatter.valueToString(null);
            assertEquals("", string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     */
    @Test(expected = ClassCastException.class)
    public void testValueToStringObjectForStringValue() {
        try {
            formatter.valueToString("string");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     */
    @Test
    public void testValueToStringObjectForDecimalValue() {
        try {
            String string = formatter.valueToString(2.5);
            assertEquals("2,5", string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     */
    @Test
    public void testValueToStringObjectForIntValue() {
        try {
            String string = formatter.valueToString(2);
            assertEquals("2", string);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     */
    @Test
    public void testStringToValueStringForNull() {
        try {
            Object value = formatter.stringToValue(null);
            assertEquals(null, value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     * 
     * @throws ParseException
     */
    @Test(expected = ParseException.class)
    public void testStringToValueStringForEmpty() throws ParseException {
        formatter.stringToValue("");
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     */
    @Test
    public void testStringToValueStringForDecimalValue() {
        try {
            Object value = formatter.stringToValue("2,523");
            assertEquals(2.523, value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     */
    @Test
    public void testStringToValueStringForIntValue() {
        try {
            Object value = formatter.stringToValue("2");
            assertEquals(2.0, value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

}
