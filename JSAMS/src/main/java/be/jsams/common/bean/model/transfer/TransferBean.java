package be.jsams.common.bean.model.transfer;

import java.util.List;
import java.util.Map;

import be.jsams.common.bean.model.AbstractIdentityBean;
import be.jsams.common.bean.model.sale.AbstractDocumentBean;
import be.jsams.common.bean.model.sale.detail.AbstractDetailBean;
import be.jsams.common.bean.model.sale.detail.BillDetailBean;
import be.jsams.common.bean.model.sale.detail.CommandDetailBean;
import be.jsams.common.bean.model.sale.detail.DeliveryOrderDetailBean;
import be.jsams.common.bean.model.sale.detail.EstimateDetailBean;
import be.jsams.common.bean.view.transfer.TransferBeanView;
import be.jsams.server.model.AbstractIdentity;

/**
 * {@link AbstractIdentityBean} for a transfer wrapper bean. <p>
 * The wizard stores the source, destination and transfer mode selections. <p>
 * <b>source: </b>
 * <ol>
 * <li>for estimate,</li>
 * <li>for command,</li>
 * <li>for delivery order,</li>
 * <li>for bill</li>
 * </ol>
 * <b>destination: </b>
 * <ol>
 * <li>for command,</li>
 * <li>for delivery order,</li>
 * <li>for bill,</li>
 * <li>for credit note</li>
 * </ol>
 * <b>mode: </b>
 * <ol>
 * <li>for full mode,</li>
 * <li>for partial mode,</li>
 * <li>for full grouped mode,</li>
 * <li>for partial grouped mode</li>
 * </ol>
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

    private List<? extends AbstractDocumentBean<?, ?>> documents;
    private List<? extends AbstractDetailBean<?, ?, ?>> selectableDetails;

    private Map<Long, List<EstimateDetailBean>> estimateDetails;
    private Map<Long, List<CommandDetailBean>> commandDetails;
    private Map<Long, List<DeliveryOrderDetailBean>> deliveryOrderDetails;
    private Map<Long, List<BillDetailBean>> billDetails;

    public static final String TRANSFER_MODE_PROPERTY = "transferMode";
    public static final String SOURCE_TYPE_PROPERTY = "sourceType";
    public static final String DESTINATION_TYPE_PROPERTY = "destinationType";

    /**
     * Constructor.
     */
    public TransferBean() {
        super();
        setView(buildView());
    }

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
        setEstimateDetails(other.getEstimateDetails());
        setCommandDetails(other.getCommandDetails());
        setDeliveryOrderDetails(other.getDeliveryOrderDetails());
        setBillDetails(other.getBillDetails());
        setDocuments(other.getDocuments());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TransferBeanView buildView() {
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
        setEstimateDetails(null);
        setCommandDetails(null);
        setDeliveryOrderDetails(null);
        setBillDetails(null);
        setDocuments(null);
    }

    /**
     * Removes all of the mappings from these maps.
     * The map will be empty after this call returns.
     */
    public void clearDetails() {
        if (billDetails != null) {
            billDetails.clear();
        }
        if (deliveryOrderDetails != null) {
            deliveryOrderDetails.clear();
        }
        if (commandDetails != null) {
            commandDetails.clear();
        }
        if (estimateDetails != null) {
            estimateDetails.clear();
        }
        if (selectableDetails != null) {
            selectableDetails.clear();
        }
    }
    

    /**
     * @return the documents
     */
    public List<? extends AbstractDocumentBean<?, ?>> getDocuments() {
        return documents;
    }

    /**
     * @param documents the documents to set
     */
    public void setDocuments(List<? extends AbstractDocumentBean<?, ?>> documents) {
        this.documents = documents;
    }

    /**
     * @return the selectableDetails
     */
    public List<? extends AbstractDetailBean<?, ?, ?>> getSelectableDetails() {
        return selectableDetails;
    }

    /**
     * @param selectableDetails the selectableDetails to set
     */
    public void setSelectableDetails(List<? extends AbstractDetailBean<?, ?, ?>> selectableDetails) {
        this.selectableDetails = selectableDetails;
    }

    /**
     * @return the estimateDetails
     */
    public Map<Long, List<EstimateDetailBean>> getEstimateDetails() {
        return estimateDetails;
    }

    /**
     * @param estimateDetails the estimateDetails to set
     */
    public void setEstimateDetails(Map<Long, List<EstimateDetailBean>> estimateDetails) {
        this.estimateDetails = estimateDetails;
    }

    /**
     * @return the commandDetails
     */
    public Map<Long, List<CommandDetailBean>> getCommandDetails() {
        return commandDetails;
    }

    /**
     * @param commandDetails the commandDetails to set
     */
    public void setCommandDetails(Map<Long, List<CommandDetailBean>> commandDetails) {
        this.commandDetails = commandDetails;
    }

    /**
     * @return the deliveryOrderDetails
     */
    public Map<Long, List<DeliveryOrderDetailBean>> getDeliveryOrderDetails() {
        return deliveryOrderDetails;
    }

    /**
     * @param deliveryOrderDetails the deliveryOrderDetails to set
     */
    public void setDeliveryOrderDetails(Map<Long, List<DeliveryOrderDetailBean>> deliveryOrderDetails) {
        this.deliveryOrderDetails = deliveryOrderDetails;
    }

    /**
     * @return the billDetails
     */
    public Map<Long, List<BillDetailBean>> getBillDetails() {
        return billDetails;
    }

    /**
     * @param billDetails the billDetails to set
     */
    public void setBillDetails(Map<Long, List<BillDetailBean>> billDetails) {
        this.billDetails = billDetails;
    }

}
