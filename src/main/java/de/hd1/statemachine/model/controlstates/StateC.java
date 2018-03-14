package de.hd1.statemachine.model.controlstates;

public class StateC extends State {

  @Override
  public String transitFromCtoD() {
    return "StateC + transitFromCtoD() = StateD";
  }

  @Override
  public String transitFromCtoB() {
    return "StateC + transitFromCtoB() = StateB";
  }
}
