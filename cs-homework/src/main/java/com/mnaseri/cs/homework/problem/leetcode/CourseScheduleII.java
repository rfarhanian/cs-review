package com.mnaseri.cs.homework.problem.leetcode;

import java.util.*;

/**
 * @see <a href="https://leetcode.com/problems/course-schedule-ii/">The Problem</a>
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses, prerequisites);
        Map<Integer, State> visited = new HashMap<>();
        Stack<Integer> output = new Stack<>();
        List<Integer> vertices = graph.getVertices();
        for (int vertex : vertices) {
            visited.put(vertex, State.BLANK);
        }
        for (int vertex : vertices) {
            if (visited.get(vertex) == State.BLANK) {
                if (!dfs(graph, vertex, visited, output)) {
                    return new int[0];
                }
            }
        }
        int[] result = new int[output.size()];
        int count = 0;
        while (!output.empty()) {
            result[count] = output.pop();
            count++;
        }
        return result;
    }

    private boolean dfs(Graph graph, int vertex, Map<Integer, State> visited, Stack<Integer> output) {
        if (visited.get(vertex) == State.PARTIAL) {
            output.clear();
            return false;
        }
        if (visited.get(vertex) == State.BLANK) {
            visited.put(vertex, State.PARTIAL);
            List<Integer> neighbors = graph.getNeighbors(vertex);
            for (int neighbor : neighbors) {
                if (!dfs(graph, neighbor, visited, output)) {
                    return false;
                }
            }
            output.push(vertex);
            visited.put(vertex, State.COMPLETE);
        }
        return true;
    }

    private enum State {BLANK, PARTIAL, COMPLETE}

    public class Graph {

        private List<Integer> nodes = new ArrayList<>();
        private Map<Integer, List<Integer>> edges = new HashMap<>();

        public Graph(int numCourses, int[][] prerequisites) {
            for (int i = 0; i < numCourses; i++) {
                nodes.add(i);
            }

            for (int[] prereq : prerequisites) {
                edges.putIfAbsent(prereq[1], new ArrayList<>());
                List<Integer> values = edges.get(prereq[1]);
                values.add(prereq[0]);
            }
        }

        public List<Integer> getNeighbors(int vertex) {
            List<Integer> result = edges.get(vertex);
            return result == null || result.isEmpty() ? Collections.emptyList() : Collections.unmodifiableList(result);
        }

        public List<Integer> getVertices() {
            return Collections.unmodifiableList(nodes);
        }
    }
}
