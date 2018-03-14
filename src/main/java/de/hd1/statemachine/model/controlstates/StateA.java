package de.hd1.statemachine.model.controlstates;

public class StateA extends State {

  @Override
  public String transitFromAtoB() {
    return "StateA + transitFromAtoB() = StateB";
  }

}