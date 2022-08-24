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

import java.util.Scanner;

public class DeterministicAutomata extends src.BackEnd.FiniteStateMachine {
    private int currentState;

    public int getCurrentState(){
        return currentState;
    }

    //Constructor
    public DeterministicAutomata(FiniteSet<Integer> initState,
                                 FiniteSet<Integer> finalState,
                                 FiniteSet<Integer> stateSet,
                                 FiniteSet<Character> symbolSet,
                                 TransitionFunction<Integer, Character> transitionFunction,
                                 boolean debug) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction, debug);
        initialise();
    }

    //Initialise method to reset state
    public void initialise() {
        currentState = initState.iterator().next();
        if (debug){
            System.out.println(isAcceptedState(currentState));
        }
    }

    //Get next symbol method
    protected void next(char symbol) {
        if (isValidSymbol(symbol)) {
            Pair<Integer, Character> key = Pair.of(currentState, symbol);
            FiniteSet<Integer> nextState = transitionFunction.get(key);
            currentState = nextState.iterator().next();
            if (debug){
                System.out.println(isAcceptedState(currentState));
            }
        }
        else if (isEnter(symbol)){
            initialise();
        }else{
            throw new RuntimeException("Invalid symbol encountered");
        }
    }

    //Accept string method
    public void accept(String string) {
        try{
        if (!debug){
            initialise();
        }
        for (char symbol : string.toCharArray()){
            next(symbol);
        }
        if (!debug){
            System.out.println(isAcceptedState(currentState));
        }} catch (Exception e){
            System.out.println(false);
        }
    }
}
