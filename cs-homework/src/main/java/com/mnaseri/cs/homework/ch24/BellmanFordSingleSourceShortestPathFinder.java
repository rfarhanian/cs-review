package com.mnaseri.cs.homework.ch24;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.qa.annotation.Complexity;

import java.util.List;

/**
 * Bellman-Ford is the shortest path algorithm without any restriction.
 * Bellman-Ford algorithm detects negative cycles and graphs with negative cycles have no shortest path.
 *
 * @param <E>
 * @param <V>
 */
public class BellmanFordSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {

    @Complexity(value = "O(VE)")
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        List<Vertex<V>> vertices = graph.getVertices();
        AdjacencyListGraph<E, V> result = initialize(graph, start, vertices);

        for (Vertex<V> vertex : vertices) {
            List<Edge<E, V>> edges = graph.getEdges();
            for (Edge<E, V> edge : edges) {
                relax(graph, edge.getFrom(), edge.getTo());
            }
        }

        for (Edge<E, V> edge : graph.getEdges()) {
            Vertex<V> from = edge.getFrom();
            Vertex<V> to = edge.getTo();
            int currentDistance = from.getProperty("distance", Integer.class);
            int weight = weight(edge);
            int toDistance = to.getProperty("distance", Integer.class);
            if (currentDistance + weight < toDistance) {
                return null; // This indicates that a cycle has been found.
            }
        }
        return result;
    }

    private AdjacencyListGraph<E, V> initialize(Graph<E, V> graph, int start, List<Vertex<V>> vertices) {
        AdjacencyListGraph<E, V> result = new AdjacencyListGraph<>();

        for (Vertex<V> vertex : vertices) {
            result.add();
            Vertex<V> node = result.get(vertex.getIndex());
            node.setProperty("predecessor", null);
            node.setProperty("distance", Integer.MAX_VALUE);
        }
        for (Edge<E, V> edge : graph.getEdges()) {
            result.connect(edge.getFrom().getIndex(), edge.getTo().getIndex(), edge.getDetails());
        }
        result.get(start).setProperty("distance", 0);
        return result;
    }

    private void relax(Graph<E, V> graph, Vertex<V> current, Vertex<V> neighbor) {
        int startWeight = (int) current.getProperty("distance");
        int neighborWeight = (int) neighbor.getProperty("distance");

        Edge<E, V> edge = graph.edge(current.getIndex(), neighbor.getIndex());
        int edgeWeight = weight(edge);

        if (startWeight + edgeWeight < neighborWeight) {
            neighbor.setProperty("distance", startWeight + edgeWeight);
            neighbor.setProperty("predecessor", current);
        }
    }

    protected int weight(Edge<E, V> edge) {
        final E details = edge.getDetails();
        return details == null ? 0 : details.getWeight();
    }


}
