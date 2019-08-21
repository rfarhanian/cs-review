package com.mnaseri.cs.homework.graph;

import com.mmnaseri.cs.clrs.ch22.s1.EdgeDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch22.s1.VertexDetails;

import java.util.ArrayList;
import java.util.List;

public class LondonSabzevarAllPath {

    private Graph<? extends EdgeDetails, ? extends VertexDetails> graph;

    public LondonSabzevarAllPath(Graph<? extends EdgeDetails, ? extends VertexDetails> graph) {
        this.graph = graph;
    }

    public List<List<String>> dfs(Vertex<? extends VertexDetails> source, Vertex<? extends VertexDetails> destination) {
        return dfs(graph, source.getIndex(), destination.getIndex());
    }

    public List<List<String>> dfs(Graph<? extends EdgeDetails, ? extends VertexDetails> graph, int start, int destination) {
        List<List<String>> solutions = new ArrayList<>();
        ArrayList<String> path = new ArrayList<>();
        path.add("London");
        doDfs(graph, graph.get(start), graph.get(destination), path, solutions);
        return solutions;
    }

    private void doDfs(Graph<? extends EdgeDetails, ? extends VertexDetails> graph, Vertex<? extends VertexDetails> vertex, Vertex<? extends VertexDetails> destination, List<String> path, List<List<String>> solutions) {
        if (vertex.equals(destination)) {
            solutions.add(path);
            return; //This is not needed because according to our graph, airplanes cannot fly away from Sabzevaar.

        }
        List<? extends Vertex<? extends VertexDetails>> neighbors = graph.getNeighbors(vertex.getIndex());
        for (Vertex<? extends VertexDetails> current : neighbors) {
            String city = (String) current.getProperty("city");
            List<String> copiedEnrichedPath = new ArrayList<>(path); //why is this fixing the problem?
            copiedEnrichedPath.add(city);
            doDfs(graph, current, destination, copiedEnrichedPath, solutions);
        }
    }


}
