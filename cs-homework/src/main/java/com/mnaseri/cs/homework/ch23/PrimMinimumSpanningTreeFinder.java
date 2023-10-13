package com.mnaseri.cs.homework.ch23;

//import com.mnaseri.cs.homework.ch22.AdjacencyListGraph;
//import com.mnaseri.cs.homework.ch22.Graph;

import com.mnaseri.cs.homework.ch22.AdjacencyListGraph;
import com.mnaseri.cs.homework.ch22.Graph;

import java.util.*;

/**
 * The notability notes : Chapter 23 explains the problem very well.
 * So does Eric Demaine: https://www.youtube.com/watch?v=tKwnms5iRBU
 */
public class PrimMinimumSpanningTreeFinder {

    public static void main(String[] args) {
        Graph g = new AdjacencyListGraph(true);
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);
        map.put("i", 9);
        for (int node : map.values()) {
            g.add(node);
        }
        connect(g, map, "a", "b", 4);
        connect(g, map, "a", "h", 8);
        connect(g, map, "b", "c", 8);
        connect(g, map, "h", "i", 7);
        connect(g, map, "i", "c", 2);
        connect(g, map, "i", "g", 6);
        connect(g, map, "g", "f", 2);
        connect(g, map, "c", "f", 4);
        connect(g, map, "c", "d", 7);
        connect(g, map, "d", "e", 9);
        connect(g, map, "d", "f", 14);
        connect(g, map, "f", "e", 10);
        PrimMinimumSpanningTreeFinder mstFinder = new PrimMinimumSpanningTreeFinder();
        Graph mst = mstFinder.find(g);
        System.out.println("mst = " + mst);
    }

    private static void connect(Graph g, Map<String, Integer> map, String first, String second, int weight) {
        g.connect(value(map, first), value(map, second), weight);
        g.connect(value(map, second), value(map, first), weight);
    }

    private static Integer value(Map<String, Integer> map, String value) {
        return map.get(value);
    }

    public Graph find(Graph graph) {
        Map<Integer, Integer> parents = new HashMap<>();
        Map<Integer, Integer> weights = new HashMap<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> nodes = graph.getVertices();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new PrimHeapProperty(weights));
        for (int node : nodes) {
            weights.put(node, Integer.MAX_VALUE);
            parents.put(node, null);
            priorityQueue.add(node);
        }
        weights.put(nodes.get(0), 0);
        while (!priorityQueue.isEmpty()) {
            Integer current = priorityQueue.poll();
            visited.add(current);
            for (Integer neighbor : graph.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    Integer edgeWeight = graph.edge(current, neighbor);
                    if (edgeWeight < weights.get(neighbor)) {
                        weights.put(neighbor, edgeWeight);
                        priorityQueue.remove(neighbor); //priority queue does not support decreaseKey operation. remove and add will do.
                        priorityQueue.add(neighbor);
                        parents.put(neighbor, current);
                    }
                }
            }
        }
        Graph result = new AdjacencyListGraph(true);
        for (Integer node : nodes) {
            result.add(node);
        }
        for (int i = 2; i < nodes.size(); i++) {
            result.connect(i, parents.get(i), weights.get(i));
        }
        return result;
    }

    public static class PrimHeapProperty implements Comparator<Integer> {
        private Map<Integer, Integer> weights;

        public PrimHeapProperty(Map<Integer, Integer> weights) {
            this.weights = weights;
        }

        public static void main(String[] args) {

            HashMap<Integer, Integer> weights = new HashMap<>();
            weights.put(1, 1);
            weights.put(2, 2);
            weights.put(3, 3);
            weights.put(4, 4);
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new PrimHeapProperty(weights));
            for (Map.Entry<Integer, Integer> entry : weights.entrySet()) {
                priorityQueue.add(entry.getValue());
            }
            priorityQueue.remove(4);
            weights.put(4, -1);
            priorityQueue.add(4);
            while (!priorityQueue.isEmpty()) {
                System.out.println("priorityQueue.poll() = " + priorityQueue.poll());

            }
        }

        public int compare(Integer first, Integer second) {
            int comparison = Integer.compare(weights.get(first), weights.get(second));
            return comparison == 0 ? Integer.compare(first, second) : comparison;
        }
    }

}
