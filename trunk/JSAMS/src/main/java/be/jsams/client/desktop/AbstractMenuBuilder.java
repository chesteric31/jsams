package be.jsams.client.desktop;

import be.jsams.client.swing.component.JsamsMenu;

/**
 * Abstract class to build menu.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public abstract class AbstractMenuBuilder {

    /**
     * Build the {@link JsamsMenu}.
     * 
     * @return the created {@link JsamsMenu}
     */
    public abstract JsamsMenu build();

}
