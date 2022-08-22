package src.BackEnd;

import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

public class DeterministicAutomata extends src.BackEnd.FiniteStateMachine {

    private int currentState;

    DeterministicAutomata(FiniteSet<Integer> initState,
                          FiniteSet<Integer> finalState,
                          FiniteSet<Integer> stateSet,
                          FiniteSet<Character> symbolSet,
                          TransitionFunction<Integer, Character> transitionFunction) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction);
        initialise();
    }

    public void initialise(){
        currentState = initState.iterator().next();
    }

    public void next(char symbol) {
        Pair<Integer, Character> key = Pair.of(currentState, symbol);
        FiniteSet<Integer> nextState = transitionFunction.get(key);
        currentState = nextState.iterator().next();
    }

    public void next(String string) {
        for (char symbol : string.toCharArray()) {
            next(symbol);
        }
    }

    public void next(String string, boolean debug){
        if (!debug){
            next(string);
            return;
        }
        System.out.println("Current state: "+currentState);
        for (char symbol: string.toCharArray()){
            next(symbol);
            System.out.println("Symbol: "+symbol+", State: "+currentState);
        }
    }
    public boolean accept(String string, boolean debug) {
        next(string, debug);
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
        System.out.println(zeroOne.accept("1010", true));
    }
}
