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

public class ENonDeterministicAutomata extends NonDeterministicAutomata {
    private HashMap<Integer, FiniteSet<Integer>> eTransitionFunction;
    private HashMap<Integer, FiniteSet<Integer>> closure;

    //Constructor
    public ENonDeterministicAutomata(FiniteSet<Integer> initState,
                              FiniteSet<Integer> finalState,
                              FiniteSet<Integer> stateSet,
                              FiniteSet<Character> symbolSet,
                              TransitionFunction<Integer, Character> transitionFunction,
                              HashMap<Integer, FiniteSet<Integer>> eTransitionFunction) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction);
        this.eTransitionFunction = eTransitionFunction;
        this.closure = getClosure();
    }

    //Used to update closure field
    public HashMap<Integer, FiniteSet<Integer>> getClosure(){
        DFS dfsObj = new DFS(stateSet, eTransitionFunction);
        dfsObj.run();
        return dfsObj.getClosure();
    }

    //Get the closure of a set of states, which is the union of the closures of each constituent state
    public FiniteSet<Integer> getClosure(FiniteSet<Integer> states){
        FiniteSet<Integer> union = new FiniteSet<>();
        for (int state: states){
            union.addAll(closure.get(state));
        }return union;
    }

    //Override the parent's next and accept methods to use closure as the current state
    @Override
    public void next(char symbol) {
        currentState = getClosure(currentState);
        super.next(symbol);
    }

    @Override
    public boolean accept(String string, boolean debug) {
        string = string.trim();
        boolean noException = next(string, debug);
        if (!noException){
            return false;
        }
        boolean accept = isAcceptedState(getClosure(currentState));
        initialise();
        return accept;
    }

    public static void main(String[] args) {
        FiniteSet<Character> symbolSet = FiniteSet.of('+', '-', '0', '1','2','3','4','5','6','7','8','9');
        FiniteSet<Integer> stateSet = FiniteSet.of(0,1,2,3);
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(3);
        TransitionFunction<Integer, Character> transitionFunction =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(0,'+'), FiniteSet.of(1)),
                        Map.entry(Pair.of(0,'-'), FiniteSet.of(1)),
                        Map.entry(Pair.of(1,'0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(1,'9'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'0'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'1'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'2'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'3'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'4'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'5'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'6'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'7'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'8'), FiniteSet.of(2)),
                        Map.entry(Pair.of(2,'9'), FiniteSet.of(2))
                );
        HashMap<Integer, FiniteSet<Integer>> eTransitionFunction = new HashMap<>(Map.of(
                0, FiniteSet.of(1),
                2, FiniteSet.of(3)
        ));

        ENonDeterministicAutomata intRec = new ENonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction, eTransitionFunction);

        System.out.println(intRec.accept("+009", false));
        System.out.println(intRec.accept("009", false));
        System.out.println(intRec.accept("-009", false));
        System.out.println(intRec.accept("0 0 9 ", false));
        System.out.println(intRec.accept("a009", false));
    }
}
