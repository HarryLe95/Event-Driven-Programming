/*
 * Implements the Epsilon-Non-Deterministic Finite State Automata.
 *
 * Unlike Deterministic Finite State Automata, the Epsilon-Non-Deterministic version can have a system be at
 * more than one states at any point in time. Unlike the Non-Deterministic Finite State Automata, the Epsilon version
 * has an additional eTransitionFunction, which describes the epsilon transitions possible.
 *
 * The closure of a state is the set of state reachable under epsilon transitions, including itself. The closure is
 * found using a variant of Depth First Search, implemented in the utils submodule.
 *
 * The public `accept` method receives as an input a string or a character and determines
 * whether the given string is accepted by the DFA, based on the 5 tuple provided at construction. White
 * space characters are ignored, but invalid symbols give a false whenever first encountered in the string.
 * The `accept` method uses the internal `next` method to output the final state given the input string
 * and use the base class' method `isAcceptedState` to validate the final state.
 *
 * TODO - Write a method to convert an eNFA to an NFA
 */

package src.BackEnd;

import src.utils.DFS;
import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ENonDeterministicAutomata extends NonDeterministicAutomata {
    private HashMap<Integer, FiniteSet<Integer>> eTransitionFunction;
    private HashMap<Integer, FiniteSet<Integer>> closure;

    //Constructor
    ENonDeterministicAutomata(){}
    public ENonDeterministicAutomata(FiniteSet<Integer> initState,
                                     FiniteSet<Integer> finalState,
                                     FiniteSet<Integer> stateSet,
                                     FiniteSet<Character> symbolSet,
                                     TransitionFunction<Integer, Character> transitionFunction,
                                     HashMap<Integer, FiniteSet<Integer>> eTransitionFunction,
                                     boolean debug) {
        this.initState = initState;
        this.finalState = finalState;
        this.stateSet = stateSet;
        this.symbolSet = symbolSet;
        this.transitionFunction= transitionFunction;
        this.debug = debug;
        validateState(this.initState);
        validateState(this.finalState);
        validateTransitionFunction(this.transitionFunction);
        this.eTransitionFunction = eTransitionFunction;
        this.closure = getClosure();
        initialise();
    }

    //Used to update closure field
    public HashMap<Integer, FiniteSet<Integer>> getClosure() {
        DFS dfsObj = new DFS(stateSet, eTransitionFunction);
        dfsObj.run();
        return dfsObj.getClosure();
    }

    //Get the closure of a set of states, which is the union of the closures of each constituent state
    public FiniteSet<Integer> getClosure(FiniteSet<Integer> states) {
        FiniteSet<Integer> union = new FiniteSet<>();
        for (int state : states) {
            union.addAll(closure.get(state));
        }
        return union;
    }

    @Override
    public void initialise() {
        currentState = getClosure(initState);
        if (debug){
            System.out.println(isAcceptedState(currentState));
        }
    }
    //Override the parent's next and accept methods to use closure as the current state
    @Override
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
            currentState = getClosure(nextState);
            if (debug){
                System.out.println(isAcceptedState(currentState));
            }
        }
        else if (isEnter(symbol)){
            initialise();
        }else{
            System.out.println(symbol+" "+(int)symbol);
            throw new RuntimeException("Invalid symbol encountered");
        }
    }

    @Override
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
