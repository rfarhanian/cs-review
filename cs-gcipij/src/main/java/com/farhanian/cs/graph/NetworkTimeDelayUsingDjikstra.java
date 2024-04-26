package com.farhanian.cs.graph;

import java.util.*;

/**
 * I wouldn't implement the following solution using Djikstra. I would come up with an adjList as result and
 * then compare it against the existing graph(adj) in the relax phase. I would also use int[] in lieu of Pair class.
 *
 * @see <a href="https://www.educative.io/courses/grokking-coding-interview-patterns-java/network-delay-time">Educative.io</a>
 * @see <a href="https://leetcode.com/problems/network-delay-time/">Leetcode</a>
 */
public class NetworkTimeDelayUsingDjikstra {

    // Adjacency list
    private Map<Integer, List<Pair<Integer, Integer>>> adj = new HashMap<>();

    public int networkDelayTime(int[][] times, int n, int k) {
        // Build the adjacency list
        for (int[] time : times) {
            int source = time[0];
            int dest = time[1];
            int travelTime = time[2];

            adj.putIfAbsent(source, new ArrayList<>());
            adj.get(source).add(new Pair(travelTime, dest));
        }

        int[] signalReceivedAt = new int[n + 1];
        Arrays.fill(signalReceivedAt, Integer.MAX_VALUE);

        dijkstra(signalReceivedAt, k, n);

        int answer = Integer.MIN_VALUE;
        for (int i = 1; i <= n; i++) {
            answer = Math.max(answer, signalReceivedAt[i]);
        }

        // INT_MAX signifies atleat one node is unreachable
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private void dijkstra(int[] signalReceivedAt, int source, int n) {
        Queue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getKey() - b.getKey());
        pq.add(new Pair(0, source));

        // Time for starting node is 0
        signalReceivedAt[source] = 0;

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> topPair = pq.remove();

            int node = topPair.getValue();
            int currentTime = topPair.getKey();

            if (currentTime > signalReceivedAt[node]) {
                continue;
            }

            if (!adj.containsKey(node)) {
                continue;
            }

            // Broadcast the signal to adjacent nodes
            for (Pair<Integer, Integer> edge : adj.get(node)) {
                int time = edge.getKey();
                int neighborNode = edge.getValue();

                // Fastest signal time for neighborNode so far
                // signalReceivedAt[currNode] + time :
                // time when signal reaches neighborNode
                if (signalReceivedAt[neighborNode] > currentTime + time) { //Relax operation
                    signalReceivedAt[neighborNode] = currentTime + time;
                    pq.add(new Pair<>(signalReceivedAt[neighborNode], neighborNode));
                }
            }
        }
    }


    public static class Pair<T, T1> {
        private T key;
        private T1 value;

        public Pair(T key, T1 value) {
            this.key = key;
            this.value = value;
        }

        public T getKey() {
            return key;
        }

        public T1 getValue() {
            return value;
        }
    }
}
