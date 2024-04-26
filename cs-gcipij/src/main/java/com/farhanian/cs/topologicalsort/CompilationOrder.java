package com.farhanian.cs.topologicalsort;

import java.util.*;

/**
 * Please consider that the following solution cannot be solved using a classical topological sort which is a DFS with
 * stack. The reason is there might be a cycle in the graph. So what we should do is to keep track of the inverse
 * of the graph and when there is no in-degree for a node, we will add it to the output. We will visit all
 * independent nodes and start with candidates with empty in-degrees. Then we visit all the neighbors and disconnect
 * from them and add them to the output as they become independent.
 * <p>
 * Please consider that this works as a topological sort because there are cycles. That is why this algorithm works.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/compilation-order">The problem</a>
 */
public class CompilationOrder {
    public static void main(String[] args) {
        ArrayList<List<Character>> dependencies = new ArrayList<>();
        dependencies.add(Arrays.asList('B', 'A'));
        dependencies.add(Arrays.asList('C', 'A'));
        dependencies.add(Arrays.asList('D', 'C'));
        dependencies.add(Arrays.asList('E', 'D'));
        dependencies.add(Arrays.asList('E', 'B'));
        //[[B, A], [C, A], [D, C], [E, D], [E, B]]
        System.out.println("-------------------------");
        System.out.println("dependencies = " + dependencies);
        List<Character> compilationOrder = findCompilationOrder(dependencies);
        System.out.println("compilationOrder = " + compilationOrder);
        System.out.println("-------------------------");
        dependencies.add(Arrays.asList('B', 'A'));
        dependencies.add(Arrays.asList('C', 'A'));
        dependencies.add(Arrays.asList('D', 'B'));
        dependencies.add(Arrays.asList('E', 'B'));
        dependencies.add(Arrays.asList('E', 'D'));
        dependencies.add(Arrays.asList('E', 'C'));
        dependencies.add(Arrays.asList('F', 'D'));
        dependencies.add(Arrays.asList('F', 'E'));
        dependencies.add(Arrays.asList('F', 'C'));
//         [[B, A], [C, A], [D, B], [E, B], [E, D], [E, C], [F, D], [F, E], [F, C]]
        System.out.println("dependencies = " + dependencies);
        System.out.println("compilationOrder = " + findCompilationOrder(dependencies));
        System.out.println("-------------------------");
        dependencies = new ArrayList<>();
        dependencies.add(Arrays.asList('A', 'B'));
        dependencies.add(Arrays.asList('B', 'A'));
        System.out.println("dependencies = " + dependencies);
        System.out.println("compilationOrder = " + findCompilationOrder(dependencies));
        System.out.println("-------------------------");
        //[[B, C], [C, A], [A, F]]
        dependencies = new ArrayList<>();
        dependencies.add(Arrays.asList('B', 'C'));
        dependencies.add(Arrays.asList('C', 'A'));
        dependencies.add(Arrays.asList('A', 'F'));
        System.out.println("dependencies = " + dependencies);
        System.out.println("compilationOrder = " + findCompilationOrder(dependencies));
        System.out.println("-------------------------");
        dependencies = new ArrayList<>();
        dependencies.add(Arrays.asList('C', 'C'));
        System.out.println("dependencies = " + dependencies);
        System.out.println("compilationOrder = " + findCompilationOrder(dependencies));
        System.out.println("-------------------------");

    }

    public static List<Character> findCompilationOrder(List<List<Character>> dependencies) {
        Graph graph = new Graph();
        for (List<Character> item : dependencies) {
            //System.out.println("item size():" + item.size());
            Character neighbor = item.get(0);
            Character vertex = item.get(1);
            //System.out.println("item 0:" + vertex);
            //System.out.println("item 1:" + neighbor);
            graph.addVertex(vertex);
            graph.addVertex(neighbor);
            graph.connect(vertex, neighbor);
        }
//        System.out.println("Graph is initialized:");
        Queue<Character> sources = new LinkedList<>();
        for (Character item : graph.getVertices()) {
            if (graph.getInDegrees(item).isEmpty()) {
                sources.add(item);
            }
        }
        List<Character> output = new ArrayList<>();
        while (!sources.isEmpty()) {
            Character current = sources.remove();
            output.add(current);
//            System.out.println("Source just added " + current);
            //disconnect
            List<Character> neighbors = graph.getNeighbors(current);
//            System.out.println("neighbors: " + neighbors);
            for (Character neighbor : neighbors) {
                graph.disconnect(current, neighbor);
                if (graph.getInDegrees(neighbor).isEmpty()) {
                    sources.add(neighbor);
                }
            }
        }
        return output;
    }

    private static class Graph {
        private Map<Character, List<Character>> nodes = new HashMap<>();
        private Map<Character, List<Character>> inverse = new HashMap<>();

        public void addVertex(Character vertex) {
            nodes.putIfAbsent(vertex, new ArrayList<>());
            inverse.putIfAbsent(vertex, new ArrayList<>());
            //System.out.println("vertex(" + vertex + ") is added to the graph");
        }

        public void connect(Character first, Character second) {
            nodes.get(first).add(second);
            inverse.get(second).add(first);
            //System.out.println("first("+first+") is connected to second("+second+").");
        }

        public void disconnect(Character first, Character second) {
            nodes.get(first).remove(second);
            inverse.get(second).remove(first);
//            System.out.println("first(" + first + ") is disconnected from the second(" + second + ").");
        }

        public List<Character> getInDegrees(Character vertex) {
            List<Character> indegress = inverse.get(vertex);
            return new ArrayList<>(indegress);
        }


        public List<Character> getNeighbors(Character vertex) {
            List<Character> neighbors = nodes.get(vertex);
            return new ArrayList<>(neighbors);
        }

        public List<Character> getVertices() {
            return new ArrayList<>(nodes.keySet());
        }
    }

}
