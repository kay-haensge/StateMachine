package de.hd1.statemachine.model.controlstates;

public abstract class State {

  public Object transitFromAtoB() {
    throw new IllegalStateException();
  }

  public Object transitFromBtoC() {
    throw new IllegalStateException();
  }

  public Object transitFromCtoD() {
    throw new IllegalStateException();
  }

  public Object transitFromDtoC() {
    throw new IllegalStateException();
  }

  public Object transitFromCtoB() {
    throw new IllegalStateException();
  }

  public Object transitFromBtoA() {
    throw new IllegalStateException();
  }

  public enum InstanceStates {
    A,
    B,
    C,
    D,
    UNKNOWN
  }

  public enum InstanceTransitions {
    A_TO_B,
    B_TO_C,
    C_TO_D,
    D_TO_C,
    C_TO_B,
    B_TO_A
  }
}
