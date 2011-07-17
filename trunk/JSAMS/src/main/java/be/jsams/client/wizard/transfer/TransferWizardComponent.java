package be.jsams.client.wizard.transfer;

import be.jsams.client.wizard.DefaultJsamsWizardComponent;

/**
 * Kind of memory of the wizard to store the source, destination and transfer mode selections.
 * source: 1 for estimate, 2 for command, 3 for delivery order and 4 for bill
 * destination: 1 for command, 2 for delivery order, 3 for bill and 4 for credit note
 * mode: 1 for full mode, 2 for partial mode, 3 for full grouped mode and 4 for partial grouped mode
 *
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public class TransferWizardComponent extends DefaultJsamsWizardComponent {

    private int source;
    private int destination;
    private int mode;
    
    /**
     * Constructor.
     */
    public TransferWizardComponent() {
        super();
    }

    /**
     * @return the source
     */
    public int getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(int source) {
        this.source = source;
    }

    /**
     * @return the destination
     */
    public int getDestination() {
        return destination;
    }

    /**
     * @param destination the destination to set
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * @return the mode
     */
    public int getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(int mode) {
        this.mode = mode;
    }
    
}
