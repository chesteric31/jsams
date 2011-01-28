package be.jsams.client.validator;

import be.jsams.server.model.Agent;

import com.jgoodies.validation.ValidationResult;
import com.jgoodies.validation.Validator;
import com.jgoodies.validation.util.PropertyValidationSupport;

/**
 * {@link Validator} for Agent object.
 *
 * @author chesteric31
 * @version $Rev$ $Date::                  $ $Author$
 */
public class AgentValidator implements Validator<Agent> {

    /**
     * {@inheritDoc}
     */
    public ValidationResult validate(Agent agent) {
        PropertyValidationSupport support = new PropertyValidationSupport(agent, "");

        return support.getResult();
    }

}
