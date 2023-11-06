package com.mnaseri.cs.homework.ch22;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepthFirstSearch {

    private Map<Integer, State> visited = new HashMap<>();

    private Graph graph;

    public void search(GraphVisitor visitor) {
        visited.clear();
        if (!graph.isEmpty()) {
            List<Integer> vertices = graph.getVertices();

            for (Integer node : vertices) {
                visited.put(node, State.BLANK);
            }

            for (Integer node : vertices) {
                if (visited.get(node) == State.BLANK) {
                    search(node, visitor);
                }
            }
        }
    }

    public DepthFirstSearch(Graph graph) {
        this.graph = graph;
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(false);
        for (int i = 1; i < 11; i++) {
            graph.add(i);
        }
        graph.connect(1, 10, 200);
        graph.connect(10, 1, 2);
        graph.connect(2, 9, 200);
        graph.connect(3, 8, 200);
        graph.connect(4, 6, 200);
        graph.connect(5, 5, 0);

        DepthFirstSearch depthFirstSearch = new DepthFirstSearch(graph);
        depthFirstSearch.search(new GraphVisitor() {
            @Override
            public void visit(int value) {
                System.out.println(value);
            }
        });
    }

    private void search(Integer vertex, GraphVisitor visitor) {
        if (visited.get(vertex) == State.PARTIAL) {
            return;  // Cycle
        }
        if (visited.get(vertex) == State.BLANK) {
            visited.put(vertex, State.PARTIAL);
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer neighbor : neighbors) {
                search(neighbor, visitor);
            }
            visitor.visit(vertex);
            visited.put(vertex, State.COMPLETE);
        }
    }

    private enum State {BLANK, PARTIAL, COMPLETE}
}
