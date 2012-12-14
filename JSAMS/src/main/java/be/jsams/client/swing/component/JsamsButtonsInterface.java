package be.jsams.client.swing.component;

/**
 * Interface for the methods to implement OK, cancel and reset.
 * 
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public interface JsamsButtonsInterface {

    /**
     * Performs the OK process.
     */
    void performOk();

    /**
     * Performs the cancel process.
     */
    void performCancel();

    /**
     * Performs the reset process.
     */
    void performReset();

}
