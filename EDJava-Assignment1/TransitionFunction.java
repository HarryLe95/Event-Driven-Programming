import java.util.HashMap;
import java.util.Map;

public class TransitionFunction<stateType,symbolType> extends 
             HashMap<Pair<stateType,symbolType>,FiniteSet<stateType>>{
    
    TransitionFunction(){
        super();
    }

    TransitionFunction(int initialCapacity){
        super(initialCapacity);
    }

    TransitionFunction(int initialCapacity, float loadFactor){
        super(initialCapacity, loadFactor);
    }

    TransitionFunction(Map<? extends Pair<stateType,symbolType>,? extends FiniteSet<stateType>> m){
        super(m);
    }

}