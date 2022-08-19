import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        FiniteSet<Integer> initState = FiniteSet.of(0);
        FiniteSet<Integer> finalState = FiniteSet.of(2);
        FiniteSet<Integer> stateSet = FiniteSet.of(0, 1, 2);
        FiniteSet<Character> symbolSet = FiniteSet.of('0', '1');

        //Defining transition function the manual way
//        TransitionFunction<Integer,Character> transitionFunction = new TransitionFunction<>();
//        transitionFunction.put(Pair.of(0,'0'),FiniteSet.of(1));
//        transitionFunction.put(Pair.of(0,'1'),FiniteSet.of(0));
//        transitionFunction.put(Pair.of(1,'0'),FiniteSet.of(1));
//        transitionFunction.put(Pair.of(1,'1'),FiniteSet.of(2));
//        transitionFunction.put(Pair.of(2,'0'),FiniteSet.of(1));
//        transitionFunction.put(Pair.of(2,'1'),FiniteSet.of(0));

        //Defining transition function the varargs way
//        TransitionFunction<Integer, Character> transitionFunction =
//                TransitionFunction.ofEntries(
//                        Map.entry(Pair.of(0,'0'), FiniteSet.of(1)),
//                        Map.entry(Pair.of(0,'1'), FiniteSet.of(0)),
//                        Map.entry(Pair.of(1,'0'), FiniteSet.of(1)),
//                        Map.entry(Pair.of(1,'1'), FiniteSet.of(2)),
//                        Map.entry(Pair.of(2,'0'), FiniteSet.of(1)),
//                        Map.entry(Pair.of(2,'1'), FiniteSet.of(0))
//                );

        //Defining transition function the of way - only work for up to 10 k:v pairs
        TransitionFunction<Integer, Character> transitionFunction = TransitionFunction.of(
                0, '0', FiniteSet.of(1),
                0, '1', FiniteSet.of(0),
                1, '0', FiniteSet.of(1),
                1, '1', FiniteSet.of(2),
                2, '0', FiniteSet.of(1),
                2, '1', FiniteSet.of(0)
        );
        FiniteStateMachine machine = new FiniteStateMachine(initState, finalState,
                stateSet, symbolSet,
                transitionFunction);
        FiniteSet<Integer> state = FiniteSet.of(1, 2);
        int current_state = 0;
        int last_state = 2;
        int wrong_last_state = 1;
        FiniteSet<Integer> wrong_state = FiniteSet.of(0, 1);
        char symbol = '0';
        Pair<Integer, Character> inputKey = Pair.of(current_state, symbol);
        System.out.println(transitionFunction.get(inputKey));
        System.out.println(machine.isAcceptedState(last_state));
        System.out.println(machine.isAcceptedState(state));
        System.out.println(machine.isAcceptedState(wrong_last_state));
        System.out.println(machine.isAcceptedState(wrong_state));
    }
}
