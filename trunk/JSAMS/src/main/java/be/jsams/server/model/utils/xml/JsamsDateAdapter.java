package be.jsams.server.model.utils.xml;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.DatatypeConverter;

/**
 * Adapter for binding between XML and POJO java, to adapt xs:date to java date.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public final class JsamsDateAdapter {

    /**
     * Constructor.
     */
    private JsamsDateAdapter() {
    }

    /**
     * Converts a XML date to a java date.
     * 
     * @param xml a XML date
     * @return a java date
     */
    public static Date parseDate(String xml) {
        return DatatypeConverter.parseDate(xml).getTime();
    }

    /**
     * Converts a XML date to a java date.
     * 
     * @param javaDate a java date
     * @return a XML date
     */
    public static String printDate(Date javaDate) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(javaDate);
        return DatatypeConverter.printDate(calendar);
    }

}
