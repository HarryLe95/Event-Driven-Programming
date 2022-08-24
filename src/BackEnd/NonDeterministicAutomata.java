/*
 * Implements the Non-Deterministic Finite State Automata.
 *
 * Unlike Deterministic Finite State Automata, the Non-Deterministic version can have a system be at
 * more than one states at any point in time. Inherits from the FiniteStateMachine class and has the same
 * interface as the deterministic version.
 *
 * The public `accept` method receives as an input a string or a character and determines
 * whether the given string is accepted by the DFA, based on the 5 tuple provided at construction. White
 * space characters are ignored, but invalid symbols give a false whenever first encountered in the string.
 * The `accept` method uses the internal `next` method to output the final state given the input string
 * and use the base class' method `isAcceptedState` to validate the final state.
 *
 * TODO - Write a method to convert an NFA to a DFA
 */


package src.BackEnd;

import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

import java.util.Scanner;

public class NonDeterministicAutomata extends FiniteStateMachine {

    protected FiniteSet<Integer> currentState;

    public FiniteSet<Integer> getCurrentState(){
        return currentState;
    }

    //Constructor
    NonDeterministicAutomata(){}
    public NonDeterministicAutomata(FiniteSet<Integer> initState,
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
        currentState = initState;
        if (debug){
            System.out.println(isAcceptedState(currentState));
        }
    }

    //Get next symbol method
    public void next(char symbol) {
        if (isValidSymbol(symbol)) {
            FiniteSet<Integer> nextState = new FiniteSet<>();
            for (int item : currentState) {
                Pair<Integer, Character> key = Pair.of(item, symbol);
                FiniteSet<Integer> temp = transitionFunction.get(key);
                if (temp != null) {
                    nextState.addAll(temp);
                }
            }
            currentState = nextState;
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
        if (!debug){
            initialise();
        }
        for (char symbol : string.toCharArray()){
            next(symbol);
        }
        if (!debug){
            System.out.println(isAcceptedState(currentState));
        }
    }

}
