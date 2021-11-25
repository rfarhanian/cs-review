package com.mnaseri.cs.homework.ch22;

import java.util.*;

public class AdjacencyListGraph implements Graph {

    private Map<Integer, List<Edge>> adjList = new HashMap<>();
    private List<Integer> nodes = new ArrayList<>();
    private int size;
    private boolean indirect;

    public AdjacencyListGraph(AdjacencyListGraph graph) {
        size = graph.size;
        nodes = graph.nodes;
        adjList = transposeInit(nodes, graph.adjList);
    }

    public AdjacencyListGraph(boolean indirect) {
        this.indirect = indirect;
        size = 0;
    }

    public static void main(String[] args) {
        AdjacencyListGraph graph = new AdjacencyListGraph(false);
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
        System.out.println("5,5 are disconnected now.");
        System.out.println("graph.connected(5,5) = " + graph.connected(5, 5));
        System.out.println("1 is to be deleted from the graph. All connections must be removed. neighbors:" + graph.getNeighbors(1));
        graph.delete(1);
        System.out.println("graph.getNeighbors(10) = " + graph.getNeighbors(10));
    }

    private Map<Integer, List<Edge>> transposeInit(List<Integer> nodes, Map<Integer, List<Edge>> edges) {
        Map<Integer, List<Edge>> newAdjList = new HashMap<>();
        for (Integer node : nodes) {
            newAdjList.put(node, new ArrayList<>());
        }
        for (List<Edge> oldEdges : edges.values()) {
            for (Edge oldEdge : oldEdges) {
                Edge newEdge = new Edge(oldEdge.getTo(), oldEdge.getFrom(), oldEdge.getValue());
                newAdjList.get(newEdge.getFrom()).add(newEdge);
            }
        }
        return newAdjList;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void delete(int node) {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node " + node + " does not exist.");
        }
        nodes.remove(node);
        size--;
        List<Edge> edges = adjList.get(node);
        for (Edge edge : edges) {
            cleanNeighbor(edge.getTo(), edge.getFrom()); //host , target
        }
        adjList.remove(node);
    }

    private void cleanNeighbor(int host, int target) {
        List<Edge> edges = adjList.get(host);
        Edge theEdge = null;
        for (Edge edge : edges) {
            if (edge.getTo() == target) {
                theEdge = edge;
                break;
            }
        }
        if (theEdge != null) {
            edges.remove(theEdge);
        }
    }

    @Override
    public int add(int node) {
        nodes.add(node);
        adjList.put(node, new ArrayList<Edge>());
        size++;
        return node;
    }

    @Override
    public int get(int index) {
        return nodes.get(index);
    }

    @Override
    public int edge(int from, int to) {
        List<Edge> edges = adjList.get(from);
        for (Edge edge : edges) {
            if (edge.getTo() == to) {
                return edge.getValue();
            }
        }
        return -1;
    }

    @Override
    public boolean connected(int from, int to) {
        List<Edge> edges = adjList.get(from);
        for (Edge edge : edges) {
            if (edge.getFrom() == from && edge.getTo() == to) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int connect(int from, int to) {
        List<Edge> edges = adjList.get(from);
        edges.add(new Edge(from, to, 0));
        if (indirect) {
            List<Edge> theOtherEdges = adjList.get(to);
            if (theOtherEdges == null) {
                adjList.put(to, new ArrayList<>());
                theOtherEdges = adjList.get(to);
            }
            theOtherEdges.add(new Edge(to, from, 0));
        }
        return 0;
    }

    @Override
    public Edge connect(int from, int to, int details) {
        List<Edge> edges = adjList.get(from);
        if (edges == null) {
            adjList.put(from, new ArrayList<>());
            edges = adjList.get(from);
        }
        Edge edge = new Edge(from, to, details);
        edges.add(edge);
        if (indirect) {
            List<Edge> theOtherEdges = adjList.get(to);
            if (theOtherEdges == null) {
                adjList.put(to, new ArrayList<>());
                theOtherEdges = adjList.get(to);
            }
            theOtherEdges.add(new Edge(to, from, details));
        }
        return edge;
    }

    @Override
    public void disconnect(int from, int to) {
        List<Edge> edges = adjList.get(from);
        Edge theEdge = null;
        for (Edge edge : edges) {
            if (edge.getTo() == to && edge.getFrom() == from) {
                theEdge = edge;
                break;
            }
        }
        if (theEdge != null) {
            edges.remove(theEdge);
        }
        if (indirect) {
            edges = adjList.get(to);
            theEdge = null;
            for (Edge edge : edges) {
                if (edge.getTo() == from && edge.getFrom() == to) {
                    theEdge = edge;
                    break;
                }
            }
            if (theEdge != null) {
                edges.remove(theEdge);
            }
        }
    }


    @Override
    public AdjacencyListGraph transpose() {
        return new AdjacencyListGraph(this) {
            @Override
            public int edge(int from, int to) {
                return super.edge(to, from);
            }

            @Override
            public boolean connected(int from, int to) {
                return super.connected(to, from);
            }

            @Override
            public int connect(int from, int to) {
                return super.connect(to, from);
            }

            @Override
            public Edge connect(int from, int to, int details) {
                return super.connect(to, from, details);
            }

            @Override
            public void disconnect(int from, int to) {
                super.disconnect(to, from);
            }

        };
    }

    @Override
    public List<Integer> getVertices() {
        return Collections.unmodifiableList(nodes);
    }

    @Override
    public List<Integer> getNeighbors(int node) {
        if (!nodes.contains(node)) {
            throw new IllegalArgumentException("Node " + node + " does not exist.");
        }
        List<Integer> result = new ArrayList<>();
        List<Edge> edges = adjList.get(node);
        for (Edge edge : edges) {
            result.add(edge.getTo());
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Integer node : nodes) {
            builder.append(node).append("->");
            List<Edge> edges = adjList.get(node);
            for (Edge edge : edges) {
                builder.append(edge.getTo()).append("(").append(edge.getValue()).append(")");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
