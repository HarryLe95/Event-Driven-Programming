/*
 * Serve as the ABC (Python terminology) for finite state machine
 *
 * Provide methods for input validation and final state validation.
 */
package src.BackEnd;

import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

import java.util.InputMismatchException;
import java.util.Map;

public class FiniteStateMachine {
    protected FiniteSet<Integer> initState;
    protected FiniteSet<Integer> finalState;
    protected FiniteSet<Integer> stateSet;
    protected FiniteSet<Character> symbolSet;
    protected TransitionFunction<Integer, Character> transitionFunction;

    FiniteStateMachine(){}
    //Constructor
    public FiniteStateMachine(FiniteSet<Integer> initState,
                       FiniteSet<Integer> finalState,
                       FiniteSet<Integer> stateSet,
                       FiniteSet<Character> symbolSet,
                       TransitionFunction<Integer, Character> transitionFunction) {
        this.initState = initState;
        this.finalState = finalState;
        this.stateSet = stateSet;
        this.symbolSet = symbolSet;
        this.transitionFunction = transitionFunction;
        validateState(this.initState);
        validateState(this.finalState);
        validateTransitionFunction(this.transitionFunction);
    }

    public FiniteSet<Integer> getInitState() {
        return initState;
    }

    public void setInitState(FiniteSet<Integer> initState) {
        this.initState = initState;
    }

    public FiniteSet<Integer> getFinalState() {
        return finalState;
    }

    public void setFinalState(FiniteSet<Integer> finalState) {
        this.finalState = finalState;
    }

    public FiniteSet<Integer> getStateSet() {
        return stateSet;
    }

    public void setStateSet(FiniteSet<Integer> stateSet) {
        this.stateSet = stateSet;
    }

    public FiniteSet<Character> getSymbolSet() {
        return symbolSet;
    }

    public void setSymbolSet(FiniteSet<Character> symbolSet) {
        this.symbolSet = symbolSet;
    }

    public TransitionFunction<Integer, Character> getTransitionFunction() {
        return transitionFunction;
    }

    public void setTransitionFunction(TransitionFunction<Integer, Character> transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    //Check if parameter state is a valid state
    public boolean isValidState(int state) {
        return stateSet.contains(state);
    }

    //Check if parameter symbol is a valid symbol
    public boolean isValidSymbol(Character symbol) {
        return symbolSet.contains(symbol);
    }

    //Validate states and transition function
    public void validateState(int state) {
        if (!isValidState(state)) {
            String exceptionMessage = String.format("Input state %d does not belong to FSM state set", state);
            throw new InputMismatchException(exceptionMessage);
        }
    }

    public void validateState(FiniteSet<Integer> states) {
        for (int state : states) {
            validateState(state);
        }
    }

    public void validateSymbol(char symbol) {
        if (!isValidSymbol(symbol)) {
            String exceptionMessage = String.format("Input symbol %c does not belong to FSM symbol set", symbol);
            throw new InputMismatchException(exceptionMessage);
        }
    }

    public void validateTransitionFunction(TransitionFunction<Integer, Character> f) {
        for (Map.Entry<Pair<Integer, Character>, FiniteSet<Integer>> item : f.entrySet()) {
            Pair<Integer, Character> key = item.getKey();
            int state = key.first;
            char symbol = key.second;
            FiniteSet<Integer> states = item.getValue();
            validateState(state);
            validateSymbol(symbol);
            validateState(states);
        }
    }

    //Check accepting final state
    public boolean isAcceptedState(FiniteSet<Integer> states) {
        FiniteSet<Integer> intersection = finalState.getIntersection(states);
        return !intersection.isEmpty();
    }

    public boolean isAcceptedState(int state){
        return finalState.contains(state);
    }
}