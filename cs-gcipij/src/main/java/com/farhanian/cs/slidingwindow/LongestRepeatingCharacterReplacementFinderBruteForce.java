package com.farhanian.cs.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestRepeatingCharacterReplacementFinderBruteForce {

    public int characterReplacement(String s, int k) {
        List<String> substrings = getAllSubstrings(s);
        int max = 0;
        for (int i = 0; i < substrings.size(); i++) {
            String current = substrings.get(i);
            System.out.println(current);
            int candidate = getLongest(current, k);
            if (max < candidate) {
                max = candidate;
                //System.out.println("max:" + current);
            }
        }
        return max;
    }

    private int getLongest(String x, int k) {
        if (x.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> freq = new HashMap<>();
        int maxFreq = 1;
        char maxChar = x.charAt(0);
        for (int i = 0; i < x.length(); i++) {
            char current = x.charAt(i);
            int max = freq.getOrDefault(current, 0) + 1;
            freq.put(current, max);
            if (max > maxFreq) {
                maxFreq = max;
                maxChar = current;
            }
        }

        int modifications = 0;
        for (int i = 0; i < x.length(); i++) {
            char current = x.charAt(i);
            if (current != maxChar) {
                if (modifications < k) {
                    modifications++;
                } else {
                    return -1;
                }
            }
        }
        // if(modifications <= k ){
        //     System.out.println("candidate: "+ x);
        // }
        return modifications > k ? -1 : x.length();


    }

    private List<String> getAllSubstrings(String s) {
        List<String> substrings = new ArrayList<>();
        for (int len = 1; len <= s.length(); len++) {
            for (int j = 0; j <= s.length() - len; j++) {
                String candidate = s.substring(j, j + len);
                substrings.add(candidate);
            }
        }
        return substrings;
    }
}
