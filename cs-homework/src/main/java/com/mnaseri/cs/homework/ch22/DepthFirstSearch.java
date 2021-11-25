package com.mnaseri.cs.homework.ch22;

import java.util.ArrayList;
import java.util.List;

public class DepthFirstSearch {

    private Graph graph;
    private List<Integer> visited = new ArrayList<>();

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

    public void search(GraphVisitor visitor) {
        visited.clear();
        if (!graph.isEmpty()) {
            List<Integer> vertices = graph.getVertices();
            for (Integer node : vertices) {
                search(node, visitor);
            }
        }
    }

    private void search(Integer vertex, GraphVisitor visitor) {
        if (visited.contains(vertex)) {
            return;
        }
        visited.add(vertex);
        List<Integer> neighbors = graph.getNeighbors(vertex);
        for (Integer neighbor : neighbors) {
            search(neighbor, visitor);
        }
        visitor.visit(vertex);

    }
}
