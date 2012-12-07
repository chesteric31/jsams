package be.jsams.server.service.transfer;

/**
 * Marking interface for transfer of estimate to command.
 * <p>
 * This interface is necessary to avoid (while application starting) this sort
 * of issue:
 * <p>
 * Caused by: java.lang.IllegalStateException: Cannot convert value of type
 * [$Proxy28 implementing be.jsams.server.service.transfer.TransferService, org
 * .springframework.aop.SpringProxy,org.springframework.aop.framework.Advised]
 * to required type
 * [be.jsams.server.service.transfer.EstimateCommandTransferService] for
 * property 'estimateCommandTransferService': no matching editors or conversion
 * strategy found
 * 
 * @author chesteric31
 * @version $Revision$ $Date::                  $ $Author$
 */
public interface EstimateCommandTransferService extends TransferService {

}
