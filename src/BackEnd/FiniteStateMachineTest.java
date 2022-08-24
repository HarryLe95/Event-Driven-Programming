package src.BackEnd;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import src.utils.FiniteSet;
import src.utils.TransitionFunction;

import java.util.InputMismatchException;

public class FiniteStateMachineTest {
    @Test(expected = InputMismatchException.class)
    public void testIncorrectState1(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
    }

    @Test(expected = InputMismatchException.class)
    public void testIncorrectState2(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                3, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
    }

    @Test(expected = InputMismatchException.class)
    public void testIncorrectState3(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(5)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
    }

    @Test(expected = InputMismatchException.class)
    public void testIncorrectSymbol1(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '5', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
    }

    @Test(expected = InputMismatchException.class)
    public void testIncorrectSymbol2(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, 'c', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
    }

    @Test(expected = InputMismatchException.class)
    public void testIncorrectSymbol3(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '-', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
    }

    @Test()
    public void testAcceptState1(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        assertTrue(machine.isAcceptedState(2));
    }

    @Test()
    public void testAcceptState2(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        assertFalse(machine.isAcceptedState(1));
    }

    @Test()
    public void testAcceptState3(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        assertFalse(machine.isAcceptedState(5));
    }

    @Test()
    public void testAcceptStates1(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        FiniteSet<Integer> finalSet = FiniteSet.of(1,2);
        assertTrue(machine.isAcceptedState(finalSet));
    }

    @Test()
    public void testAcceptStates2(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        FiniteSet<Integer> finalSet = FiniteSet.of(0, 1,2);
        assertTrue(machine.isAcceptedState(finalSet));
    }

    @Test()
    public void testAcceptStates3(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        FiniteSet<Integer> finalSet = FiniteSet.of(2,0);
        assertTrue(machine.isAcceptedState(finalSet));
    }

    @Test()
    public void testAcceptStates4(){
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,transitionFunction,false);
        FiniteSet<Integer> finalSet = FiniteSet.of(1,0);
        assertFalse(machine.isAcceptedState(finalSet));
    }
}
