/*
 * FiniteSet is the type alias for HashSet<E>, used to store
 * states and symbols for FiniteStateMachines. Additional methods provided
 * include getIntersection and getUnion, which are useful for validating input symbols, and
 * declaring initial states.
 */


package src.utils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class FiniteSet<E> extends HashSet<E> {
    //Constructors following HashSet constructors
    public FiniteSet() {
        super();
    }

    public FiniteSet(Collection<? extends E> c) {
        super(c);
    }

    //Return the intersection between this and another
    public FiniteSet<E> getIntersection(FiniteSet<E> another) {
        FiniteSet<E> intersection = new FiniteSet<>(another);
        intersection.retainAll(this);
        return intersection;
    }

    //Return the union of this and another
    public FiniteSet<E> getUnion(FiniteSet<E> another) {
        FiniteSet<E> union = new FiniteSet<>(another);
        union.addAll(this);
        return union;
    }

    //Static Factory method
    public static <E> FiniteSet<E> of(E... elem){
        return new FiniteSet<>(Set.of(elem));
    }

}