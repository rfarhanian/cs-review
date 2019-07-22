package com.mnaseri.cs.homework.graph.util;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyMatrixGraph;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.SimpleVertexDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch23.s1.MutableWeightedEdgeDetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * London, Paris, Asalooye, Tehran, Sabzevaar, Kish, Khaash
 * <p>
 * Following are the price of the tickets between cities:
 * London ---> Paris (250 Pound)
 * London --> Tehran (600 Pound)
 * Paris --> Tehran (550 Pound)
 * Paris--> Asalooyeh (100 Pound)
 * Tehran --> Sabzevaar (20 Pound)
 * Asalooyeh --> Tehran (15 Pound)
 * Asalooyeh --> Kish (99 Pound)
 * Kish --> Tehran (90 Pound)
 * London --> Kish (1000 Pound)
 * <p>
 */
public class GraphDataStore {

    //private List<String> cities = Arrays.asList("--0---", "--1--", "--2---", "----3----", "---4----", "--5-");
    private List<String> cities = Arrays.asList("London", "Paris", "Tehran", "Asalooyeh", "Sabzevaar", "Kish");
    private Map<String, Vertex<SimpleVertexDetails>> citiesMap = new HashMap<>();


    public Graph<MutableWeightedEdgeDetails, SimpleVertexDetails> londonSabzevarGraph() {
        Graph<MutableWeightedEdgeDetails, SimpleVertexDetails> graph = new AdjacencyMatrixGraph<>();

        for (String cityName : cities) {
            int vertexId = graph.add();
            Vertex<SimpleVertexDetails> vertex = graph.get(vertexId);
            vertex.setProperty("city", cityName);
            citiesMap.put(cityName, vertex);
        }

        graph.connect(location("London"), location("Paris")).setDetails(new MutableWeightedEdgeDetails(250));
        graph.connect(location("London"), location("Tehran")).setDetails(new MutableWeightedEdgeDetails(600));
        graph.connect(location("Paris"), location("Tehran")).setDetails(new MutableWeightedEdgeDetails(550));
        graph.connect(location("Paris"), location("Asalooyeh")).setDetails(new MutableWeightedEdgeDetails(100));
        graph.connect(location("Tehran"), location("Sabzevaar")).setDetails(new MutableWeightedEdgeDetails(20));
        graph.connect(location("Asalooyeh"), location("Tehran")).setDetails(new MutableWeightedEdgeDetails(15));
        graph.connect(location("Asalooyeh"), location("Kish")).setDetails(new MutableWeightedEdgeDetails(99));
        graph.connect(location("Kish"), location("Tehran")).setDetails(new MutableWeightedEdgeDetails(90));
        graph.connect(location("London"), location("Kish")).setDetails(new MutableWeightedEdgeDetails(1000));
        return graph;
    }

    public int location(String location) {
        return citiesMap.get(location).getIndex();
    }
}
