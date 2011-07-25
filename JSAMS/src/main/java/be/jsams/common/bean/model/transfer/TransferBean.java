package be.jsams.common.bean.model.transfer;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.view.transfer.TransferBeanView;
import be.jsams.server.model.AbstractIdentity;

/**
 * {@link AbstractIdentityBean} for a transfer wrapper bean.
 * 
 * Kind of memory of the wizard to store the source, destination and transfer mode selections.
 * source: 1 for estimate, 2 for command, 3 for delivery order and 4 for bill
 * destination: 1 for command, 2 for delivery order, 3 for bill and 4 for credit note
 * mode: 1 for full mode, 2 for partial mode, 3 for full grouped mode and 4 for partial grouped mode
 *
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class TransferBean extends AbstractIdentityBean<AbstractIdentity, TransferBeanView> {

    /**
     * Serial Version UID
     */
    private static final long serialVersionUID = 7759305766595246389L;

    private int transferMode;
    private int sourceType;
    private int destinationType;

    public static final String TRANSFER_MODE_PROPERTY = "transferMode";
    public static final String SOURCE_TYPE_PROPERTY = "sourceType";
    public static final String DESTINATION_TYPE_PROPERTY = "destinationType";
    
    /**
     * @return the transferMode
     */
    public int getTransferMode() {
        return transferMode;
    }

    /**
     * @param transferMode the transferMode to set
     */
    public void setTransferMode(int transferMode) {
        int oldValue = this.transferMode;
        this.transferMode = transferMode;
        firePropertyChange(TRANSFER_MODE_PROPERTY, oldValue, this.transferMode);
        System.out.println("transfer is: " + this.transferMode);
    }

    /**
     * @return the sourceType
     */
    public int getSourceType() {
        return sourceType;
    }

    /**
     * @param sourceType the sourceType to set
     */
    public void setSourceType(int sourceType) {
        int oldValue = this.sourceType;
        this.sourceType = sourceType;
        firePropertyChange(SOURCE_TYPE_PROPERTY, oldValue, this.sourceType);
        System.out.println("source is: " + this.sourceType);
    }

    /**
     * @return the destinationType
     */
    public int getDestinationType() {
        return destinationType;
    }

    /**
     * @param destinationType the destinationType to set
     */
    public void setDestinationType(int destinationType) {
        int oldValue = this.destinationType;
        this.destinationType = destinationType;
        firePropertyChange(DESTINATION_TYPE_PROPERTY, oldValue, this.destinationType);
        System.out.println("destination is: " + this.destinationType);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void refresh(AbstractIdentityBean<?, ?> bean) {
        TransferBean other = (TransferBean) bean;
        setTransferMode(other.getTransferMode());
        setSourceType(other.getSourceType());
        setDestinationType(other.getDestinationType());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TransferBeanView getView() {
        return new TransferBeanView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        setTransferMode(0);
        setSourceType(0);
        setDestinationType(0);
    }

}
