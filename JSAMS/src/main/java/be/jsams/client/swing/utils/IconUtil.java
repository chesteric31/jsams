package be.jsams.client.swing.utils;

import java.awt.Image;
import java.awt.Toolkit;

/**
 * Utility class for icon management.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class IconUtil {

    public static final String MENU_ICON_PREFIX = "org/freedesktop/tango/16x16/";
    public static final String TITLE_ICON_PREFIX = "org/freedesktop/tango/32x32/";

    /**
     * Constructor to avoid to instance this utility class.
     */
    private IconUtil() {
    }

    /**
     * Builds an {@link Image} icon from a path file name.
     * 
     * @param fileName the path file name
     * @return the built image icon
     */
    public static Image buildIcon(final String fileName) {
        Image image = null;
        if (fileName != null) {
            image = Toolkit.getDefaultToolkit().getImage(
                    Thread.currentThread().getContextClassLoader().getResource(fileName));
        }
        return image;
    }

}
