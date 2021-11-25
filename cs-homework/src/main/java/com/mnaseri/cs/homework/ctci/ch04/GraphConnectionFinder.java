package com.mnaseri.cs.homework.ctci.ch04;

import java.util.*;

public class GraphConnectionFinder {


    public boolean isConnected(Node first, Node second) {
        Set<Node> visited = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        queue.add(first);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (!visited.contains(current)) {
                visited.add(current);
                if (current.equals(second)) {
                    return true;
                }
                List<Node> neighbors = first.neighbors;
                for (Node neighbor : neighbors) {
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }


    private static class Node {
        private Integer value;
        private List<Node> neighbors = new ArrayList<>();

        public Node(Integer value) {
            this.value = value;
        }


    }
}