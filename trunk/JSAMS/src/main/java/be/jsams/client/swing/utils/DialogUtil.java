package be.jsams.client.swing.utils;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

/**
 * Utility class for dialog management.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public final class DialogUtil {

    /**
     * Constructor to avoid to instance this utility class.
     */
    private DialogUtil() {
    }
    
    /**
     * Center the component.
     * 
     * @param component the {@link Component} to center
     */
    public static void centerComponentOnScreen(Component component) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();

        Point point = new Point();
        point.x += ((dimension.width - component.getWidth()) / 2);
        point.y += ((dimension.height - component.getHeight()) / 2);

        if (point.x < 0) {
            point.x = 0;
        }

        if (point.y < 0) {
            point.y = 0;
        }

        component.setLocation(point);
    }

}
