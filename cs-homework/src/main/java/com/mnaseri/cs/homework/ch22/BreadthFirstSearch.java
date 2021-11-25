package com.mnaseri.cs.homework.ch22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    private Graph graph;
    private List<Integer> visited = new ArrayList<>();

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

    public void search(GraphVisitor visitor) {
        visited.clear();
        if (!graph.isEmpty()) {
            List<Integer> vertices = graph.getVertices();
            for (Integer element : vertices) {
                search(visitor, element);
            }
        }
    }

    private void search(GraphVisitor visitor, Integer element) {
        if (visited.contains(element)) {
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(element);
        while (!queue.isEmpty()) {
            Integer vertex = queue.poll();
            visitor.visit(vertex);
            visited.add(vertex);
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (Integer neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
    }

}
