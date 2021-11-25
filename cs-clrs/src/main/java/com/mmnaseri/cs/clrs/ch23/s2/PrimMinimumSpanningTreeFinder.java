package com.mmnaseri.cs.clrs.ch23.s2;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mmnaseri.cs.clrs.ch23.s1.MutableWeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch23.s1.WeightedEdgeDetails;
import com.mmnaseri.cs.clrs.common.HeapProperty;

import java.util.*;

/**
 * @author Mohammad Milad Naseri (mmnaseri@programmer.net)
 * @since 1.0 (8/5/15)
 */
public class PrimMinimumSpanningTreeFinder<E extends WeightedEdgeDetails, V extends VertexDetails> implements MinimumSpanningTreeFinder<E, V> {

    public static void main(String[] args) {
        final Graph<WeightedEdgeDetails, VertexDetails> graph = new AdjacencyListGraph<>();
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 0);
        map.put("b", 1);
        map.put("c", 2);
        map.put("d", 3);
        map.put("e", 4);
        map.put("f", 5);
        map.put("g", 6);
        map.put("h", 7);
        map.put("i", 8);
        for (int i = 0; i < 9; i++) {
            graph.add();
        }
        bidiConnect(graph, value(map, "a"), value(map, "b"), 4);
        bidiConnect(graph, value(map, "a"), value(map, "h"), 8);
        bidiConnect(graph, value(map, "b"), value(map, "c"), 8);
        bidiConnect(graph, value(map, "i"), value(map, "h"), 7);
        bidiConnect(graph, value(map, "i"), value(map, "c"), 2);
        bidiConnect(graph, value(map, "i"), value(map, "g"), 6);
        bidiConnect(graph, value(map, "g"), value(map, "f"), 2);
        bidiConnect(graph, value(map, "c"), value(map, "f"), 4);
        bidiConnect(graph, value(map, "c"), value(map, "d"), 7);
        bidiConnect(graph, value(map, "d"), value(map, "e"), 9);
        bidiConnect(graph, value(map, "d"), value(map, "f"), 4);
        bidiConnect(graph, value(map, "f"), value(map, "e"), 0);
        PrimMinimumSpanningTreeFinder<WeightedEdgeDetails, VertexDetails> mstFinder = new PrimMinimumSpanningTreeFinder<>();
        Graph mst = mstFinder.find(graph);
        System.out.println("mst = " + mst);
    }

    private static void bidiConnect(Graph<WeightedEdgeDetails, VertexDetails> graph, int from, int to, int weight) {
        final WeightedEdgeDetails edgeDetails = weight(weight);
        graph.connect(from, to, edgeDetails);
        graph.connect(to, from, edgeDetails);
    }

    private static WeightedEdgeDetails weight(int weight) {
        final MutableWeightedEdgeDetails details = new MutableWeightedEdgeDetails();
        details.setWeight(weight);
        return details;
    }

    private static Integer value(Map<String, Integer> map, String value) {
        return map.get(value);
    }

    @Override
    public Graph<E, V> find(Graph<E, V> graph) {
        if (graph.isEmpty()) {
            return graph;
        }
        final Map<Integer, Integer> weights = new HashMap<>();
        final Map<Integer, Integer> parents = new HashMap<>();
        final Set<Integer> examined = new HashSet<>();
        final PriorityQueue<Integer> heap = new PriorityQueue<>(graph.size(), new PrimHeapProperty(weights));//new ArrayHeap<>(new PrimHeapProperty(weights));
        final AdjacencyListGraph<E, V> result = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : graph) {
            weights.put(vertex.getIndex(), Integer.MAX_VALUE);
            parents.put(vertex.getIndex(), null);
            result.add(vertex.getDetails());
            heap.add(vertex.getIndex());
        }
        weights.put(0, 0);
        while (!heap.isEmpty()) {
            final Integer vertex = heap.remove();
            examined.add(vertex);
            final List<Vertex<V>> neighbors = graph.getNeighbors(vertex);
            for (Vertex<V> neighbor : neighbors) {
                if (!examined.contains(neighbor.getIndex())) {
                    final int edgeWeight = weight(graph, vertex, neighbor.getIndex());
                    if (edgeWeight < weights.get(neighbor.getIndex())) {
                        weights.put(neighbor.getIndex(), edgeWeight);
                        heap.remove(neighbor.getIndex());//priority queue does not support decreaseKey operation. remove and add will do.
                        heap.add(neighbor.getIndex());
                        parents.put(neighbor.getIndex(), vertex);
                    }
                }
            }
        }
        for (int i = 1; i < graph.size(); i++) {
            result.connect(i, parents.get(i));
            result.connect(parents.get(i), i);
        }
        return result;
    }

    private static <E extends WeightedEdgeDetails, V extends VertexDetails> int weight(Graph<E, V> graph, Integer vertex, Integer neighbor) {
        final Edge<E, V> first = graph.edge(vertex, neighbor);
        final Edge<E, V> second = graph.edge(neighbor, vertex);
        if (second == null) {
            return Integer.MAX_VALUE;
        }
        E firstDetails = first.getDetails();
        E secondDetails = second.getDetails();
        if (firstDetails == null) {
            firstDetails = secondDetails;
        }
        if (secondDetails == null) {
            secondDetails = firstDetails;
        }
        final int firstWeight = firstDetails == null ? 0 : firstDetails.getWeight();
        final int secondWeight = secondDetails == null ? 0 : secondDetails.getWeight();
        return firstWeight != secondWeight ? Integer.MAX_VALUE : firstWeight;
    }

    private static class PrimHeapProperty implements HeapProperty<Integer> {

        private final Map<Integer, Integer> weights;

        private PrimHeapProperty(Map<Integer, Integer> weights) {
            this.weights = weights;
        }

        @Override
        public int compare(Integer first, Integer second) {
            final int comparison = Integer.compare(weights.get(first), weights.get(second));
            if (comparison == 0) {
                return Integer.compare(first, second);
            }
            return comparison;
        }

    }

}
