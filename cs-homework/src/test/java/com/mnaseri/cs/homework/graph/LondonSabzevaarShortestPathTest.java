package com.mnaseri.cs.homework.graph;

import com.mmnaseri.cs.clrs.ch22.s1.AdjacencyMatrixGraph;
import com.mmnaseri.cs.clrs.ch22.s1.Graph;
import com.mmnaseri.cs.clrs.ch22.s1.SimpleVertexDetails;
import com.mmnaseri.cs.clrs.ch22.s1.Vertex;
import com.mmnaseri.cs.clrs.ch23.s1.MutableWeightedEdgeDetails;
import com.mmnaseri.cs.clrs.ch24.s3.DijkstraSingleSourceShortestPathFinder;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Please create the following cities in your graph:
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
 * Find the cheapest flight between London and Sabezevaar.
 */
public class LondonSabzevaarShortestPathTest {
    //private List<String> cities = Arrays.asList("--0---", "--1--", "--2---", "----3----", "---4----", "--5-");
    private List<String> cities = Arrays.asList("London", "Paris", "Tehran", "Asalooyeh", "Sabzevaar", "Kish");
    private Map<String, Vertex<SimpleVertexDetails>> citiesMap = new HashMap<>();
    private Graph<MutableWeightedEdgeDetails, SimpleVertexDetails> graph;

    @BeforeMethod
    public void setUp() {
        graph = new AdjacencyMatrixGraph<>();

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
    }

    @Test
    public void testGraph() {
        DijkstraSingleSourceShortestPathFinder<MutableWeightedEdgeDetails, SimpleVertexDetails> pathFinder = new DijkstraSingleSourceShortestPathFinder<MutableWeightedEdgeDetails, SimpleVertexDetails>();
        Graph<MutableWeightedEdgeDetails, SimpleVertexDetails> path = pathFinder.find(graph, location("London"));
        DijkstraSingleSourceShortestPathFinder<MutableWeightedEdgeDetails, SimpleVertexDetails> dijkstra = new DijkstraSingleSourceShortestPathFinder<>();
        Graph<MutableWeightedEdgeDetails, SimpleVertexDetails> graph = dijkstra.find(path, 0);
        final LinkedList<String> shortestPath = new LinkedList<>();
        List<String> expectedOutput = buildComputedShortestPath(graph, shortestPath);
        Assert.assertEquals(expectedOutput.size(), shortestPath.size());
        Assert.assertEquals(shortestPath, expectedOutput);
        System.out.println("result = " + shortestPath);
    }

    private List<String> buildComputedShortestPath(Graph<MutableWeightedEdgeDetails, SimpleVertexDetails> graph, LinkedList<String> shortestPath) {
        Vertex<SimpleVertexDetails> vertex = graph.get(location("Sabzevaar"));
        Assert.assertNotNull(vertex);
        Vertex<SimpleVertexDetails> current = vertex;
        while (current != null) {
            String currentCity = (String) LondonSabzevaarShortestPathTest.this.graph.get(current.getIndex()).getProperty("city");
            shortestPath.addFirst(currentCity);
            Vertex parent = (Vertex) current.getProperty("predecessor");
            current = parent != null ? graph.get(parent.getIndex()) : null;
        }

        return Arrays.asList("London", "Paris", "Asalooyeh", "Tehran", "Sabzevaar");
    }

    private int location(String location) {
        return citiesMap.get(location).getIndex();
    }
}
