package com.farhanian.cs.slidingwindow;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindRepeatedSequencesBruteForce {

    public static void main(String[] args) {
        Set<String> output = FindRepeatedSequencesBruteForce.findRepeatedSequences("AAAAACCCCCAAAAACCCCCC", 8);
        System.out.println("output = " + output);
    }

    public static Set<String> findRepeatedSequences(String s, int k) {

        Map<String, Integer> frequency = new HashMap<>();
        for (int i = 0; i < s.length() - k; i++) {
            String window = s.substring(i, i + k);
            if (!frequency.containsKey(window)) {
                frequency.put(window, 0);
            }
            frequency.put(window, frequency.get(window) + 1);
        }
        Set<String> output = new HashSet<>();
        Set<Map.Entry<String, Integer>> entries = frequency.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            Integer value = entry.getValue();
            String key = entry.getKey();
            if (value > 1) {
                output.add(key);
            }
        }
        return output;
    }
}