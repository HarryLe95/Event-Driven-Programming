package src.BackEnd;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

import java.util.HashMap;
import java.util.Map;

public class ENonDeterministicAutomata_Test {
    @Test
    public void testIntRec1(){
        FiniteSet<Character> symbolSet = FiniteSet.of('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(0, '+'), FiniteSet.of(1)),
                        Map.entry(Pair.of(0, '-'), FiniteSet.of(1)),
                        Map.entry(Pair.of(1, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '9'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '9'), FiniteSet.of(2))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1),
                2, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata intRec = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        intRec.accept("+123");
        assertTrue(intRec.isAcceptedState(intRec.getCurrentState()));
    }

    @Test
    public void testIntRec2(){
        FiniteSet<Character> symbolSet = FiniteSet.of('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(0, '+'), FiniteSet.of(1)),
                        Map.entry(Pair.of(0, '-'), FiniteSet.of(1)),
                        Map.entry(Pair.of(1, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '9'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '9'), FiniteSet.of(2))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1),
                2, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata intRec = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        intRec.accept("-123");
        assertTrue(intRec.isAcceptedState(intRec.getCurrentState()));
    }

    @Test
    public void testIntRec3(){
        FiniteSet<Character> symbolSet = FiniteSet.of('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(0, '+'), FiniteSet.of(1)),
                        Map.entry(Pair.of(0, '-'), FiniteSet.of(1)),
                        Map.entry(Pair.of(1, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '9'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '9'), FiniteSet.of(2))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1),
                2, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata intRec = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        intRec.accept("123");
        assertTrue(intRec.isAcceptedState(intRec.getCurrentState()));
    }

    @Test
    public void testIntRec4(){
        FiniteSet<Character> symbolSet = FiniteSet.of('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(0, '+'), FiniteSet.of(1)),
                        Map.entry(Pair.of(0, '-'), FiniteSet.of(1)),
                        Map.entry(Pair.of(1, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '9'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '9'), FiniteSet.of(2))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1),
                2, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata intRec = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        intRec.accept("+12+433");
        assertFalse(intRec.isAcceptedState(intRec.getCurrentState()));
    }

    @Test
    public void testIntRec5(){
        FiniteSet<Character> symbolSet = FiniteSet.of('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(0, '+'), FiniteSet.of(1)),
                        Map.entry(Pair.of(0, '-'), FiniteSet.of(1)),
                        Map.entry(Pair.of(1, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1, '9'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2, '9'), FiniteSet.of(2))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1),
                2, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata intRec = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        intRec.accept("+1-23");
        assertFalse(intRec.isAcceptedState(intRec.getCurrentState()));
    }

    @Test
    public void testAorB1(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(1, 'b'), FiniteSet.of(2)),
                        Map.entry(Pair.of(4, 'a'), FiniteSet.of(5))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,4),
                2, FiniteSet.of(3),
                5, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata AorB = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        AorB.accept("a");
        assertTrue(AorB.isAcceptedState(AorB.getCurrentState()));
    }

    @Test
    public void testAorB2(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(1, 'b'), FiniteSet.of(2)),
                        Map.entry(Pair.of(4, 'a'), FiniteSet.of(5))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,4),
                2, FiniteSet.of(3),
                5, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata AorB = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        AorB.accept("b");
        assertTrue(AorB.isAcceptedState(AorB.getCurrentState()));
    }

    @Test
    public void testAorB3(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(1, 'b'), FiniteSet.of(2)),
                        Map.entry(Pair.of(4, 'a'), FiniteSet.of(5))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,4),
                2, FiniteSet.of(3),
                5, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata AorB = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        AorB.accept("ab");
        assertFalse(AorB.isAcceptedState(AorB.getCurrentState()));
    }

    @Test()
    public void testAorB4(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(1, 'b'), FiniteSet.of(2)),
                        Map.entry(Pair.of(4, 'a'), FiniteSet.of(5))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,4),
                2, FiniteSet.of(3),
                5, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata AorB = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        AorB.accept("abc");
        assertFalse(AorB.isAcceptedState(AorB.getCurrentState()));
    }


    @Test
    public void testABZeroOrCOne1(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("ab");
        assertTrue(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne2(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("ababab");
        assertTrue(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne3(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("");
        assertTrue(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne4(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("ccc");
        assertTrue(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne5(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("c");
        assertTrue(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne6(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("ca");
        assertFalse(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne7(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("ababa");
        assertFalse(machine.isAcceptedState(machine.getCurrentState()));
    }

    @Test
    public void testABZeroOrCOne8(){
        FiniteSet<Character> symbolSet = FiniteSet.of('a','b','c');
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(6);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));

        ENonDeterministicAutomata machine = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction, false);
        machine.accept("cca");
        assertFalse(machine.isAcceptedState(machine.getCurrentState()));
    }
}
