/*
 * Implements the Deterministic Finite State Automata.
 *
 * The public `accept` method receives as an input a string or a character and determines
 * whether the given string is accepted by the DFA, based on the 5 tuple provided at construction. White
 * space characters are ignored, but invalid symbols give a false whenever first encountered in the string.
 * The `accept` method uses the internal `next` method to output the final state given the input string
 * and use the base class' method `isAcceptedState` to validate the final state.
 *
 */

package src.BackEnd;

import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

public class DeterministicAutomata extends src.BackEnd.FiniteStateMachine {
    private int currentState;

    //Constructor
    public DeterministicAutomata(FiniteSet<Integer> initState,
                                 FiniteSet<Integer> finalState,
                                 FiniteSet<Integer> stateSet,
                                 FiniteSet<Character> symbolSet,
                                 TransitionFunction<Integer, Character> transitionFunction) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction);
        initialise();
    }

    //Initialise method to reset state
    public void initialise() {
        currentState = initState.iterator().next();
    }

    //Get next symbol method
    protected void next(char symbol) {
        if (!isSpace(symbol)) {
            Pair<Integer, Character> key = Pair.of(currentState, symbol);
            FiniteSet<Integer> nextState = transitionFunction.get(key);
            currentState = nextState.iterator().next();
        }
    }

    //Get next string method - return true is no exception occurs, false if an invalid symbol is provided
    protected boolean next(String string, boolean debug) {
        if (debug) {
            System.out.println("Current state: " + currentState);
        }
        for (char symbol : string.toCharArray()) {
            if (!isValidSymbol(symbol)) {
                return false;
            }
            next(symbol);
            if (debug) {
                System.out.println("Symbol: " + symbol + ", State: " + currentState);
            }
        }
        return true;
    }

    public boolean accept(String string, boolean debug) {
        boolean noException = next(string, debug);
        if (!noException) {
            return false;
        }
        boolean accept = isAcceptedState(currentState);
        initialise();
        return accept;
    }

    public static void main(String[] args) {
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
        DeterministicAutomata zeroOne = new DeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction);
        System.out.println(zeroOne.accept("0101", true));
        System.out.println(zeroOne.accept("  01ba01 ", true));
        System.out.println(zeroOne.accept("11 0101 ", true));
    }
}
