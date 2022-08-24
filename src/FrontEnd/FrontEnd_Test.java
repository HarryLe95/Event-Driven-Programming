package src.FrontEnd;

import org.junit.Test;
import src.utils.FiniteSet;
import src.utils.Pair;
import src.utils.TransitionFunction;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FrontEnd_Test {
    @Test
    public void testFromSymbol1() {
        FrontEnd model = new FrontEnd("a",false);
        StateContainer container = model.getModelContainer();
        TransitionFunction<Integer, Character> TF = TransitionFunction.of(
                0, 'a', FiniteSet.of(1)
        );
        assertEquals(TF, container.getTransitionFunction());
    }

    @Test
    public void testFromSequence1() {
        FrontEnd model = new FrontEnd("ab",false);
        StateContainer container = model.getModelContainer();
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
        FrontEnd model = new FrontEnd("a|b",false);
        StateContainer container = model.getModelContainer();
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
        FrontEnd model = new FrontEnd("a*",false);
        StateContainer container = model.getModelContainer();
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

    @Test
    public void test1(){
        FrontEnd model = new FrontEnd("(ab)*|c+",false);
        StateContainer container = model.getModelContainer();
        TransitionFunction<Integer, Character> TF =
                TransitionFunction.ofEntries(
                        Map.entry(Pair.of(2, 'a'), FiniteSet.of(3)),
                        Map.entry(Pair.of(4, 'b'), FiniteSet.of(5)),
                        Map.entry(Pair.of(7, 'c'), FiniteSet.of(8))
                );
        HashMap<Integer, FiniteSet<Integer>> eTF = new HashMap<>(Map.of(
                0, FiniteSet.of(1,7),
                1, FiniteSet.of(2,6),
                3, FiniteSet.of(4),
                5, FiniteSet.of(1),
                8, FiniteSet.of(9),
                9, FiniteSet.of(6,7)
        ));
        assertEquals(TF, container.getTransitionFunction());
        assertEquals(eTF, container.getETransitionFunction());
    }
}
