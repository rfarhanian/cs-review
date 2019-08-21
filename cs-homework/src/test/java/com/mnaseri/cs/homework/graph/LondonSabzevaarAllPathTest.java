package com.mnaseri.cs.homework.graph;

import com.mmnaseri.cs.clrs.ch22.s1.*;
import com.mnaseri.cs.homework.graph.util.GraphDataStore;
import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Find all paths between London and Sabezevaar.
 */
public class LondonSabzevaarAllPathTest {
    private Map<String, Vertex<SimpleVertexDetails>> citiesMap = new HashMap<>();
    private GraphDataStore dataStore = new GraphDataStore();
    private Graph<? extends EdgeDetails, ? extends VertexDetails> graph;
    private LondonSabzevarAllPath londonSabzevarAllPath;

    @BeforeMethod
    public void setUp() {
        graph = dataStore.londonSabzevarGraph();
        londonSabzevarAllPath = new LondonSabzevarAllPath(graph);
    }

    @Test
    public void verifyAllExistingPath() {
        Vertex<? extends VertexDetails> source = graph.get(dataStore.location("London"));
        Vertex<? extends VertexDetails> destination = graph.get(dataStore.location("Sabzevaar"));
        List<List<String>> solution = londonSabzevarAllPath.dfs(source, destination);
        Assert.assertNotNull(solution);
        Assert.assertEquals(5, solution.size());
        System.out.println("solution = " + solution);

    }

}
