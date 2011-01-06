package be.jsams.client.swing.utils;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * 
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class IconUtil {

    public static final String MENU_ICON_PREFIX = "org/freedesktop/tango/16x16/";

    public static final String TITLE_ICON_PREFIX = "org/freedesktop/tango/32x32/";

    public static Image buildIcon(final String fileName) {
        if (fileName != null) {
            return Toolkit.getDefaultToolkit().getImage(
                    Thread.currentThread().getContextClassLoader().getResource(fileName));
        } else {
            return null;
        }
    }

}
