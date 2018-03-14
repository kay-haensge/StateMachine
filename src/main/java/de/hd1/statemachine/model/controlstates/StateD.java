package de.hd1.statemachine.model.controlstates;

public class StateD extends State {

  @Override
  public String transitFromDtoC() {
    return "StateD + transitFromDtoC() = StateC";
  }
}
