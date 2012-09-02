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
     * @throws ParseException 
     */
    @Test
    public void testValueToStringObjectForNull() throws ParseException {
        String string = formatter.valueToString(null);
        assertEquals("", string);
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     * @throws ParseException 
     */
    @Test(expected = ClassCastException.class)
    public void testValueToStringObjectForStringValue() throws ParseException {
        formatter.valueToString("string");
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     * @throws ParseException 
     */
    @Test
    public void testValueToStringObjectForDecimalValue() throws ParseException {
        String string = formatter.valueToString(2.5);
        assertEquals("2,5", string);
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#valueToString(java.lang.Object)}
     * .
     * @throws ParseException 
     */
    @Test
    public void testValueToStringObjectForIntValue() throws ParseException {
        String string = formatter.valueToString(2);
        assertEquals("2", string);
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     * @throws ParseException 
     */
    @Test
    public void testStringToValueStringForNull() throws ParseException {
        Object value = formatter.stringToValue(null);
        assertEquals(null, value);
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     * 
     * @throws ParseException the expected {@link ParseException}
     */
    @Test(expected = ParseException.class)
    public void testStringToValueStringForEmpty() throws ParseException {
        formatter.stringToValue("");
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     * @throws ParseException 
     */
    @Test
    public void testStringToValueStringForDecimalValue() throws ParseException {
        Object value = formatter.stringToValue("2,523");
        assertEquals(2.523, value);
    }

    /**
     * Test method for
     * {@link be.jsams.client.formatter.DoubleFormatter#stringToValue(java.lang.String)}
     * .
     * @throws ParseException 
     */
    @Test
    public void testStringToValueStringForIntValue() throws ParseException {
        Object value = formatter.stringToValue("2");
        assertEquals(2.0, value);
    }

}
