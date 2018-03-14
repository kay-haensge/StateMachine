package de.hd1.statemachine.model.controlstates;

public class StateB extends State {


  @Override
  public String transitFromBtoC() {
    return "StateB + transitFromBtoC() = StateC";
  }

  @Override
  public String transitFromBtoA() {
    return "StateB + transitFromBtoA() = StateA";
  }

}
