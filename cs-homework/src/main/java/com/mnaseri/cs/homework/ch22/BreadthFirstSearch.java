package com.mnaseri.cs.homework.ch22;

import java.util.*;

public class BreadthFirstSearch {

    private Map<Integer, State> visited = new HashMap<>();

    private Graph graph;

    public void search(GraphVisitor visitor) {

        if (!graph.isEmpty()) {
            List<Integer> vertices = graph.getVertices();
            for (Integer element : vertices) {
                visited.put(element, State.BLANK);
            }
            for (Integer element : vertices) {
                if (visited.get(element) == State.BLANK) {
                    search(visitor, element);
                }
            }
        }
    }

    public BreadthFirstSearch(Graph graph) {
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

        BreadthFirstSearch depthFirstSearch = new BreadthFirstSearch(graph);
        depthFirstSearch.search(new GraphVisitor() {
            @Override
            public void visit(int value) {
                System.out.println(value);
            }
        });
    }

    private void search(GraphVisitor visitor, Integer element) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(element);
        visited.put(element, State.PARTIAL);
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            visitor.visit(vertex);
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer neighbor : neighbors) {
                if (visited.get(neighbor) == State.BLANK) {
                    visited.put(neighbor, State.PARTIAL);
                    queue.add(neighbor);
                }
            }
            visited.put(vertex, State.COMPLETE);
        }
    }

    private enum State {BLANK, PARTIAL, COMPLETE}

}
