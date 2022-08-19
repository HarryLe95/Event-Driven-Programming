import java.util.Collection;
import java.util.HashSet;

/*
 * FiniteSet is the type alias for HashSet<E> (C++ type alias), use to store
 * states and symbols for FiniteStateMachines. Additional methods provided
 * include getIntersection and getUnion, which are useful for validating input symbols
 * and declared initial states.
 */

public class FiniteSet<E> extends HashSet<E>{
    //Constructors following HashSet constructors
    FiniteSet(){
        super();
    }

    FiniteSet(Collection<? extends E>c){
        super(c);
    }

    FiniteSet(int initialCapacity){
        super(initialCapacity);
    }

    FiniteSet(int initialCapacity, float loadFactor){
        super(initialCapacity,loadFactor);
    }

    //Return the intersection between this and another
    public FiniteSet<E> getIntersection(FiniteSet<E> another){
        FiniteSet<E> intersection = new FiniteSet<E>(another);
        intersection.retainAll(this);
        return intersection;
    }

    //Return the union of this and another
    public FiniteSet<E> getUnion(FiniteSet<E> another){
        FiniteSet<E> union = new FiniteSet<E>(another);
        union.addAll(this);
        return union;
    }
}