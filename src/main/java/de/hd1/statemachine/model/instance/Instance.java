package de.hd1.statemachine.model.instance;

import de.hd1.statemachine.model.controlstates.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Instance Object Holder`
 *
 * @author Kay Haensge
 */
@Component
public class Instance {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  private HashMap<State.InstanceTransitions, HashMap<String, State.InstanceStates>> allowedTransitions;

  /**
   * Current State. Starting with A of {@link State.InstanceStates}
   */
  private State.InstanceStates currentState = State.InstanceStates.A;

  /**
   * The InstanceId
   */
  private String instanceId = UUID.randomUUID().toString();

  /**
   * The InstanceName
   */
  private String instanceName = null;

  /**
   * Named States and its Object Representation
   */
  private HashMap<State.InstanceStates, State> states;

  public Instance() {
    init();
  }

  /**
   * Create states map and allowed transitions map
   */
  private void init() {

    states = new HashMap<State.InstanceStates, State>();
    states.put(State.InstanceStates.A, new StateA());
    states.put(State.InstanceStates.B, new StateB());
    states.put(State.InstanceStates.C, new StateC());
    states.put(State.InstanceStates.D, new StateD());

    allowedTransitions = new HashMap<State.InstanceTransitions, HashMap<String, State.InstanceStates>>();
    allowedTransitions.put(
            State.InstanceTransitions.A_TO_B,
            allowedTransition(State.InstanceStates.A, State.InstanceStates.B));
    allowedTransitions.put(
            State.InstanceTransitions.B_TO_C,
            allowedTransition(State.InstanceStates.B, State.InstanceStates.C));
    allowedTransitions.put(
            State.InstanceTransitions.C_TO_D,
            allowedTransition(State.InstanceStates.C, State.InstanceStates.D));
    allowedTransitions.put(
            State.InstanceTransitions.D_TO_C,
            allowedTransition(State.InstanceStates.D, State.InstanceStates.C));
    allowedTransitions.put(
            State.InstanceTransitions.C_TO_B,
            allowedTransition(State.InstanceStates.C, State.InstanceStates.B));
    allowedTransitions.put(
            State.InstanceTransitions.B_TO_A,
            allowedTransition(State.InstanceStates.B, State.InstanceStates.A));
  }

  private HashMap<String, State.InstanceStates> allowedTransition(
          State.InstanceStates from, State.InstanceStates to) {

    HashMap<String, State.InstanceStates> allowedTransition = new HashMap<String, State.InstanceStates>();
    allowedTransition.put("from", from);
    allowedTransition.put("to", to);

    return allowedTransition;
  }

  public Instance(String instanceName) {
    init();
    this.instanceName = instanceName;
  }

  public Instance(String instanceName, State.InstanceStates initialInstanceState) {
    init();
    this.instanceName = instanceName;
    this.setState(initialInstanceState);
  }

  public Instance(String instanxceId, String instanceName, State.InstanceStates initialInstanceState) {
    init();
    this.instanceId = instanceId;
    this.instanceName = instanceName;
    this.setState(initialInstanceState);
  }

  /**
   * Perform the Transition with the given Transition Integer,
   *
   * @param transition The Transition-Identifier.
   */
  public void doTransition(State.InstanceTransitions transition) {

    // The from/to states
    State.InstanceStates fromState;
    State.InstanceStates toState;

    HashMap<String, State.InstanceStates> transitionEntry = allowedTransitions.get(transition);
    if (null != transitionEntry) {
      fromState = transitionEntry.get("from");
      toState = transitionEntry.get("to");
      logger.log(Level.INFO, this.getInstanceName() + ": (perform " + transition + "), fromState=" + fromState + ", toState=" + toState);
    } else {
      return;
    }

    // Call the transition at the current state
    String transactionMesssage = callTransition(transition).toString();

    logger.log(Level.INFO, this.getInstanceName() + ": transactionMessage=" + transactionMesssage);

    // if everything went through, the new state will be set, otherwise, it hopefully remained at the starting state.
    this.setState(toState);

    logger.log(Level.INFO, this.getInstanceName() + ": getState()=" + this.getState());
  }

  /**
   * Perform the transition by calling the State's element method.
   */
  private Object callTransition(State.InstanceTransitions transition) {
    switch (transition) {
      case A_TO_B:
        return states.get(currentState).transitFromAtoB();
      case B_TO_A:
        return states.get(currentState).transitFromBtoA();
      case B_TO_C:
        return states.get(currentState).transitFromBtoC();
      case C_TO_B:
        return states.get(currentState).transitFromCtoB();
      case C_TO_D:
        return states.get(currentState).transitFromCtoD();
      case D_TO_C:
        return states.get(currentState).transitFromDtoC();

      default:
        return null;
    }
  }

  /**
   * Get the Instance's Identifier.
   *
   * @return The Instance Id.
   */

  public String getInstanceId() {
    return instanceId;
  }

  /**
   * Return the Instance Name
   *
   * @return the given InstanceName
   */
  public String getInstanceName() {
    return instanceName;
  }

  /**
   * Gives you the current state of the Instance. If anything is weired, unknown will be returned.
   *
   * @return The Current state of {@link State.InstanceStates} will be returned. If unclear, it will be UNKNOWN
   */
  public State.InstanceStates getState() {
    return currentState;
  }

  /**
   * Set the instance's Identifier
   */
  public void setInstanceId(String instanceId) {
    this.instanceId = instanceId;
  }

  /**
   * Put a new instance name.
   *
   * @param instanceName the new Instance name.
   */
  public void setInstanceName(String instanceName) {
    this.instanceName = instanceName;
  }


  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Instance ["
            + (currentState != null ? "currentState=" + currentState + ", " : "")
            + (instanceId != null ? "instanceId=" + instanceId + ", " : "")
            + (instanceName != null ? "instanceName=" + instanceName : "")
            + "]";
  }

  /**
   * Set the current state.
   *
   * @param state The state of the instance.
   */
  private void setState(State.InstanceStates state) {
    this.currentState = state;
  }
}
