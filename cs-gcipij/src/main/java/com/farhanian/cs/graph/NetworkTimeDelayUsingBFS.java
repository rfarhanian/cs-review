package com.farhanian.cs.graph;

import java.util.*;

/**
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/network-delay-time">Educative.io</a>
 * @see <a href="https://leetcode.com/problems/network-delay-time/">Leetcode</a>
 */
public class NetworkTimeDelayUsingBFS {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> adjList = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            adjList.put(i, new ArrayList<>());
        }

        for (int[] time : times) {
            int from = time[0];
            int to = time[1];
            int distance = time[2];
            List<int[]> neighbors = adjList.get(from);
            neighbors.add(new int[]{to, distance});
        }
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{k, 0});

        int maxDistance = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.remove();
            int from = current[0];
            int currentDistance = current[1];
            if (!visited.contains(from)) {
                visited.add(from);
                maxDistance = Math.max(maxDistance, currentDistance);
                List<int[]> neighbors = adjList.get(from);
                for (int[] neighbor : neighbors) {
                    if (!visited.contains(neighbor[0])) {
                        int newTime = currentDistance + neighbor[1];
                        pq.add(new int[]{neighbor[0], newTime});
                    }
                }
            }

        }

        return visited.size() < n ? -1 : maxDistance;

    }
}
