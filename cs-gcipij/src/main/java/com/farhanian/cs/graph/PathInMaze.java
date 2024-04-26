package com.farhanian.cs.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The most important part of this problem is to visualize how an adjcancy matrix or adjaceny set can detect cycles of
 * length three or more. If you visualize it with a matrix, you can see that common elements in both rows indicate that
 * room i and room j (that already have a cycle) have other rooms in common. The sum of these rooms becomes their cycle
 * size for any two row. You need to apply this comparison for all edges.
 *
 * @see <a link="https://www.educative.io/courses/grokking-coding-interview-patterns-java/paths-in-maze-that-lead-to-same-room">The course</a>
 */
public class PathInMaze {

    public static int numberOfPaths(int n, int[][] corridors) {

        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        int cycles = 0;
        for (int row = 0; row < corridors.length; row++) {
            int[] corridor = corridors[row];
            int room1 = corridor[0];
            int room2 = corridor[1];
            adjList.putIfAbsent(room1, new HashSet<>());
            adjList.putIfAbsent(room2, new HashSet<>());
            adjList.get(room1).add(room2);
            adjList.get(room2).add(room1);
            cycles += findIntersections(adjList.get(room1), adjList.get(room2));
        }
        return cycles;
    }

    private static int findIntersections(Set<Integer> first, Set<Integer> second) {
        int counter = 0;
        for (Integer item : first) {
            if (second.contains(item)) {
                counter++;
            }
        }
        return counter;
    }
}