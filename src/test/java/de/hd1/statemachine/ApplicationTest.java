package de.hd1.statemachine;

import de.hd1.statemachine.model.controlstates.State;
import de.hd1.statemachine.model.instance.Instance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {

  @Test
  public void testConnectedInstance() {

    Instance instance = new Instance("Instance A");
    State.InstanceTransitions[] msgs = {
            State.InstanceTransitions.A_TO_B,
            State.InstanceTransitions.B_TO_C,
            State.InstanceTransitions.C_TO_D
    };

    updateState(instance, msgs);

    assertTrue(instance.getState().equals(State.InstanceStates.D));
  }

  @Test
  public void fullTransitionInstance() {

    Instance instance = new Instance("Instance B");

    State.InstanceTransitions[] msgs = {
            State.InstanceTransitions.A_TO_B,
            State.InstanceTransitions.B_TO_C,
            State.InstanceTransitions.C_TO_D,
            State.InstanceTransitions.D_TO_C,
            State.InstanceTransitions.C_TO_B,
            State.InstanceTransitions.B_TO_A
    };

    updateState(instance, msgs);

    assertTrue(instance.getState().equals(State.InstanceStates.A));
  }

  @Test
  public void illegalStateTest() {

    Instance instance = new Instance("Instance C");
    State.InstanceTransitions[] msgs = {
            State.InstanceTransitions.A_TO_B,
            State.InstanceTransitions.B_TO_C,
            State.InstanceTransitions.C_TO_D,
            State.InstanceTransitions.C_TO_B,
            State.InstanceTransitions.B_TO_A
    };
    try {
      updateState(instance, msgs);
      assertTrue(instance.getState().equals(State.InstanceStates.D));
    } catch (Exception e) {
      assertTrue(e instanceof IllegalStateException);
      System.out.println("Testing for " + e + " was successful.");
    }

    assertTrue(instance.getState().equals(State.InstanceStates.D));

  }

  private void updateState(Instance instance, State.InstanceTransitions[] msgs) {
    for (State.InstanceTransitions msg : msgs) {
      System.out.println("msg=" + msg);
      instance.doTransition(msg);
    }
  }

}
