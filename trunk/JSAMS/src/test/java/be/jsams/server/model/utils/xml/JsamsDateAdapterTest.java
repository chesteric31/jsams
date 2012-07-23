package be.jsams.server.model.utils.xml;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link JsamsDateAdapter} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class JsamsDateAdapterTest {

    private static final String DATE = "2012-07-23+02:00";
    private Calendar calendar;

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
        calendar = Calendar.getInstance();
        calendar.set(2012, 6, 23, 0, 0, 0); // 23 July 2012
        calendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * Test method for {@link be.jsams.server.model.utils.xml.JsamsDateAdapter#parseDate(java.lang.String)}.
     */
    @Test
    public void testParseDate() {
        assertEquals(calendar.getTime(), JsamsDateAdapter.parseDate(DATE));
    }

    /**
     * Test method for {@link be.jsams.server.model.utils.xml.JsamsDateAdapter#printDate(java.util.Date)}.
     */
    @Test
    public void testPrintDate() {
        assertEquals(DATE, JsamsDateAdapter.printDate(calendar.getTime()));
    }

}
