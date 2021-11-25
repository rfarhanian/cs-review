package com.mnaseri.cs.homework.ch22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {
    private Edge[][] arr;
    private List<Integer> nodes = new ArrayList<>();
    private int size;
    private boolean indirect;

    public AdjacencyMatrixGraph(AdjacencyMatrixGraph graph) {
        arr = graph.arr;
        size = graph.size;
        nodes = graph.nodes;
    }

    public AdjacencyMatrixGraph(int capacity, boolean indirect) {
        arr = new Edge[capacity + 1][capacity + 1];
        this.indirect = indirect;
        size = 0;
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(10, false);
        for (int i = 1; i < 11; i++) {
            graph.add(i);
        }
        graph.connect(1, 10, 200);
        graph.connect(10, 1, 2);
        graph.connect(2, 9, 200);
        graph.connect(3, 8, 200);
        graph.connect(4, 6, 200);
        graph.connect(5, 5, 0);
        System.out.println("graph.getNeighbors(10) = " + graph.getNeighbors(10));
        System.out.println("graph.edge(1,10) = " + graph.edge(1, 10));
        System.out.println("graph.getNeighbors(1) = " + graph.getNeighbors(1));
        System.out.println("graph.edge(10,1) = " + graph.edge(10, 1));
        System.out.println("graph.connected(5,5) = " + graph.connected(5, 5));
        graph.disconnect(5, 5);
        System.out.println("graph.connected(5,5) = " + graph.connected(5, 5));
        graph.delete(1);
        System.out.println("graph.getNeighbors(10) = " + graph.getNeighbors(10));
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void delete(int node) {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node " + node + " does not exist.");
        }
        nodes.remove(node);
        Edge[] row = arr[node];
        Arrays.fill(row, null);
        for (int i = 0; i < arr.length; i++) {
            arr[i][node] = null;
        }
    }

    public int add(int detail) {
        detail = Math.max(1, Math.min(arr.length, detail));
        nodes.add(detail);
        size++;
        return detail;
    }

    public int get(int index) {
        return nodes.get(index);
    }

    public int edge(int from, int to) {
        Edge edge = arr[from][to];
        return edge != null ? edge.getValue() : -1;
    }

    public boolean connected(int from, int to) {
        return arr[from][to] != null;
    }

    public int connect(int from, int to) {
        if (indirect) {
            arr[to][from] = new Edge(to, from, 0);
        }
        arr[from][to] = new Edge(from, to, 0);
        return 0;
    }

    public Edge connect(int from, int to, int details) {
        if (indirect) {
            arr[to][from] = new Edge(to, from, details);
        }
        arr[from][to] = new Edge(from, to, details);
        return new Edge(from, to, details);
    }

    public void disconnect(int from, int to) {
        if (indirect) {
            arr[to][from] = null;
        }
        arr[from][to] = null;
    }


    public Graph transpose() {
        throw new UnsupportedOperationException();
    }

    public List<Integer> getVertices() {
        return Collections.unmodifiableList(nodes);
    }

    public List<Integer> getNeighbors(int index) {
        if (!nodes.contains(index)) {
            throw new IllegalArgumentException("Node " + index + " does not exist.");
        }
        List<Integer> result = new ArrayList<>();
        Edge[] row = arr[index];
        for (int i = 0; i < row.length; i++) {
            Edge item = row[i];
            if (item != null) {
                result.add(item.getTo());
            }
        }
        return result;
    }
}
