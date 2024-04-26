package com.farhanian.cs.topologicalsort;

import java.util.*;

/**
 * This is a very interesting Graph problem.
 * Before tackling AlienDictionary, please remember that following is the way we sort in English:
 * <p><ul>First Example: {"b", "a"} -> Sorted: {"a", "b"}</ul></p>
 * <ul>Second Example: {"aa", "a"} -> Sorted: {"a", "aa"} <p>(This happens because when the first "a" is compared, there is
 * no other character in the second string, that is why the comparator finds the second string greater than the first one.</ul></p>
 * <ul>Third Example: {"abde", "abcx"} -> Sorted: {"abcx", "abde"}</ul> <p> (This happens because "d" is smaller than "c". "x"
 * and "e" play no role in this logic.)</p>
 * <p>
 * <b>Now if you are analyzing an alien language, you can only deduce order according to the first unequal character of
 * two consecutive words in a sorted list.<b/>
 * </p>
 * <p>
 *     So
 * <ol> 1- you need to create a graph with Kahn's topological sort in mind(keep track of indegrees). </ol>
 * <ol> 2-Then you should run the Kahn's topological sort and return the visited nodes if there are no cycles (you have
 * visited all existing nodes).</ol>
 * </p>
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/alien-dictionary">The problem</a>
 * @see <a href="https://leetcode.com/problems/alien-dictionary/editorial/">Leetcode problem</a>
 * @see <a href="https://en.wikipedia.org/wiki/Topological_sorting#Kahn's_algorithm">Kahn's topological sort algorithm</a>
 */
public class AlienDictionary {
    public static void main(String[] args) {
        List<String> sample = Arrays.asList("a", "abb", "aa", "ab");
        Collections.sort(sample);
        System.out.println("sorted sample = " + sample);
        sample = Arrays.asList("abde", "abcx");
        Collections.sort(sample);
        System.out.println("another sorted sample = " + sample);

        List<List<String>> words = Arrays.asList(
                Arrays.asList("mzosr", "mqov", "xxsvq", "xazv", "xazau", "xaqu", "suvzu", "suvxq", "suam", "suax", "rom", "rwx", "rwv"),
                Arrays.asList("vanilla", "alpine", "algor", "port", "norm", "nylon", "ophellia", "hidden"),
                Arrays.asList("passengers", "to", "the", "unknown"),
                Arrays.asList("alpha", "bravo", "charlie", "delta"),
                Arrays.asList("jupyter", "ascending")
        );

        for (int i = 0; i < words.size(); i++) {
            System.out.println(i + 1 + ".\twords = " + words.get(i));
            alienOrder(words.get(i).toArray(new String[0]));
            System.out.println("\tDictionary = \"" + alienOrder(words.get(i).toArray(new String[0])) + "\"");
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static String alienOrder(String[] words) {
        Graph graph = new Graph();
        for (String word : words) {
            for (char current : word.toCharArray()) {
                graph.insert(current);
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String previous = words[i];
            String current = words[i + 1];
            boolean notFound = true;
            for (int j = 0; j < Math.min(current.length(), previous.length()); j++) {
                char first = previous.charAt(j);
                char second = current.charAt(j);
                if (first != second) {
                    notFound = false;
                    graph.connect(first, second);
                    break; //Due to third example. We can only deduce priority from the first different character.
                }
            }
            if (notFound && previous.length() > current.length()) {
                return "";
            }
        }

        Queue<Character> queue = new LinkedList<>();
        StringBuilder output = new StringBuilder();
        for (Character item : graph.getVertices()) {
            if (graph.isIsolated(item)) {
                queue.add(item);
            }
        }
        int count = graph.getSize();
        while (!queue.isEmpty()) {
            Character current = queue.remove();
            output.append(current);
            count--;
            List<Character> neighbors = graph.getNeighbors(current);
            for (Character neighbor : neighbors) {
                graph.disconnect(current, neighbor);
                if (graph.isIsolated(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        //If you have not visited all vertices, there must be a cycle.
        //Background: If your graph is acyclic, you can do topological sort using a dfs otherwise, you have to a BFS using
        //the most independent nodes(nodes with indegree zero). If there are unvisited nodes, cycle must have prevented them from reaching to indegree=0.
        return count != 0 ? "" : output.toString();
    }

    private static class Graph {
        Map<Character, List<Character>> nodes = new HashMap<>();
        Map<Character, Integer> inverse = new HashMap<>();

        public void insert(Character vertex) {
            nodes.putIfAbsent(vertex, new ArrayList<>());
            inverse.putIfAbsent(vertex, 0);
        }

        public boolean isConnected(char first, char second) {
            List<Character> adjList = nodes.get(first);
            return adjList.contains(second);
        }

        public void connect(Character first, Character second) {
//            if (!isConnected(first, second)) {
            nodes.get(first).add(second);
            inverse.put(second, inverse.get(second) + 1);
//            }
        }

        public void disconnect(Character first, Character second) {
//            if (isConnected(first, second)) {
            nodes.get(first).remove(second);
            inverse.put(second, inverse.get(second) - 1);
//            }
        }

        public List<Character> getVertices() {
            return new ArrayList<>(nodes.keySet());
        }

        public int getSize() {
            return nodes.keySet().size();
        }

        public List<Character> getNeighbors(Character vertex) {
            return new ArrayList<>(nodes.get(vertex));
        }

        public boolean isIsolated(Character vertex) {
            return inverse.get(vertex) == 0;
        }
    }
}
