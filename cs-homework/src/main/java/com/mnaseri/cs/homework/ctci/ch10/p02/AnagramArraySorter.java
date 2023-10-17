package com.mnaseri.cs.homework.ctci.ch10.p02;

import java.util.*;

public class AnagramArraySorter {
    public static void main(String[] args) {
        String[] input = new String[]{"race", "abbax", "arec", "ac", "baabx", "acd"};
        String[] expectedOutput = new String[]{"ac", "acd", "aba", "baa", "care", "race"};
        sort(input);
        System.out.println("expectedOutput = " + Arrays.toString(expectedOutput));
        System.out.println("output = " + Arrays.toString(input));
    }

    public static String[] sort(String[] input) {
        //"race", "abba", "care", "ac", "baab", "acd"
        //"aabb", "aabb", "ac", "acd", "acer", "acer"
        //itemCache: {"race":"acer"}, {"abba": "aabb"}, {"care": "acer"}, {"ac": "ac"}, {"baab":"aabb"}, {"acd","acd"}}
        //sortedCache:{"acer":["care","race"]}, {"aabb":["abba","baab"]}, {"ac": ["ac"]}, {"acd",["acd"]}}
        if (input.length == 0) {
            return new String[0];
        }
//        Arrays.sort(input);
        Map<String, String> itemCache = new HashMap<>();
        Map<String, List<String>> sortedCache = new HashMap<>();
        for (String item : input) {
            char[] sorted = item.toCharArray(); //race
            Arrays.sort(sorted);//acer
            String sortedValue = new String(sorted);//acer
            itemCache.put(item, sortedValue); // {"race", "acer"
            List<String> sortedValueList = sortedCache.getOrDefault(sortedValue, new ArrayList<>());
            sortedValueList.add(item);
            sortedCache.put(sortedValue, sortedValueList);//"acer":["race"]
        }
        for (int i = 0; i < input.length; i++) {
            String item = input[i];
            input[i] = itemCache.get(item);
        }
        Arrays.sort(input); //"aabb", "aabb", "ac", "acd", "acer", "acer"
        for (Map.Entry<String, List<String>> entry : sortedCache.entrySet()) {
            List<String> value = entry.getValue();
            if (value.size() > 1) {
                Collections.sort(value);
            }
        }
        for (int i = 0; i < input.length; i++) {
            String item = input[i];
            List<String> original = sortedCache.get(item);
            input[i] = original.get(0);
            original.remove(0);
            if (original.isEmpty()) {
                sortedCache.remove(item);
            }
        }
        return input;


    }

    public static boolean isAnagram(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        if (first.length() != second.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < first.length(); i++) {
            Character current = first.charAt(i);
            Character secondCandidate = second.charAt(i);
            map.put(current, map.getOrDefault(current, 0) + 1);
            map.put(secondCandidate, map.getOrDefault(secondCandidate, 0) - 1);
            if (map.get(current) == 0) {
                map.remove(current);
            }
            if (map.get(secondCandidate) == 0) {
                map.remove(secondCandidate);
            }
        }
        return map.isEmpty();
    }
}
