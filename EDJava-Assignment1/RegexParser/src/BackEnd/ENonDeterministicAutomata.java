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

    ENonDeterministicAutomata(FiniteSet<Integer> initState,
                              FiniteSet<Integer> finalState,
                              FiniteSet<Integer> stateSet,
                              FiniteSet<Character> symbolSet,
                              TransitionFunction<Integer, Character> transitionFunction,
                              HashMap<Integer, FiniteSet<Integer>> eTransitionFunction) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction);
        this.eTransitionFunction = eTransitionFunction;
        this.closure = getClosure();
    }

    public HashMap<Integer, FiniteSet<Integer>> getClosure(){
        DFS dfsObj = new DFS(stateSet, eTransitionFunction);
        dfsObj.run();
        return dfsObj.getClosure();
    }

    public FiniteSet<Integer> getClosure(FiniteSet<Integer> states){
        FiniteSet<Integer> union = new FiniteSet<>();
        for (int state: states){
            union.addAll(closure.get(state));
        }return union;
    }

    @Override
    public void next(char symbol) {
        currentState = getClosure(currentState);
        super.next(symbol);
    }

    @Override
    public boolean accept(String string, boolean debug) {
        next(string, debug);
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

        System.out.println(intRec.accept("+009", true));


    }
}
