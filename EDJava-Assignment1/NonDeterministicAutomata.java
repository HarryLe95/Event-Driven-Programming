public class NonDeterministicAutomata extends FiniteStateMachine {

    protected FiniteSet<Integer> currentState;

    NonDeterministicAutomata(){}
    NonDeterministicAutomata(FiniteSet<Integer> initState,
                             FiniteSet<Integer> finalState,
                             FiniteSet<Integer> stateSet,
                             FiniteSet<Character> symbolSet,
                             TransitionFunction<Integer, Character> transitionFunction) {
        super(initState, finalState, stateSet, symbolSet, transitionFunction);
        initialise();
    }

    public void initialise() {
        currentState = initState;
    }

    public void next(char symbol) {
        FiniteSet<Integer> nextState = new FiniteSet<>();
        for (int item: currentState){
            Pair<Integer, Character> key = Pair.of(item, symbol);
            FiniteSet<Integer> temp = transitionFunction.get(key);
            if(temp!=null){
                nextState.addAll(temp);
            }
        }
        currentState = nextState;
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
                0, '0', FiniteSet.of(0,1),
                0, '1', FiniteSet.of(0),
                1, '1', FiniteSet.of(2)
        );
        NonDeterministicAutomata zeroOne = new NonDeterministicAutomata(initState, finalState,
                stateSet, symbolSet, transitionFunction);
        System.out.println(zeroOne.accept("0101", true));
        System.out.println(zeroOne.accept("1010", true));
    }

}
