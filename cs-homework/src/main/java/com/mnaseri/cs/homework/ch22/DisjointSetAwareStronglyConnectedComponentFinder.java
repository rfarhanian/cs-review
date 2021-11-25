package com.mnaseri.cs.homework.ch22;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=5wFyZJ8yH9Q
 */
public class DisjointSetAwareStronglyConnectedComponentFinder {

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(false);
        for (int i = 0; i < 9; i++) {
            graph.add(i);
        }
        graph.connect(0, 1);
        graph.connect(1, 2);
        graph.connect(2, 3);
        graph.connect(3, 0);
        graph.connect(2, 4);
        graph.connect(4, 5);
        graph.connect(5, 6);
        graph.connect(6, 4);
        graph.connect(7, 6);
        graph.connect(7, 8);


        DisjointSetAwareStronglyConnectedComponentFinder kosaraju = new DisjointSetAwareStronglyConnectedComponentFinder();
        SimpleDisjointSet result = kosaraju.find(graph);
        System.out.println("result = " + result);

    }

    public SimpleDisjointSet find(Graph g) {
        DepthFirstSearch dfs = new DepthFirstSearch(g);
        Stack<Integer> stack = new Stack<>();

        dfs.search(new GraphVisitor() {
            @Override
            public void visit(int value) {
                stack.push(value);
            }
        });
        Graph tg = g.transpose();
        Set<Integer> visited = new HashSet<>();
        SimpleDisjointSet disjointSet = new SimpleDisjointSet();
        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (!visited.contains(current)) {
                List<Integer> list = new ArrayList<>();
                dfs(tg, current, visited, list);
                disjointSet.create(current);
                for (int item : list) {
                    if (item != current) {
                        disjointSet.create(item);
                        disjointSet.union(current, item);
                    }
                }
            }
        }
        return disjointSet;
    }

    private void dfs(Graph g, Integer node, Set<Integer> visited, List<Integer> list) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        List<Integer> neighbors = g.getNeighbors(node);
        for (Integer neighbor : neighbors) {
            dfs(g, neighbor, visited, list);
        }

        list.add(node);
    }

    public static class SimpleDisjointSet {

        private Map<Integer, List<Integer>> map = new HashMap<>();

        public void create(int item) {
            List<Integer> set = new ArrayList<>();
            set.add(item);
            map.put(item, set);
        }

        public void union(int first, int second) {
            List<Integer> secondList = map.get(second);
            map.remove(second);
            map.get(first).addAll(secondList);
        }

        public Integer find(int item) {
            List<Integer> list = map.get(item);
            return list.get(0);
        }

        @Override
        public String toString() {
            return map.toString();
        }
    }

}
