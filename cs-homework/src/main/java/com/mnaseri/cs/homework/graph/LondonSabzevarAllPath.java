package com.mnaseri.cs.homework.graph;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

import java.util.LinkedList;
import java.util.List;

public class LondonSabzevarAllPath {

    private Graph<? extends EdgeDetails, ? extends VertexDetails> graph;

    public LondonSabzevarAllPath(Graph<? extends EdgeDetails, ? extends VertexDetails> graph) {
        this.graph = graph;
    }

    public List<String> dfs(Vertex<? extends VertexDetails> source, Vertex<? extends VertexDetails> destination) {
        return dfs(graph, source.getIndex(), destination.getIndex());
    }

    public List<String> dfs(Graph<? extends EdgeDetails, ? extends VertexDetails> graph, int start, int destination) {
//        final Graph<? extends EdgeDetails, ? extends VertexDetails> copy = GraphUtils.copy(graph);
//        List<? extends Vertex<? extends VertexDetails>> vertices = copy.getVertices();
//
//        for (Vertex<? extends VertexDetails> vertex : vertices) {
//            vertex.setProperty("color", GraphColor.WHITE);
//            vertex.setProperty("parent", null);
//            vertex.setProperty("distance", 0);
//        }
        LinkedList<String> solutions = new LinkedList<>();
        doDfs(graph, graph.get(start), graph.get(destination), "London", solutions);
        return solutions;
    }

    private void doDfs(Graph<? extends EdgeDetails, ? extends VertexDetails> graph, Vertex<? extends VertexDetails> vertex, Vertex<? extends VertexDetails> destination, String path, List<String> solutions) {
//        vertex.setProperty("color", GraphColor.GREY);
        if (vertex.equals(destination)) {
            solutions.add(path);
        }
        List<? extends Vertex<? extends VertexDetails>> neighbors = graph.getNeighbors(vertex.getIndex());
        for (Vertex<? extends VertexDetails> current : neighbors) {
//            if (GraphColor.WHITE.equals(current.getProperty("color", GraphColor.class))) {
//                current.setProperty("distance", vertex.getProperty("distance", Integer.class) + 1);
//                current.setProperty("parent", vertex);
            path = path + "->" + current.getProperty("city");
            doDfs(graph, current, destination, path, solutions);
//            }
        }
//        vertex.setProperty("color", GraphColor.BLACK);
    }


}
