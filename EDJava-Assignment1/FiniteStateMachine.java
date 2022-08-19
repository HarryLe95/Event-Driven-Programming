
public class FiniteStateMachine{
    private final FiniteSet<Integer> initState;
    private final FiniteSet<Integer> finalState;
    private final FiniteSet<Integer> stateSet;
    private final FiniteSet<String>  symbolSet;
    private final TransitionFunction<Integer,String> transitionFunction;

    FiniteStateMachine(FiniteSet<Integer> initState, 
                       FiniteSet<Integer> finalState, 
                       FiniteSet<Integer> stateSet,
                       FiniteSet<String>  symbolSet,
                       TransitionFunction<Integer,String> transitionFunction){
        this.initState = initState;
        this.finalState= finalState;
        this.stateSet  = stateSet;
        this.symbolSet = symbolSet;
        this.transitionFunction = transitionFunction;
    }

    public boolean isValidState(int state){
        return stateSet.contains(state);
    }

    public boolean isValidState(FiniteSet<Integer> states){
        return stateSet.contains(states);
    }


    public boolean isValidSymbol(String symbol){
        return symbolSet.contains(symbol);
    }
    

}