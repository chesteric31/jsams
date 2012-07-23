package be.jsams.common.bean.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for {@link EmailValidator} class.
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class EmailValidatorTest {

    /**
     * @throws java.lang.Exception a possible {@link Exception}
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for {@link be.jsams.common.bean.validator.EmailValidator#validate(java.lang.String)}.
     */
    @Test
    public void testValidate() {
        assertTrue(EmailValidator.validate("chesteric31@gmail.com"));
        assertFalse(EmailValidator.validate("not a good email!!!"));
    }

}
