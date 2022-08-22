package src.utils;

import java.util.HashMap;
import java.util.Map;

public class DFS {
    public FiniteSet<Integer> getVertexSet() {
        return vertexSet;
    }

    public void setVertexSet(FiniteSet<Integer> vertexSet) {
        this.vertexSet = vertexSet;
    }

    protected FiniteSet<Integer> vertexSet;

    public HashMap<Integer, FiniteSet<Integer>> getEdgeSet() {
        return edgeSet;
    }

    public void setEdgeSet(HashMap<Integer, FiniteSet<Integer>> edgeSet) {
        this.edgeSet = edgeSet;
    }

    protected HashMap<Integer, FiniteSet<Integer>> edgeSet;

    public HashMap<Integer, FiniteSet<Integer>> getClosure() {
        return closure;
    }

    protected HashMap<Integer, FiniteSet<Integer>> closure;
    protected HashMap<Integer, Integer> status;


    public DFS(FiniteSet<Integer> vertexSet,
               HashMap<Integer, FiniteSet<Integer>> edgeSet) {
        this.vertexSet = vertexSet;
        this.edgeSet = edgeSet;
        initialise();
    }

    public void initialise() {
        HashMap<Integer, FiniteSet<Integer>> closure = new HashMap<>();
        HashMap<Integer, Integer> status = new HashMap<>();
        for (int v : vertexSet) {
            FiniteSet<Integer> vTargets = FiniteSet.of(v);
            if (edgeSet.get(v) != null) {
                vTargets.addAll(edgeSet.get(v));
            }
            closure.put(v, vTargets);
            status.put(v, 0);
        }
        this.closure = closure;
        this.status = status;
    }

    private void runSubroutine(int u) {
        status.put(u, 1);
        for (int v : closure.get(u)) {
            if (status.get(v) == 0) {
                runSubroutine(v);
            }
            closure.put(u,closure.get(u).getUnion(closure.get(v)));
        }
    }

    public void run() {
        for (int u : vertexSet) {
            if (status.get(u) == 0) {
                runSubroutine(u);
            }
        }
    }

    public static void main(String[] args) {
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
        System.out.println(graphDFS.getClosure());
    }
}
