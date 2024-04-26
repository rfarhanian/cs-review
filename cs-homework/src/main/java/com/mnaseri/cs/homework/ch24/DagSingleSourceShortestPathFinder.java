package com.mnaseri.cs.homework.ch24;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch22.s4.DFSTopologicalSorter;
import com.mmnaseri.cs.clrs.ch22.s4.TopologicalSorter;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

import java.util.List;

/**
 * The assumption is that the graph is a "Directed Acyclic Graph" or a tree so there will be no negative cycles,
 * although you can have negative edges. Djikstra's assumption is that all nodes are non-negative.
 * 1- We create a copy of the graph with infinite distance for all edges except for zero for starting point.
 * 2- Then we do a topological sort and iterate through all the sorted nodes one after another.
 * 3- We relax all edges of the original graph.
 * 4- And finally we will return the result.
 *
 * @param <E>
 * @param <V>
 */
public class DagSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {

    public Graph<E, V> find(Graph<E, V> graph, int start) {
        Graph<E, V> result = new AdjacencyListGraph<>();
        List<Vertex<V>> vertices = graph.getVertices();

        for (int i = 0, verticesSize = vertices.size(); i < verticesSize; i++) {
            result.add();
            Vertex<V> node = result.get(i);
            node.setProperty("distance", Integer.MAX_VALUE);
            node.setProperty("predecessor", null);
        }

        for (Edge edge : graph.getEdges()) {
            result.connect(edge.getFrom().getIndex(), edge.getTo().getIndex());
        }
        result.get(start).setProperty("distance", 0);
        TopologicalSorter topologicalSorter = new DFSTopologicalSorter();
        List<Integer> sorted = topologicalSorter.sort(result);
        for (Integer node : sorted) {
            Vertex<V> current = result.get(node);
            Integer currentDistance = current.getProperty("distance", Integer.class);
            List<Vertex<V>> neighbors = graph.getNeighbors(current.getIndex());
            for (Vertex<V> neighbor : neighbors) {
                Edge<E, V> edge = graph.edge(current.getIndex(), neighbor.getIndex());
                int weight = weight(edge);
                int suggestedDistance = weight + currentDistance;
                Integer actualDistance = neighbor.getProperty("distance", Integer.class);
                if (actualDistance > suggestedDistance) {
                    neighbor.setProperty("distance", suggestedDistance);
                    neighbor.setProperty("predecessor", current);
                }
            }
        }
        return result;


    }

    protected int weight(Edge<E, V> edge) {
        final E details = edge.getDetails();
        return details == null ? 0 : details.getWeight();
    }

}
