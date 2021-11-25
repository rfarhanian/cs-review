package com.mnaseri.cs.homework.ch23;

import com.mnaseri.cs.homework.ch21.BasicDisjointSet;
import com.mnaseri.cs.homework.ch21.Item;
import com.mnaseri.cs.homework.ch22.Edge;
import com.mnaseri.cs.homework.ch22.Graph;

import java.util.*;

/**
 * The notability notes : Chapter 23 explains the problem very well.
 * So does Eric Demaine: https://www.youtube.com/watch?v=tKwnms5iRBU
 * This code is not tested.
 */
public class KruskalMinimumSpanningTreeFinder {


    public BasicDisjointSet find(Graph graph) {
        BasicDisjointSet disjointSet = new BasicDisjointSet();
        List<Edge> edges = resolveEdges(graph);
        edges.sort(new KruskalComparator());
        Map<Integer, Item> map = new HashMap<>();
        for (Integer vertex : graph.getVertices()) {
            Item item = disjointSet.create(vertex);
            map.put(vertex, item);
        }
        for (Edge edge : edges) {
            int from = edge.getFrom();
            int to = edge.getTo();
            Item fromRoot = disjointSet.find(map.get(from));
            Item toRoot = disjointSet.find(map.get(to));
            if (fromRoot.getUuid() != toRoot.getUuid()) {
                disjointSet.union(fromRoot, toRoot);
                toRoot.setUuid(fromRoot.getUuid());
            }
        }
        return disjointSet;
    }

    private List<Edge> resolveEdges(Graph graph) {
        List<Edge> result = new ArrayList<>();
        List<Integer> vertices = graph.getVertices();
        for (Integer i : vertices) {
            for (Integer j : graph.getVertices()) {
                if (graph.connected(i, j)) {
                    Edge edge = new Edge(i, j, graph.edge(i, j));
                    result.add(edge);
                }
            }
        }
        return result;
    }

    private static class KruskalComparator implements Comparator<Edge> {

        public int compare(Edge first, Edge second) {
            return Integer.compare(first.getValue(), second.getValue());
        }
    }


}
