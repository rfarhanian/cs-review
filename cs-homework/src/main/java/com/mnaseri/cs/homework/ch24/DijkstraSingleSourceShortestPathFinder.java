package com.mnaseri.cs.homework.ch24;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Djikstra's shortest path algorithm (Time Complexity: Vlog(V) + E
 * Vlog(V) is for priority queue sorting work.
 * E is apparently for visiting all the neighbors.
 * The main limitation of Dijkstra’s algorithm is that it can't give proper result for the graphs having negative weighed edges.
 *
 * @param <E>
 * @param <V>
 */
public class DijkstraSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {


    public Graph<E, V> find(Graph<E, V> graph, int start) {
        AdjacencyListGraph<E, V> result = new AdjacencyListGraph<>();
        List<Vertex<V>> vertices = graph.getVertices();
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(new Comparator<Vertex<V>>() {
            public int compare(Vertex<V> first, Vertex<V> second) {
                return Integer.compare((int) first.getProperty("distance"), (int) second.getProperty("distance"));
            }
        });

        for (Vertex<V> vertex : vertices) {
            result.add();
            Vertex<V> node = result.get(vertex.getIndex());
            node.setProperty("predecessor", null);
            node.setProperty("distance", Integer.MAX_VALUE);
            pq.add(node);
        }
        for (Edge<E, V> edge : graph.getEdges()) {
            result.connect(edge.getFrom().getIndex(), edge.getTo().getIndex(), edge.getDetails());
        }
        result.get(start).setProperty("distance", 0);

        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();

            for (Vertex<V> neighbor : graph.getNeighbors(current)) {
                relax(graph, current, neighbor, pq);
            }
        }

        return result;
    }

    private void relax(Graph<E, V> graph, Vertex<V> current, Vertex<V> neighbor, PriorityQueue<Vertex<V>> pq) {
        int startWeight = (int) current.getProperty("distance");
        int neighborWeight = (int) neighbor.getProperty("distance");

        Edge<E, V> edge = graph.edge(current.getIndex(), neighbor.getIndex());
        int edgeWeight = weight(edge);

        if (startWeight + edgeWeight < neighborWeight) {
            neighbor.setProperty("distance", startWeight + edgeWeight);
            neighbor.setProperty("predecessor", current);
            pq.remove(neighbor);
            pq.add(neighbor);
        }
    }

    protected int weight(Edge<E, V> edge) {
        final E details = edge.getDetails();
        return details == null ? 0 : details.getWeight();
    }


}
