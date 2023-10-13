package com.mnaseri.cs.homework.ch22;

import java.util.*;

/**
 * https://www.youtube.com/watch?v=5wFyZJ8yH9Q
 * <p>
 * The good thing about the transposed graph is that it can find the strongly connected component if you know where to start.
 * Try the algorithm using three nodes (e.g. 0, 1, 2). Find out the dfs and transposed dfs result. Then the algorithm
 * becomes intuitive.
 */
public class StronglyConnectedComponentFinder {

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(false);
//        for (int i = 0; i < 3; i++) {
//            graph.add(i);
//        }
//        graph.connect(0, 1);
//        graph.connect(1, 2);
//        graph.connect(2, 0);

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


        StronglyConnectedComponentFinder kosaraju = new StronglyConnectedComponentFinder();
        Map<Integer, List<Integer>> result = kosaraju.find(graph);
        System.out.println("result = " + result);

    }

    public Map<Integer, List<Integer>> find(Graph g) {
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
        Map<Integer, List<Integer>> map = new HashMap<>();
        while (!stack.isEmpty()) {
            Integer current = stack.pop();
            if (!visited.contains(current)) {
                List<Integer> set = new ArrayList<>();
                dfs(tg, current, visited, set);
                map.put(current, set);
            }
        }
        return map;
    }

    private void dfs(Graph g, Integer node, Set<Integer> visited, List<Integer> set) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        List<Integer> neighbors = g.getNeighbors(node);
        for (Integer neighbor : neighbors) {
            dfs(g, neighbor, visited, set);
        }

        set.add(node);
    }

}
