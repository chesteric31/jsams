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
     * Builds the {@link JsamsMenu}.
     * 
     * @return the created {@link JsamsMenu}
     */
    public abstract JsamsMenu build();
    
    /**
     * Enables/disables some menu items not yet active/already active.
     * 
     * @param value the boolean value, true if enable, false if disable
     */
    public abstract void enableMenuItems(boolean value);

}
