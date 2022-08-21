import java.util.Map;

public class StateContainer {
    public State start;
    public State end;

    StateContainer(State start, State end){
        this.start = start;
        this.end = end;
    }

    public static StateContainer fromSymbol(char c){
        State start = new State();
        State end = new State();
        start.add(end, c);
        return new StateContainer(start, end);
    }

    public static StateContainer fromSequence(StateContainer first, StateContainer second){
        first.end.add(second.start);
        return new StateContainer(first.start, second.end);
    }

    public static StateContainer fromAlternation(StateContainer first, StateContainer second){
        State start = new State();
        State end = new State();
        start.add(first.start);
        start.add(second.start);
        first.end.add(end);
        second.end.add(end);
        return new StateContainer(start, end);
    }

    public static StateContainer fromRepetitionZero(StateContainer another){
        State start = new State();
        start.add(another.start);
        another.end.add(start);
        return new StateContainer(start, start);
    }

    public static StateContainer fromRepetitionOne(StateContainer another){
        State start = new State();
        start.add(another.start);
        another.end.add(start);
        return new StateContainer(another.start, start);
    }

    public void printSubroutine(State currentState, FiniteSet<State> excludedStates){
        currentState.repr();
        if (!currentState.transition.isEmpty()){
            for (Map.Entry<Character, FiniteSet<State>> entry: currentState.transition.entrySet()){
                for (State state: entry.getValue()){
                    if (!excludedStates.contains(state)){
                        excludedStates.add(state);
                        printSubroutine(state, excludedStates);
                    }
                }
            }
        }
        if (!currentState.eTransition.isEmpty()){
            for (State state: currentState.eTransition){
                if (!excludedStates.contains(state)){
                    excludedStates.add(state);
                    printSubroutine(state, excludedStates);
                }
            }
        }
    }

    public void printStates(){
        FiniteSet<State> excludedStates = FiniteSet.of(start);
        printSubroutine(start,excludedStates);
    }

    public static void main(String[] args) {
        StateContainer a = fromSymbol('a');
        StateContainer a1 = fromRepetitionZero(a);
        a1.printStates();
        a1.end.repr();
    }

}
