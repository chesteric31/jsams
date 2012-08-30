package be.jsams.client.i18n;

import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link UserContext} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class UserContextTest {

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link be.jsams.client.i18n.UserContext#getLocale()}.
     */
    @Test
    public void testGetLocale() {
        Locale newLocale = Locale.CANADA;
        Locale.setDefault(newLocale);
        Locale locale = UserContext.getLocale();
        assertTrue(newLocale.equals(locale));
    }

    /**
     * Test method for {@link be.jsams.client.i18n.UserContext#getLanguage()}.
     */
    @Test
    public void testGetLanguage() {
        Locale newLocale = Locale.CANADA;
        Locale.setDefault(newLocale);
        String language = UserContext.getLanguage();
        assertTrue(newLocale.getLanguage().equals(language));
    }

    /**
     * Test method for {@link be.jsams.client.i18n.UserContext#isFrench()}.
     */
    @Test
    public void testIsFrench() {
        Locale newLocale = Locale.FRANCE;
        Locale.setDefault(newLocale);
        assertTrue(UserContext.isFrench());
    }

    /**
     * Test method for {@link be.jsams.client.i18n.UserContext#isDutch()}.
     */
    @Test
    public void testIsDutch() {
        Locale newLocale = new Locale("nl", "Belgium");
        Locale.setDefault(newLocale);
        assertTrue(UserContext.isDutch());
    }

    /**
     * Test method for {@link be.jsams.client.i18n.UserContext#isEnglish()}.
     */
    @Test
    public void testIsEnglish() {
        Locale newLocale = Locale.US;
        Locale.setDefault(newLocale);
        assertTrue(UserContext.isEnglish());
    }

}
