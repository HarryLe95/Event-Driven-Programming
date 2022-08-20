import java.util.HashMap;

public class ENonDeterministicAutomata extends NonDeterministicAutomata {
    private HashMap<Integer, FiniteSet<Integer>> eTransitionFunction;

    ENonDeterministicAutomata() {
    }

    ENonDeterministicAutomata(FiniteSet<Integer> initState,
                              FiniteSet<Integer> finalState,
                              FiniteSet<Integer> stateSet,
                              FiniteSet<Character> symbolSet,
                              TransitionFunction<Integer, Character> transitionFunction,
                              HashMap<Integer, FiniteSet<Integer>> eTransitionFunction) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction);
        this.eTransitionFunction = eTransitionFunction;
        initialise();
    }

    public static HashMap<Integer, FiniteSet<Integer>> getEClosure(FiniteSet<Integer> stateSet,
                                                                   HashMap<Integer, FiniteSet<Integer>> eTransitionFunction) {
        HashMap<Integer, FiniteSet<Integer>> connection = new HashMap<>();
        return connection;
    }
}
