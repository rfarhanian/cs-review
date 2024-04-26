package com.mnaseri.cs.homework.ch22;

import java.util.*;

public class MyAdjacencyListGraph<E> {

    private final Map<Integer, Vertex<E>> nodes = new HashMap<>();
    private final boolean isDirect;
    private int size;
    private Map<Vertex<E>, List<Edge>> list = new HashMap<>();

    public MyAdjacencyListGraph(boolean isDirect) {
        this.size = 0;
        this.isDirect = isDirect;
    }

    public Vertex<E> add(E node) {
        Vertex<E> vertex = new Vertex<>(nodes.size(), node);
        nodes.put(vertex.getIndex(), vertex);
        list.put(vertex, new ArrayList<>());
        size++;
        return vertex;
    }

    public void delete(Vertex<E> vertex) {
        list.remove(vertex);
        for (Map.Entry<Vertex<E>, List<Edge>> entry : list.entrySet()) {
            List<Edge> edges = entry.getValue();
            for (int i = 0; i < edges.size(); i++) {
                Edge edge = edges.get(i);
                if (edge.from == vertex.getIndex() || edge.to == vertex.getIndex()) {
                    edges.iterator().remove();
                }
            }
        }
        nodes.remove(vertex.getIndex());
        size--;
    }

    public void connect(Vertex<E> a, Vertex<E> b, int edgeValue) {
        int from = a.getIndex();
        int to = b.getIndex();
        Edge edge = new Edge(from, to, edgeValue);
        list.get(a).add(edge);
        if (!isDirect) {
            Edge indirectEdge = new Edge(to, from, edgeValue);
            list.get(b).add(indirectEdge);
        }
    }

    public void disconnect(Vertex<E> a, Vertex<E> b) {
        int from = a.getIndex();
        int to = b.getIndex();
        List<Edge> edges = list.get(a);
        for (Edge edge : edges) {
            if (edge.getTo() == to) {
                edges.iterator().remove();
            }
        }
        if (!isDirect) {
            List<Edge> indirectEdges = list.get(b);
            for (Edge edge : indirectEdges) {
                if (edge.from == from) {
                    indirectEdges.iterator().remove();
                }
            }
        }
    }

    public List<Vertex<E>> getVertices() {
        return new ArrayList<>(nodes.values());
    }

    public Vertex<E> getVertex(int index) {
        return nodes.get(index);
    }

    public Edge getEdge(int from, int to) {
        Vertex<E> fromVertex = getVertex(from);
        List<Edge> edges = list.get(fromVertex);
        for (Edge edge : edges) {
            if (edge.to == to) {
                return edge;
            }
        }
        return null;
    }

    public List<Vertex<E>> getNeighbors(Vertex<E> node) {
        List<Vertex<E>> output = new ArrayList<>();
        if (list.containsKey(node)) {
            List<Edge> edges = list.get(node);
            for (Edge edge : edges) {
                output.add(nodes.get(edge.getTo()));
            }
        }
        return output;
    }


    public static class Vertex<E> {
        private final int index;
        private final E value;

        public Vertex(int index, E value) {
            this.index = index;
            this.value = value;
        }

        public int getIndex() {
            return index;
        }

        public E getValue() {
            return value;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?> vertex = (Vertex<?>) o;
            return index == vertex.index && Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, value);
        }
    }

    public static class Edge {
        private final int from, to, value;

        public Edge(int from, int to, int value) {
            this.from = from;
            this.to = to;
            this.value = value;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from == edge.from && to == edge.to && value == edge.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to, value);
        }
    }
}
