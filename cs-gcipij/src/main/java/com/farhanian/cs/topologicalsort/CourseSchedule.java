package com.farhanian.cs.topologicalsort;

import java.util.*;

/**
 * I made a very interesting mistake when I was implementing the Graph class. The graph connect and disconnect
 * methods input types were disconnect(int first, int second)
 * <p>
 * This caused the remove method of the ArrayList class (adjList.get(first).remove(second)) to interpret second
 * parameter as the index of the object and not the object itself. For that reason, you should always remember to
 * use the original generic type as the parameter type.
 * disconnect(Integer first, Integer second)
 *
 * </p>
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/course-schedule">The problem</a>
 */
public class CourseSchedule {

    public static void main(String[] args) {

        int[][][] prereq = {
                {{1, 0}, {2, 1}},
                {{1, 0}, {0, 1}},
                {{1, 0}, {2, 1}, {3, 2}, {4, 3}},
                {{1, 0}, {2, 1}, {3, 2}, {4, 3}, {0, 4}},
                {{2, 0}, {2, 1}, {3, 2}, {4, 2}, {3, 1}}
        };
        int[] courses = {3, 2, 10, 5, 5};

        for (int i = 0; i < courses.length; i++) {
            System.out.println((i + 1) + ".\tNumber of courses: " + courses[i]);
            System.out.println("\tNumber of pre-requisites: " + Arrays.deepToString(prereq[i]));
            System.out.println("\tOutput: " + canFinish(courses[i], prereq[i]));
            System.out.println(new String(new char[100]).replace('\0', '-'));
        }
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        Graph graph = new Graph();
        for (int i = 0; i < numCourses; i++) {
            graph.insert(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            int[] prereq = prerequisites[i];
            int dep = prereq[1];
            int course = prereq[0];
            graph.connect(dep, course);
        }
        //System.out.println("graph is initialized");
        List<Integer> vertices = graph.getVertices();
        Queue<Integer> queue = new LinkedList<>();
        int visited = 0;
        for (int vertex : vertices) {
            if (graph.isIndependent(vertex)) {
                queue.add(vertex);
            }
        }
        //System.out.println("queue:" + queue);
        while (!queue.isEmpty()) {
            int vertex = queue.remove();
            visited++;
            List<Integer> neighbors = graph.getNeighbors(vertex);
            //System.out.println("neighbors: " + neighbors);
            for (int neighbor : neighbors) {
                graph.disconnect(vertex, neighbor);
                //System.out.println("vertex: " + vertex + " is disconnected from neighbor: " + neighbor);
                //System.out.println("graph.isIndependent(neighbor): " + graph.isIndependent(neighbor));
                if (graph.isIndependent(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        //System.out.println("visited: " + visited + " , vertices.size(): " + vertices.size());
        return visited == vertices.size();
    }

    private static class Graph {
        private Map<Integer, List<Integer>> adjList = new HashMap<>();
        private Map<Integer, Integer> indegreeMap = new HashMap<>();

        public void insert(int vertex) {
            adjList.putIfAbsent(vertex, new ArrayList<>());
            indegreeMap.putIfAbsent(vertex, 0);
        }

        public void connect(Integer first, Integer second) {
            adjList.get(first).add(second);
            indegreeMap.put(second, indegreeMap.get(second) + 1);
            //System.out.println("Connected. indegreeMap.get("+second+"): " + indegreeMap.get(second));
        }

        public void disconnect(Integer first, Integer second) {
            //System.out.println("Disconnecting first(" + first + ") from second(" + second + "): " + adjList.get(first));
            //System.out.println("adjList.get("+first+") contains "+ second + ": " + adjList.get(first).contains(second));
            adjList.get(first).remove(second);
            //System.out.println("Disconnected. Now decrementing indegree");
            indegreeMap.put(second, indegreeMap.get(second) - 1);
            //System.out.println("disconnected. indegreeMap.get("+second+"): " + indegreeMap.get(second));
        }

        public boolean isIndependent(int vertex) {
            return indegreeMap.get(vertex) == 0;
        }

        public List<Integer> getVertices() {
            return new ArrayList<>(adjList.keySet());
        }

        public List<Integer> getNeighbors(int vertex) {
            return new ArrayList<>(adjList.get(vertex));
        }

    }
}
