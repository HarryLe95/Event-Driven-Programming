/*
* Test getClosure method using DFS algorithm
*/
package src.utils;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DFSTest {
    @Test
    public void test1(){
        FiniteSet<Integer> vertexSet = FiniteSet.of(1, 2, 3, 4);
        HashMap<Integer, FiniteSet<Integer>> edgeSet = new HashMap<>(Map.of(
                1, FiniteSet.of(2),
                2, FiniteSet.of(3),
                4, FiniteSet.of(2)
        ));
        DFS graphDFS = new DFS(vertexSet, edgeSet);
        graphDFS.run();
        HashMap<Integer, FiniteSet<Integer>> result = new HashMap<>(
                Map.ofEntries(
                        Map.entry(1, FiniteSet.of(1,2,3)),
                        Map.entry(2, FiniteSet.of(2,3)),
                        Map.entry(3, FiniteSet.of(3)),
                        Map.entry(4, FiniteSet.of(2,3,4))
                )
        );
        assertEquals(result,graphDFS.getClosure());
    }

    @Test
    public void test2(){
        FiniteSet<Integer> vertexSet = FiniteSet.of(1, 2, 3, 4);
        HashMap<Integer, FiniteSet<Integer>> edgeSet = new HashMap<>(Map.of(
                1, FiniteSet.of(2,3),
                2, FiniteSet.of(3),
                4, FiniteSet.of(2)
        ));
        DFS graphDFS = new DFS(vertexSet, edgeSet);
        graphDFS.run();
        HashMap<Integer, FiniteSet<Integer>> result = new HashMap<>(
                Map.ofEntries(
                        Map.entry(1, FiniteSet.of(1,2,3)),
                        Map.entry(2, FiniteSet.of(2,3)),
                        Map.entry(3, FiniteSet.of(3)),
                        Map.entry(4, FiniteSet.of(2,3,4))
                )
        );
        assertEquals(result,graphDFS.getClosure());
    }

    @Test
    public void test3(){
        FiniteSet<Integer> vertexSet = FiniteSet.of(1, 2, 3, 4);
        HashMap<Integer, FiniteSet<Integer>> edgeSet = new HashMap<>(Map.of(
                1, FiniteSet.of(2)
        ));
        DFS graphDFS = new DFS(vertexSet, edgeSet);
        graphDFS.run();
        HashMap<Integer, FiniteSet<Integer>> result = new HashMap<>(
                Map.ofEntries(
                        Map.entry(1, FiniteSet.of(1,2)),
                        Map.entry(2, FiniteSet.of(2)),
                        Map.entry(3, FiniteSet.of(3)),
                        Map.entry(4, FiniteSet.of(4))
                )
        );
        assertEquals(result,graphDFS.getClosure());
    }

    @Test
    public void test4(){
        FiniteSet<Integer> vertexSet = FiniteSet.of(1, 2, 3, 4, 5, 6);
        HashMap<Integer, FiniteSet<Integer>> edgeSet = new HashMap<>(Map.of(
                1, FiniteSet.of(2, 4),
                2, FiniteSet.of(5),
                3, FiniteSet.of(5),
                4, FiniteSet.of(3),
                5, FiniteSet.of(6)
        ));
        DFS graphDFS = new DFS(vertexSet, edgeSet);
        graphDFS.run();
        HashMap<Integer, FiniteSet<Integer>> result = new HashMap<>(
                Map.ofEntries(
                        Map.entry(1, FiniteSet.of(1,2,3,4,5,6)),
                        Map.entry(2, FiniteSet.of(2,5,6)),
                        Map.entry(3, FiniteSet.of(3,5,6)),
                        Map.entry(4, FiniteSet.of(3,4,5,6)),
                        Map.entry(5, FiniteSet.of(5,6)),
                        Map.entry(6, FiniteSet.of(6))
                )
        );
        assertEquals(result,graphDFS.getClosure());
    }

    @Test
    public void test5() {
        FiniteSet<Integer> vertexSet = FiniteSet.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        HashMap<Integer, FiniteSet<Integer>> edgeSet = new HashMap<>(Map.of(
                1, FiniteSet.of(2),
                2, FiniteSet.of(3),
                4, FiniteSet.of(2, 5),
                6, FiniteSet.of(8),
                8, FiniteSet.of(7),
                9, FiniteSet.of(12),
                10, FiniteSet.of(7),
                11, FiniteSet.of(9),
                12, FiniteSet.of(10)
        ));
        DFS graphDFS = new DFS(vertexSet, edgeSet);
        graphDFS.run();
        HashMap<Integer, FiniteSet<Integer>> result = new HashMap<>(
                Map.ofEntries(
                        Map.entry(1, FiniteSet.of(1,2,3)),
                        Map.entry(2, FiniteSet.of(2,3)),
                        Map.entry(3, FiniteSet.of(3)),
                        Map.entry(4, FiniteSet.of(2,3,4,5)),
                        Map.entry(5, FiniteSet.of(5)),
                        Map.entry(6, FiniteSet.of(6,7,8)),
                        Map.entry(7, FiniteSet.of(7)),
                        Map.entry(8, FiniteSet.of(7,8)),
                        Map.entry(9, FiniteSet.of(7,9,10,12)),
                        Map.entry(10, FiniteSet.of(7,10)),
                        Map.entry(11, FiniteSet.of(7,9,10,11,12)),
                        Map.entry(12, FiniteSet.of(7, 10, 12))
                )
        );
        assertEquals(result,graphDFS.getClosure());
    }
}
