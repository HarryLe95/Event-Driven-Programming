import java.util.HashMap;
import java.util.Map;

/*
 * TransitionFunction<U,V> is the type alias for HashMap<Pair<U,V>,FiniteSet<V>> (C++ type alias),
 * use to store the mapping from symbol x state to state(s).
 */

public class TransitionFunction<U, V> extends
        HashMap<Pair<U, V>, FiniteSet<U>> {

    //Constructors from parent class
    TransitionFunction() {
        super();
    }

    TransitionFunction(int initialCapacity) {
        super(initialCapacity);
    }

    TransitionFunction(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    TransitionFunction(Map<? extends Pair<U, V>, ? extends FiniteSet<U>> m) {
        super(m);
    }

    //Static factory method
    public static <U, V> TransitionFunction<U, V> of(U kf, V ks, FiniteSet<U> v) {
        return new TransitionFunction<>(Map.of(Pair.of(kf, ks), v));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3,
                                                     U kf4, V ks4, FiniteSet<U> v4) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3,
                Pair.of(kf4, ks4), v4
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3,
                                                     U kf4, V ks4, FiniteSet<U> v4,
                                                     U kf5, V ks5, FiniteSet<U> v5) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3,
                Pair.of(kf4, ks4), v4,
                Pair.of(kf5, ks5), v5
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3,
                                                     U kf4, V ks4, FiniteSet<U> v4,
                                                     U kf5, V ks5, FiniteSet<U> v5,
                                                     U kf6, V ks6, FiniteSet<U> v6) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3,
                Pair.of(kf4, ks4), v4,
                Pair.of(kf5, ks5), v5,
                Pair.of(kf6, ks6), v6
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3,
                                                     U kf4, V ks4, FiniteSet<U> v4,
                                                     U kf5, V ks5, FiniteSet<U> v5,
                                                     U kf6, V ks6, FiniteSet<U> v6,
                                                     U kf7, V ks7, FiniteSet<U> v7) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3,
                Pair.of(kf4, ks4), v4,
                Pair.of(kf5, ks5), v5,
                Pair.of(kf6, ks6), v6,
                Pair.of(kf7, ks7), v7
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3,
                                                     U kf4, V ks4, FiniteSet<U> v4,
                                                     U kf5, V ks5, FiniteSet<U> v5,
                                                     U kf6, V ks6, FiniteSet<U> v6,
                                                     U kf7, V ks7, FiniteSet<U> v7,
                                                     U kf8, V ks8, FiniteSet<U> v8) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3,
                Pair.of(kf4, ks4), v4,
                Pair.of(kf5, ks5), v5,
                Pair.of(kf6, ks6), v6,
                Pair.of(kf7, ks7), v7,
                Pair.of(kf8, ks8), v8
        ));
    }

    public static <U, V> TransitionFunction<U, V> of(U kf0, V ks0, FiniteSet<U> v0,
                                                     U kf1, V ks1, FiniteSet<U> v1,
                                                     U kf2, V ks2, FiniteSet<U> v2,
                                                     U kf3, V ks3, FiniteSet<U> v3,
                                                     U kf4, V ks4, FiniteSet<U> v4,
                                                     U kf5, V ks5, FiniteSet<U> v5,
                                                     U kf6, V ks6, FiniteSet<U> v6,
                                                     U kf7, V ks7, FiniteSet<U> v7,
                                                     U kf8, V ks8, FiniteSet<U> v8,
                                                     U kf9, V ks9, FiniteSet<U> v9) {
        return new TransitionFunction<>(Map.of(
                Pair.of(kf0, ks0), v0,
                Pair.of(kf1, ks1), v1,
                Pair.of(kf2, ks2), v2,
                Pair.of(kf3, ks3), v3,
                Pair.of(kf4, ks4), v4,
                Pair.of(kf5, ks5), v5,
                Pair.of(kf6, ks6), v6,
                Pair.of(kf7, ks7), v7,
                Pair.of(kf8, ks8), v8,
                Pair.of(kf9, ks9), v9
        ));
    }

    public static <U, V> TransitionFunction<U, V> ofEntries(
            Map.Entry<? extends Pair<U, V>, ? extends FiniteSet<U>>... entries) {
        TransitionFunction<U, V> f = new TransitionFunction<>();
        for (Map.Entry<? extends Pair<U, V>, ? extends FiniteSet<U>> item : entries) {
            Pair<U, V> k = item.getKey();
            FiniteSet<U> v = item.getValue();
            f.put(k, v);
        }
        return f;
    }
}