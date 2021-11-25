package com.mnaseri.cs.homework.ch24;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

import java.util.List;

/**
 * Bellman-Ford shortest path algorithm.
 * The main limitation of Bellman-Ford algorithm is negative cycle. O(VE)
 *
 * @param <E>
 * @param <V>
 */
public class BellmanFordSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {


    public Graph<E, V> find(Graph<E, V> graph, int start) {
        AdjacencyListGraph<E, V> result = new AdjacencyListGraph<>();
        List<Vertex<V>> vertices = graph.getVertices();

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

        for (Vertex<V> vertex : vertices) {
            List<Edge<E, V>> edges = graph.getEdges();
            for (Edge<E, V> edge : edges) {
                relax(graph, edge.getFrom(), edge.getTo());
            }
        }

        List<Edge<E, V>> edges = graph.getEdges();
        for (Edge<E, V> edge : edges) {

            Vertex<V> from = edge.getFrom();
            Vertex<V> to = edge.getTo();
            int currentDistance = from.getProperty("distance", Integer.class);
            int weight = weight(edge);
            int toDistance = from.getProperty("distance", Integer.class);
            if (currentDistance + weight < toDistance) {
                return null; // This indicates that a cycle has been found.
            }
        }


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
