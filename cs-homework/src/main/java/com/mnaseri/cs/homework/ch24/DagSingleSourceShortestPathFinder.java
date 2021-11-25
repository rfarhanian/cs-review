package com.mnaseri.cs.homework.ch24;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch22.s4.DFSTopologicalSorter;
import com.mmnaseri.cs.clrs.ch22.s4.TopologicalSorter;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;

import java.util.List;

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
        List<Integer> sorted = topologicalSorter.sort(graph);
        for (Integer node : sorted) {
            Vertex<V> current = graph.get(node);
            Integer currentDistance = current.getProperty("distance", Integer.class);
            List<Vertex<V>> neighbors = graph.getNeighbors(current.getIndex());
            for (Vertex<V> neighbor : neighbors) {
                Edge<E, V> edge = graph.edge(current.getIndex(), neighbor.getIndex());
                int weight = weight(edge);
                int suggestedDistance = weight + currentDistance;
                if (neighbor.getProperty("distance", Integer.class) > suggestedDistance) {
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
