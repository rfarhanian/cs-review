package com.mnaseri.cs.homework.problem;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Following are the streets and their corresponding traffic at a certain hour.
 * Implement a method that returns a street name based upon weighted randomness.
 * Street 1: 40000
 * Street 2: 1000
 * Street 3: 150
 */
public class WeightedRandomStreetFinder {

    private static final Random RANDOM = new Random(3423);
    private String[] weightedStreets; //0-99
    private int totalPercentage;
    public WeightedRandomStreetFinder(Map<String, Integer> map) {
        Integer total = map.values().stream().reduce(0, Integer::sum);
        Map<String, Integer> probabilityMap = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String streetName = entry.getKey();
            Integer trafficAmount = entry.getValue();
            int probability = (int) (((float) trafficAmount / total) * 100);
            probabilityMap.put(streetName, probability);
            System.out.println("street name : " + streetName + ", probability:" + probability);
        }
        Integer totalPercentage = probabilityMap.values().stream().reduce(0, Integer::sum);
        this.totalPercentage = totalPercentage;
        weightedStreets = new String[totalPercentage];
        int index = 0;
        for (Map.Entry<String, Integer> entry : probabilityMap.entrySet()) {
            String streetName = entry.getKey();
            Integer percentage = entry.getValue();
            for (int i = 0; i < percentage; i++) {
                weightedStreets[index] = streetName;
                index++;
            }
        }
        System.out.println("The distribution is pseudo random.......");

    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Champs Elysees", 40000);
        map.put("Victor Hugo", 1000);
        map.put("La defense", 1640);
        map.put("Montparnas", 1600);
        WeightedRandomStreetFinder finder = new WeightedRandomStreetFinder(map);
        Map<String, Integer> freq = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            String streetName = finder.find();
            freq.put(streetName, freq.getOrDefault(streetName, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

    public String find() {
        int randomNumber = RANDOM.nextInt(totalPercentage);
        return weightedStreets[randomNumber];
    }
}

