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
 * The main limitation of Dijkstra’s algorithm is that it can't give proper result for the graphs having negative
 * weighed edges.
 *  * 1- We create a copy of the graph with infinite distance for all edges except for zero for starting point.
 *  * 2- Then we add all the nodes of the copied graph into a priority queue (with a comparator sorting by distance increasingly).
 *  * 3- We relax all edges of the original graph and apply the result in the copied graph.
 *  * 4- And finally we will return the result.
 *
 * @param <E>
 * @param <V>
 */
public class DijkstraSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {


    private AdjacencyListGraph<E, V> initialize(Graph<E, V> graph, int start) {
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
        return result;
    }

    public Graph<E, V> find(Graph<E, V> graph, int start) {
        AdjacencyListGraph<E, V> result = initialize(graph, start);

        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(new Comparator<Vertex<V>>() {
            public int compare(Vertex<V> first, Vertex<V> second) {
                return Integer.compare((int) first.getProperty("distance"), (int) second.getProperty("distance"));
            }
        });
        pq.addAll(result.getVertices());


        while (!pq.isEmpty()) {
            Vertex<V> current = pq.poll();

            for (Vertex<V> neighbor : result.getNeighbors(current)) {
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
            pq.remove(neighbor); //PriorityQueue implementation does not have a decrease-key method. So we have to remove and add the node to correctly reheapify the array.
            pq.add(neighbor);
        }
    }

    protected int weight(Edge<E, V> edge) {
        final E details = edge.getDetails();
        return details == null ? 0 : details.getWeight();
    }


}
