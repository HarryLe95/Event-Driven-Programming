package src.FrontEnd;

import org.junit.Test;
import src.utils.FiniteSet;
import src.utils.TransitionFunction;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class StateContainerTest {
    @Test
    public void testFromSymbol1() {
        StateContainer container = StateContainer.fromSymbol('a');
        container.compile();
        TransitionFunction<Integer, Character> TF = TransitionFunction.of(
                0, 'a', FiniteSet.of(1)
        );
        assertEquals(TF, container.getTransitionFunction());
    }

    @Test
    public void testFromSymbol2() {
        StateContainer container = StateContainer.fromSymbol(' ');
        container.compile();
        TransitionFunction<Integer, Character> TF = TransitionFunction.of(
                0, ' ', FiniteSet.of(1)
        );
        assertEquals(TF, container.getTransitionFunction());
    }

    @Test
    public void testFromSequence1() {
        StateContainer A = StateContainer.fromSymbol('a');
        StateContainer B = StateContainer.fromSymbol('b');
        StateContainer container = StateContainer.fromSequence(A, B);
        container.compile();
        TransitionFunction<Integer, Character> TF = TransitionFunction.of(
                0, 'a', FiniteSet.of(1),
                2, 'b', FiniteSet.of(3)
        );
        HashMap<Integer, FiniteSet<Integer>> eTF = new HashMap<>(
                Map.of(1, FiniteSet.of(2))
        );
        assertEquals(TF, container.getTransitionFunction());
        assertEquals(eTF, container.getETransitionFunction());
    }

    @Test
    public void testFromAlternation1() {
        StateContainer A = StateContainer.fromSymbol('a');
        StateContainer B = StateContainer.fromSymbol('b');
        StateContainer container = StateContainer.fromAlternation(A, B);
        container.compile();
        TransitionFunction<Integer, Character> TF = TransitionFunction.of(
                1, 'a', FiniteSet.of(2),
                4, 'b', FiniteSet.of(5)
        );
        HashMap<Integer, FiniteSet<Integer>> eTF = new HashMap<>(
                Map.ofEntries(
                        Map.entry(0, FiniteSet.of(1, 4)),
                        Map.entry(2, FiniteSet.of(3)),
                        Map.entry(5, FiniteSet.of(3)))
                );
        assertEquals(TF, container.getTransitionFunction());
        assertEquals(eTF, container.getETransitionFunction());
    }

    @Test
    public void testFromRepetitionZero1() {
        StateContainer A = StateContainer.fromSymbol('a');
        StateContainer container = StateContainer.fromRepetitionZero(A);
        container.compile();
        TransitionFunction<Integer, Character> TF = TransitionFunction.of(
                1, 'a', FiniteSet.of(2)
        );
        HashMap<Integer, FiniteSet<Integer>> eTF = new HashMap<>(
                Map.ofEntries(
                        Map.entry(0, FiniteSet.of(1)),
                        Map.entry(2, FiniteSet.of(0)))
        );
        assertEquals(TF, container.getTransitionFunction());
        assertEquals(eTF, container.getETransitionFunction());
    }
}
